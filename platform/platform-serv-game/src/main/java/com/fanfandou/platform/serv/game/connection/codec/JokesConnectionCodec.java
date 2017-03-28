package com.fanfandou.platform.serv.game.connection.codec;

import com.alibaba.fastjson.JSONObject;
import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.util.ProtostuffSerializeUtil;
import com.fanfandou.platform.api.game.entity.Message;
import com.fanfandou.platform.serv.game.entity.tol.CommonMsgType;
import com.google.common.base.Strings;
import com.google.common.primitives.Bytes;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Shorts;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Created by wudi.
 * Descreption:十冷通讯协议.
 * Date:2017/3/8
 */
public class JokesConnectionCodec extends BaseLogger implements ConnectionCodec<Message> {

    /**
     * encode包头长度.
     */
    private static final int REQ_PACKAGE_HEAD_SIZE = 12;

    private static final int MSG_ID = 3000;

    private static final int GATE_ID = 0;

    private static final int maxLenBytes = 2;

    private static final int maxTypeLen = 2;

    @Override
    public byte[] encode(Message msg) {
        byte[] msgBodyBytes = ProtostuffSerializeUtil.serializeProtoBuf(msg.getMsgBody());
        if (msgBodyBytes == null) {
            return null;
        }
        int bodyLenth = REQ_PACKAGE_HEAD_SIZE + msgBodyBytes.length;
        byte[] lenBytes = Shorts.toByteArray((short) bodyLenth);
        byte[] msgBytes = Shorts.toByteArray((short) MSG_ID);
        byte[] gateBytes = Ints.toByteArray(GATE_ID);
        byte[] bodyBytes = Ints.toByteArray(msgBodyBytes.length);

        return Bytes.concat(lenBytes, msgBytes, gateBytes, bodyBytes);
    }

    @Override
    public Message decode(byte[] datas) {
        //先取包头
        byte[] maxLengBytes = Arrays.copyOfRange(datas, 0, maxLenBytes);//包长度 2个字节

        byte[] msgIdBytes = Arrays.copyOfRange(datas, maxLenBytes, maxLenBytes + maxTypeLen);//协议编号 两个字节
        //byte[] gateBytes = Arrays.copyOfRange(datas, maxLenBytes + maxTypeLen, maxLenBytes + maxTypeLen + maxGateLen);//gete内容，虽然没啥用 四个字节
        int maxLen = Shorts.fromByteArray(maxLengBytes);
        byte[] msgBodyBytes = Arrays.copyOfRange(datas, maxLen - 14, maxLen);//虽然不知道14个字节是什么鬼

        //根据type直接转换bean
        CommonMsgType msgType = CommonMsgType.valueOf(Ints.fromByteArray(msgIdBytes));
        if (msgType != null) {
            JSONObject jsonObject = JSONObject.parseObject(new String(msgBodyBytes));
            logger.info("msgBodyBytes = " + jsonObject.toJSONString());
            String msgId = "";
            if (jsonObject.getString("2002321") != null) {
                 msgId = jsonObject.getString("msgId");
            }
            return new Message<>(msgId, jsonObject, msgType.getMsgType(), msgType.getId());
        }
        return null;
    }
}
