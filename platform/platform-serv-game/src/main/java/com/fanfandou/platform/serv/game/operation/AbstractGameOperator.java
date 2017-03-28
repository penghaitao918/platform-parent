package com.fanfandou.platform.serv.game.operation;

import com.alibaba.fastjson.JSON;
import com.fanfandou.admin.api.operation.entity.Notice;
import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.util.SpringUtils;
import com.fanfandou.platform.api.billing.entity.GoodsItemPackage;
import com.fanfandou.platform.api.game.entity.GameArea;
import com.fanfandou.platform.api.game.entity.GameOperation;
import com.fanfandou.platform.api.game.entity.OperationType;
import com.fanfandou.platform.api.game.exception.GameException;
import com.fanfandou.platform.api.game.service.GameAreaService;
import com.fanfandou.platform.serv.game.connection.GameConnector;
import com.fanfandou.platform.serv.game.service.AbstractGameWorker;
import com.fanfandou.platform.serv.game.service.GameWorker;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.fanfandou.platform.api.game.entity.OperationType.GET_LOGIN_KEY;

/**
 * Description: operator抽象类.
 * <p/>
 * Date: 2016-05-10 10:47.
 * author: Arvin.
 */
public abstract class AbstractGameOperator extends BaseLogger implements GameOperator {

    private static final long PINT_PERIOD = 30L;

    protected GameCode gameCode;

    protected GameArea gameArea;

    protected GameConnector connector;

    protected MessageHandler messageHandler;

    protected GameWorker worker;

    @Override
    public void init(GameCode gameCode, int areaId) {
        logger.info("AbstractGameOperator.init-> operator init: areaCode={}", areaId);
        GameAreaService gameAreaService = (GameAreaService) SpringUtils.getBean("gameAreaService");

        try {
            gameArea = gameAreaService.getGameAreaById(areaId);
        } catch (ServiceException e) {
            logger.error("operator init failed: Can't get the game area!", e);
        }

        this.gameCode = gameCode;
        worker = getGameWorker();
        messageHandler = new MessageHandler(worker);
        //初始化connector
        initConnector();
        //心跳
        ScheduledExecutorService pingSchedule = Executors
                .newScheduledThreadPool(2);
        pingSchedule.scheduleAtFixedRate(new PingTask(), PINT_PERIOD, PINT_PERIOD, TimeUnit.SECONDS);
    }

    @Override
    public void dispatchOperation(GameOperation gameoperation) throws ServiceException {
        //将operation根据类型，分别拆分到其他接口上
        logger.info(gameoperation.toString());
        switch (gameoperation.getOptType()) {
            case GET_LOGIN_KEY:
                getLoginKey(gameoperation.getOptId(), gameoperation.getRoleId());
                break;
            case SEND_ITEM:
                sendItem(gameoperation.getOptId(), gameoperation.getUserId(),
                        JSON.parseObject(gameoperation.getOptContent(), GoodsItemPackage.class));
                break;
            case DELIVER_OF_PAY:
                deliverPayPackage(gameoperation.getOptId(), gameoperation.getUserId(),
                        JSON.parseObject(gameoperation.getOptContent(), GoodsItemPackage.class));
                break;

            default:
                throw new GameException(GameException.GAME_OPERATION_TYPE_NOTE_EXIST, "发送类型不存在");
        }
    }

    /**
     * 初始化链接.
     */
    protected abstract void initConnector();

    /**
     * 心跳.
     */
    protected abstract void ping();

    /**
     * 获取各游戏消息处理worker.
     *
     * @return GameWorker
     */
    protected abstract GameWorker getGameWorker();

    /**
     * 心跳task.
     */
    private class PingTask extends TimerTask {

        @Override
        public void run() {
            try {
                ping();
            } catch (Throwable e) {
                logger.error("PingTask.run:",e);
            }

        }
    }
}
