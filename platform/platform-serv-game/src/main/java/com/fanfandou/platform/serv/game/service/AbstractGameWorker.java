package com.fanfandou.platform.serv.game.service;

import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.util.SpringUtils;
import com.fanfandou.platform.api.game.entity.Message;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description: game worker 抽象类.
 * <p/>
 * Date: 2016-06-14 17:56.
 * author: Arvin.
 */
public abstract class AbstractGameWorker extends BaseLogger implements GameWorker {

    protected int areaId;
    protected GameCode gameCode;

    private ThreadPoolTaskExecutor gameWorkerExecutor;

    public AbstractGameWorker() {
        this.gameWorkerExecutor = (ThreadPoolTaskExecutor) SpringUtils.getBean("gameWorkerExecutor");
    }

    @Override
    public void addWork(Message msg) {
        Runnable workThread = getWorkThread(msg);
        if (workThread != null) {
            gameWorkerExecutor.execute(workThread);
        }
    }

    protected abstract Runnable getWorkThread(Message msg);

}
