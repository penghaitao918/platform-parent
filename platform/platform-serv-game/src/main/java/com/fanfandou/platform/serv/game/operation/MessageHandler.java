package com.fanfandou.platform.serv.game.operation;

import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.platform.api.game.entity.Message;
import com.fanfandou.platform.api.game.entity.MessageType;
import com.fanfandou.platform.serv.game.service.GameWorker;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Description: 处理游戏返回的message.
 * <p/>
 * Date: 2016-05-25 20:42.
 * author: Arvin.
 */
public class MessageHandler extends BaseLogger {
    private final ConcurrentHashMap<String, BlockingQueue<Message>> syncResponse = new ConcurrentHashMap<String, BlockingQueue<Message>>();
    private GameWorker worker;

    public MessageHandler(GameWorker worker) {
        this.worker = worker;
    }

    public Message getResponse(String messageId) {
        Message response = null;
        syncResponse.putIfAbsent(messageId, new LinkedBlockingQueue<Message>(1));
        try {
            response = syncResponse.get(messageId).poll(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.error("queue interrupted", e);
        } finally {
            syncResponse.remove(messageId);
        }
        return response;
    }


    public void addResponse(Message message) {
        logger.info("MessageHandler.addResponse -> the received msg : {}", message);
        //此处将msg分类，交给不同的业务处理类
        if (MessageType.SYNC_PROCESS.equals(message.getMsgType())) {
            syncResponse.putIfAbsent(message.getMsgId(), new LinkedBlockingQueue<Message>(1));
            syncResponse.get(message.getMsgId()).add(message);
        } else if (MessageType.ASYNC_PROCESS.equals(message.getMsgType())) {
            worker.addWork(message);
        } else {
            logger.info("MessageHandler.addResponse -> Do not process this message, msgId={}", message.getMsgId());
        }
    }
}
