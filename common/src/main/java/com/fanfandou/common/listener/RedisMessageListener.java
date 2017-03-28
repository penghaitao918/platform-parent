package com.fanfandou.common.listener;

import com.fanfandou.common.constant.RedisMessageTopicConstant;
import com.fanfandou.common.entity.resource.SourceCodeFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * Description: redis 订阅信息监听.
 * <p/>
 * Date: 2016-04-15 20:31.
 * author: Arvin.
 */
public class RedisMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        if (message != null) {
            String topic = new String(message.getChannel());
            if (RedisMessageTopicConstant.MSG_TOPIC_SOURCE_GAME.equals(topic)) {
                SourceCodeFactory.initGameMap();
            } else if (RedisMessageTopicConstant.MSG_TOPIC_SOURCE_SITE.equals(topic)) {
                SourceCodeFactory.initSiteMap();
            }
        }
    }
}
