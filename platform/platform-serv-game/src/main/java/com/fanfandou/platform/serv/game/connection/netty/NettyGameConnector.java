package com.fanfandou.platform.serv.game.connection.netty;

import com.fanfandou.platform.api.game.exception.GameException;
import com.fanfandou.platform.serv.game.connection.AbstractGameConnector;
import com.fanfandou.platform.serv.game.connection.codec.ConnectionCodec;
import com.fanfandou.platform.api.game.entity.Message;
import com.fanfandou.platform.serv.game.operation.MessageHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * Description: 连接器.
 * <p/>
 * Date: 2016-04-27 15:36.
 * author: Arvin.
 */
public class NettyGameConnector extends AbstractGameConnector {
    private EventLoopGroup group;
    private Bootstrap bootstrap;
    private Channel channel;
    private ClientChannelHandler channelHandler;
    private static ChannelFutureListener channelFutureListener = null;

    public NettyGameConnector(final ConnectionCodec codec,
                              final MessageHandler messageHandler, InetSocketAddress address) {
        super(address);
        logger.info("NettyGameConnector.construct -> socketAdress={}", address);
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        channelHandler = new ClientChannelHandler(messageHandler, this);
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, getTimeout())
                .option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                NettyCodecAdatpter adatpter = new NettyCodecAdatpter(codec);
                socketChannel.pipeline().addLast("decoder", adatpter.getDecoder())
                        .addLast("encoder", adatpter.getEncoder())
                        .addLast("handler", channelHandler);
            }
        });
        channelFutureListener = new ChannelFutureListener() {
            public void operationComplete(ChannelFuture f) throws Exception {
                if (f.isSuccess()) {
                    logger.info("连接服务器成功");

                } else {
                    logger.info("连接服务器失败, 将在三秒后重连......");
                    connected = false;
                    //  3秒后重新连接
                    f.channel().eventLoop().schedule(new Runnable() {
                        @Override
                        public void run() {
                            doConnect();
                        }
                    }, 3, TimeUnit.SECONDS);
                }
            }
        };
    }

    public void doConnect() {

        if (isConnected()) {
            return;
        }
        try {
            ChannelFuture channelFuture = bootstrap.connect(super.address).sync();
            channelFuture.addListener(channelFutureListener);
            //获取channel，缓存channel，send操作都是通过channel，考虑异步返回和同步返回
            this.channel = channelFuture.channel();
            super.connected = true;

        } catch (InterruptedException e) {
            logger.error("NettyGameConnector->connect: ", e);
            close();
        }

    }

    @Override
    public void send(Message msg) throws GameException {
        logger.info("NettyGameConnector.send -> msg = {}", msg);
        if (!isConnected()) {
            doConnect();
            if (!isConnected()) {
                throw new GameException(GameException.GAME_OPERATOR_NOT_CONNECTED, "connector 未连接, 请稍后重试！");
            }
        }
        channel.writeAndFlush(msg);
    }

    @Override
    public Message sendSync(Message msg) {

        return null;
    }


    @Override
    public void close() {
        if (this.channel != null)
            this.channel.close();
        if (this.group != null)
            this.group.shutdownGracefully();
    }


}
