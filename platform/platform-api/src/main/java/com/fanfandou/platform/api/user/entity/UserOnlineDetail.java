package com.fanfandou.platform.api.user.entity;

import java.util.Date;

public class UserOnlineDetail {
    private Long id;

    private Long userId;

    private Integer idcardId;

    private int onlineSeconds;

    private int offlineSeconds;

    private Boolean isOnline;

    private Date createTime;

    private Date onlineTime;

    private Date offlineTime;

    private Date lastUpdateTime;

    private Integer gameId;

    private Integer areaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getIdcardId() {
        return idcardId;
    }

    public void setIdcardId(Integer idcardId) {
        this.idcardId = idcardId;
    }

    public int getOnlineSeconds() {
        return onlineSeconds;
    }

    public void setOnlineSeconds(int onlineSeconds) {
        this.onlineSeconds = onlineSeconds;
    }

    public int getOfflineSeconds() {
        return offlineSeconds;
    }

    public void setOfflineSeconds(int offlineSeconds) {
        this.offlineSeconds = offlineSeconds;
    }

    public Boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Boolean isOnline) {
        this.isOnline = isOnline;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Date getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(Date offlineTime) {
        this.offlineTime = offlineTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }
}