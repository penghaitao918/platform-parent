package com.fanfandou.platform.api.game.entity;

import java.io.Serializable;
import java.util.Date;

public class GameToyBatch implements Serializable {
    //主键ID
    private Integer batchId;
    //批次号
    private String batchCode;
    //批次数量
    private Integer maxCode;
    //批次激活数量
    private Integer activeCode;
    //玩具类型
    private Integer toyType;

    private Date createTime;

    private Integer siteId;

    private Integer gameId;

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode == null ? null : batchCode.trim();
    }

    public Integer getMaxCode() {
        return maxCode;
    }

    public void setMaxCode(Integer maxCode) {
        this.maxCode = maxCode;
    }

    public Integer getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(Integer activeCode) {
        this.activeCode = activeCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getToyType() {
        return toyType;
    }

    public void setToyType(Integer toyType) {
        this.toyType = toyType;
    }
}