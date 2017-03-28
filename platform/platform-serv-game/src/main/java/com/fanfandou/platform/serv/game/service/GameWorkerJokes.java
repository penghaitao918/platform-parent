package com.fanfandou.platform.serv.game.service;

import com.fanfandou.platform.api.game.entity.Message;

/**
 * Created by wudi.
 * Descreption:十冷游戏worker.
 * Date:2017/3/8
 */
public class GameWorkerJokes extends AbstractGameWorker {

    @Override
    protected Runnable getWorkThread(Message msg) {
        return null;
    }
}
