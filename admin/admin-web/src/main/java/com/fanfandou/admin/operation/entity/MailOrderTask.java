package com.fanfandou.admin.operation.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wangzhenwei on 2016/8/2.
 * Description 邮件订单task.
 */
public class MailOrderTask implements Serializable {

    //id
    private int id;
    //游戏id
    private int gameId;
    //区服id
    private String areaIds;
    //角色Id
    private String roleIds;
    //发送状态 0失败 1成功
    private int sendStatus;
    //失败原因
    private String failedReason;
    //邮件标题
    private String mailTitle;
    //邮件正文
    private String mailContent;
    //物品json
    private String itemJson;
    //创建时间
    private Date createTime;
    //邮件订单id
    private int mailOrderId;

    private int sendCount;

    public int getSendCount() {
        return sendCount;
    }

    public void setSendCount(int sendCount) {
        this.sendCount = sendCount;
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

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public int getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(int sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getFailedReason() {
        return failedReason;
    }

    public void setFailedReason(String failedReason) {
        this.failedReason = failedReason;
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

    public String getItemJson() {
        return itemJson;
    }

    public void setItemJson(String itemJson) {
        this.itemJson = itemJson;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getMailOrderId() {
        return mailOrderId;
    }

    public void setMailOrderId(int mailOrderId) {
        this.mailOrderId = mailOrderId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("gameId", gameId)
                .append("areaIds", areaIds)
                .append("roleIds", roleIds)
                .append("sendStatus", sendStatus)
                .append("failedReason", failedReason)
                .append("mailTitle", mailTitle)
                .append("mailContent", mailContent)
                .append("itemJson", itemJson)
                .append("createTime", createTime)
                .append("mailOrderId", mailOrderId)
                .toString();
    }
}
