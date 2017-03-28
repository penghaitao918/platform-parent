package com.fanfandou.admin.api.operation.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wangzhenwei on 2016/6/16.
 * Description 公告表
 */
public class Notice implements Serializable {
    //编号
    private int id;
    //游戏id
    private String gameIds;
    //渠道id
    private String siteIds;
    //区服id
    private String areaIds;
    //公告标题
    private String noticeTitle;
    //公告内容
    private String noticeContent;
    //公告类型 1进入游戏弹出公告 2游戏内滚动公告 3聊天窗口系统公告
    private NoticeEnum noticeType;
    //公告状态
    private int noticeState;
    //创建时间
    private Date createTime;
    //开始时间
    private Date beginTime;
    //结束时间
    private Date endTime;
    //发布次数
    private int publishCount;
    //发布间隔
    private int publishSpace;
    //发布人
    private String publisher;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getGameIds() {
        return gameIds;
    }

    public void setGameIds(String gameIds) {
        this.gameIds = gameIds;
    }

    public String getSiteIds() {
        return siteIds;
    }

    public void setSiteIds(String siteIds) {
        this.siteIds = siteIds;
    }

    public String getAreaIds() {
        return areaIds;
    }

    public void setAreaIds(String areaIds) {
        this.areaIds = areaIds;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public NoticeEnum getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(NoticeEnum noticeType) {
        this.noticeType = noticeType;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getPublishCount() {
        return publishCount;
    }

    public void setPublishCount(int publishCount) {
        this.publishCount = publishCount;
    }

    public int getPublishSpace() {
        return publishSpace;
    }

    public void setPublishSpace(int publishSpace) {
        this.publishSpace = publishSpace;
    }

    public int getNoticeState() {
        return noticeState;
    }

    public void setNoticeState(int noticeState) {
        this.noticeState = noticeState;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("gameIds", gameIds)
                .append("siteIds", siteIds)
                .append("areaIds", areaIds)
                .append("noticeTitle", noticeTitle)
                .append("noticeContent", noticeContent)
                .append("noticeType", noticeType)
                .append("beginTime", beginTime)
                .append("endTime", endTime)
                .append("publishCount", publishCount)
                .append("publishSpace", publishSpace)
                .toString();
    }
}
