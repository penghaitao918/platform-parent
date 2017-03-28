package com.fanfandou.platform.serv.game.connection.netty;

import com.fanfandou.platform.serv.game.connection.codec.ConnectionCodec;
import com.fanfandou.platform.api.game.entity.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.List;

/**
 * Description: 协议适配器.
 * <p/>
 * Date: 2016-05-19 11:03.
 * author: Arvin.
 */
public class NettyCodecAdatpter {

    private final ChannelOutboundHandler encoder = new InternalEncoder();

    private final ChannelInboundHandler decoder = new InternalDecoder();

    private ConnectionCodec<Message> codec;

    //初始化
    public NettyCodecAdatpter(ConnectionCodec<Message> codec) {
        this.codec = codec;
    }

    private class InternalEncoder extends MessageToByteEncoder<Message> {
        @Override
        protected void encode(ChannelHandlerContext channelHandlerContext,
                              Message msg, ByteBuf byteBuf) throws Exception {
            byteBuf.writeBytes(codec.encode(msg));
        }
    }

    private class InternalDecoder extends ByteToMessageDecoder {


        @Override
        protected void decode(ChannelHandlerContext channelHandlerContext,
                              ByteBuf byteBuf, List<Object> list) throws Exception {
            byte[] datas = new byte[byteBuf.readableBytes()];
            byteBuf.readBytes(datas);
            Message message = codec.decode(datas);
            if(message != null) {
                list.add(message);
            }
        }
    }

    public ChannelOutboundHandler getEncoder() {
        return encoder;
    }

    public ChannelInboundHandler getDecoder() {
        return decoder;
    }
}
