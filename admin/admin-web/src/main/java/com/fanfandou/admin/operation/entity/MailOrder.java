package com.fanfandou.admin.operation.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;


/**
 * Created by wangzhenwei on 2016/7/12.
 * Description 邮件订单表
 */
public class MailOrder implements Serializable {
    //id
    private int id;
    //游戏id
    private int gameId;
    //区服id
    private String areaIds;
    //发送值
    private String sendByValue;
    //发送类型 1个人邮件 2全服邮件
    private int sendType;
    //发送id类型 1平台id 2用户id 3用户名
    private int sendByType;
    //发送状态 0失败 1成功
    private int sendStatus;
    //邮件类型 1    2    3
    private int mailType;
    //邮件标题
    private String mailTitle;
    //邮件正文
    private String mailContent;
    //申请原因
    private String applyReason;
    //创建时间
    private Date createTime;
    //物品json
    private String itemJson;

    public void setItemJson(String itemJson) {
        this.itemJson = itemJson;
    }

    public String getItemJson() {
        return itemJson;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getAreaIds() {
        return areaIds;
    }

    public void setAreaIds(String areaIds) {
        this.areaIds = areaIds;
    }

    public String getSendByValue() {
        return sendByValue;
    }

    public void setSendByValue(String sendByValue) {
        this.sendByValue = sendByValue;
    }

    public int getSendType() {
        return sendType;
    }

    public void setSendType(int sendType) {
        this.sendType = sendType;
    }

    public int getSendByType() {
        return sendByType;
    }

    public void setSendByType(int sendByType) {
        this.sendByType = sendByType;
    }

    public int getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(int sendStatus) {
        this.sendStatus = sendStatus;
    }

    public int getMailType() {
        return mailType;
    }

    public void setMailType(int mailType) {
        this.mailType = mailType;
    }

    public String getMailTitle() {
        return mailTitle;
    }

    public void setMailTitle(String mailTitle) {
        this.mailTitle = mailTitle;
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("gameId", gameId)
                .append("areaIds", areaIds)
                .append("sendByValue", sendByValue)
                .append("sendType", sendType)
                .append("sendByType", sendByType)
                .append("sendStatus", sendStatus)
                .append("mailType", mailType)
                .append("mailTitle", mailTitle)
                .append("mailContent", mailContent)
                .append("applyReason", applyReason)
                .append("createTime", createTime)
                /*.append("items", items)*/
                .append("itemJson", itemJson)
                .toString();
    }
}
