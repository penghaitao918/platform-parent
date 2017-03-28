package com.fanfandou.platform.api.game.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Description: 通讯消息体（发送和返回通用）.
 * <p/>
 * Date: 2016-05-25 13:59.
 * author: Arvin.
 */
public class Message<T> {

    private String msgId;
    private T msgBody;
    private int protocolType;
    private String encryptKey;
    private MessageType msgType;

    /**
     * constructor.
     * @param msgId msgId
     * @param msgBody msgBody
     * @param protocolType protocolType
     */
    public Message(String msgId, T msgBody, int protocolType) {
        this.msgId = msgId;
        this.msgBody = msgBody;
        this.protocolType = protocolType;
    }

    /**
     * constructor.
     * @param msgId msgId
     * @param msgBody msgBody
     * @param msgType msgType
     * @param protocolType protocolType
     */
    public Message(String msgId, T msgBody, MessageType msgType, int protocolType) {
        this.msgId = msgId;
        this.msgBody = msgBody;
        this.msgType = msgType;
        this.protocolType = protocolType;
    }

    /**
     * constructor.
     * @param msgId msgId
     * @param msgBody msgBody
     * @param msgType msgType
     * @param protocolType protocolType
     * @param encryptKey encryptKey
     */
    public Message(String msgId, T msgBody, MessageType msgType, int protocolType, String encryptKey) {
        this.msgId = msgId;
        this.msgBody = msgBody;
        this.msgType = msgType;
        this.protocolType = protocolType;
        this.encryptKey = encryptKey;
    }

    public String getMsgId() {
        return msgId;
    }

    public T getMsgBody() {
        return msgBody;
    }

    public int getProtocolType() {
        return protocolType;
    }

    public MessageType getMsgType() {
        return msgType;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public void setMsgBody(T msgBody) {
        this.msgBody = msgBody;
    }

    public void setProtocolType(int protocolType) {
        this.protocolType = protocolType;
    }

    public String getEncryptKey() {
        return encryptKey;
    }

    public void setEncryptKey(String encryptKey) {
        this.encryptKey = encryptKey;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
