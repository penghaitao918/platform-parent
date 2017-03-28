package com.fanfandou.platform.serv.game.connection;

import com.fanfandou.platform.api.game.exception.GameException;
import com.fanfandou.platform.api.game.entity.Message;

/**
 * Description: 与游戏通讯接口.
 * <p/>
 * Date: 2016-05-05 10:57.
 * author: Arvin.
 */
public interface GameConnector {

    void connect();

    void disconnect();

    void send(Message msg) throws GameException;

    Message sendSync(Message msg);

    void close();

    boolean isConnected();

}
