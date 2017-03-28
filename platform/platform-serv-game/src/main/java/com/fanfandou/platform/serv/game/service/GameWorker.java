package com.fanfandou.platform.serv.game.service;

import com.fanfandou.platform.api.game.entity.Message;

/**
 * Description: 游戏消息处理类.
 * <p/>
 * Date: 2016-06-14 17:33.
 * author: Arvin.
 */
public interface GameWorker {

    /**
     * 将msg放入线程池中处理.
     * @param msg msg
     */
    void addWork(Message msg);
}
