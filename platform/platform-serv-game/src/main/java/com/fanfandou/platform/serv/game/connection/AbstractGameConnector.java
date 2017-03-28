package com.fanfandou.platform.serv.game.connection;

import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.platform.serv.game.connection.codec.ConnectionCodec;

import java.net.InetSocketAddress;
import java.net.InterfaceAddress;

/**
 * Description: 与游戏通讯的默认实现.
 * <p/>
 * Date: 2016-05-05 11:00.
 * author: Arvin.
 */
public abstract class AbstractGameConnector extends BaseLogger implements GameConnector, Resetable {

    protected InetSocketAddress address;
    protected boolean connected;
    private static final int TIME_OUT = 10000;

    public AbstractGameConnector(InetSocketAddress address) {
        this.address = address;
    }

    @Override
    public void reset() {
        //先断掉连接，再连上
    }

    @Override
    public void connect() {
        setConnected(false);
        doConnect();
        //是否需要一个初始化？？？
        //判断参数正确
        //判断是否有旧连接
        //正常连接
        //注意每一步打log
    }

    @Override
    public void disconnect() {
        //注意区分哪些资源需要回收
    }

    protected int getTimeout() {
        //TODO 以后从配置中读
        return TIME_OUT;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    protected abstract void doConnect();

}
