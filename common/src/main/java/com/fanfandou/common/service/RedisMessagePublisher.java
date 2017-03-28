package com.fanfandou.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Description: redis 发布信息.
 * <p/>
 * Date: 2016-04-16 10:59.
 * author: Arvin.
 */
@Service("redisMessagePublisher")
public class RedisMessagePublisher {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 发布消息.
     * @param topic 发布的key，最好使用RedisMessageTopicConstant中的值
     * @param msg 消息value
     */
    public void sendMessage(String topic, Serializable msg) {
        redisTemplate.convertAndSend(topic, msg);
    }
}
