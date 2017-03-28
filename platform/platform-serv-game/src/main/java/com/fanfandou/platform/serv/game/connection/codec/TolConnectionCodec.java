package com.fanfandou.platform.serv.game.connection.codec;

import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.util.ProtostuffSerializeUtil;
import com.fanfandou.platform.api.game.entity.Message;
import com.fanfandou.platform.serv.game.entity.tol.CommonMsgType;
import com.google.common.base.Strings;
import com.google.common.primitives.Bytes;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Shorts;
import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Description: tol通讯协议.
 * <p/>
 * 数据包包含3部分：
 * <p/>
 * 1.包长度（2字节）：<p/>
 * 对应cpp的short<p/>
 * 按网络序（大端序）存储<p/>
 * 包长度 = 协议号（4字节） + 消息内容（x字节）<p/>
 * 合法包长度应为：4B ～ 20KB，即至少包含一个协议号
 * <p/>
 * 2.协议号（4字节）：<p/>
 * 对应cpp的int<p/>
 * 按网络序（大端序）存储
 * <p/>
 * 3.消息内容（x字节）：<p/>
 * protobuf结构序列化之后的字节序
 * <p/>
 * Date: 2016-05-25 10:50.
 * author: Arvin.
 */
public class TolConnectionCodec extends BaseLogger implements ConnectionCodec<Message> {

    private static final int maxTypeBytes = 4;
    private static final int maxLenBytes = 2;


    @Override
    public byte[] encode(Message msg) {
        byte[] msgBodyBytes = ProtostuffSerializeUtil.serializeProtoBuf(msg.getMsgBody());
        if (msgBodyBytes == null) {
            return null;
        }
        // 按 big endian 写入包长度
        int packetLen = maxTypeBytes + msgBodyBytes.length;
        byte[] lenBytes = Shorts.toByteArray((short) packetLen);

        // 按 big endian 写入协议号
        int msgType = msg.getProtocolType();
        byte[] typeBytes = Ints.toByteArray(msgType);
        logger.debug("TolConnectionCodec.encode -> lenBytes:{}, typeBytes:{}, msgBodyBytes:{}",
                lenBytes, typeBytes, msgBodyBytes);
        //组合消息
        return Bytes.concat(lenBytes, typeBytes, msgBodyBytes);
    }

    @Override
    public Message decode(byte[] datas) {
        if (datas.length < maxTypeBytes + maxLenBytes) {
            logger.info("TolConnectionCodec.decode-> tol response msg length is less than the minimum length!");
            return null;
        }
        byte[] lenBytes = Arrays.copyOfRange(datas, 0, maxLenBytes);//包长度
        byte[] typeBytes = Arrays.copyOfRange(datas, maxLenBytes, maxLenBytes + maxTypeBytes);//协议号
        byte[] msgBodyBytes = Arrays.copyOfRange(datas, maxTypeBytes + maxLenBytes, datas.length);//内容
        int len = Shorts.fromByteArray(lenBytes);
        if (len != maxTypeBytes + msgBodyBytes.length) {
            logger.info("TolConnectionCodec.decode-> tol response msg length is wrong!");
            return null;
        }
        //根据type直接转换bean
        CommonMsgType msgType = CommonMsgType.valueOf(Ints.fromByteArray(typeBytes));
        if (msgType != null) {
            Object msgBody = ProtostuffSerializeUtil.deserializeProtoBuf(msgBodyBytes, msgType.getClazz());
            //此处根据msgtype取到messageId
            String msgId = "";
            if (!Strings.isNullOrEmpty(msgType.getMsgIdFieldName())) {
                try {
                    Field msgIdField = msgType.getClazz().getDeclaredField(msgType.getMsgIdFieldName());
                    msgIdField.setAccessible(Boolean.TRUE);
                    msgId = (String) msgIdField.get(msgBody);
                } catch (ReflectiveOperationException e) {
                    logger.error("TolConnectionCodec.decode-> get msg id field!", e);
                }
            }

            return new Message<>(msgId, msgBody, msgType.getMsgType(), msgType.getId());
        }
        return null;

    }

}
