package com.fanfandou.platform.api.game.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Description: 消息类型（主要与消息处理方式相关）.
 * <p/>
 * Date: 2016-06-14 15:41.
 * author: Arvin.
 */
public enum MessageType {
    //不处理的消息
    NOT_PROCESS(-1, "not.process"),
    //同步处理的消息
    SYNC_PROCESS(1, "sync.process"),
    //异步处理的消洗
    ASYNC_PROCESS(2, "async.process");

    private int id;
    private String code;

    MessageType(int id, String code) {
        this.id = id;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    /**
     * 根据id获取MessageType.
     * @param id id.
     * @return MessageType.
     */
    public static MessageType valueOf(int id) {
        MessageType[] types = values();
        for (MessageType type : types) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
