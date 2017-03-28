package com.fanfandou.platform.api.game.entity;

import java.io.Serializable;
import java.util.Date;

public class GameToy implements Serializable {
    private Long id;

    //序列号
    private Long toyCode;
    //游戏id
    private Integer gameId;
    //渠道id
    private Integer siteId;
    //玩具类型
    private Integer toyType;
    //生产时间
    private Date prodTime;
    //玩具批次号
    private Integer batchId;
    //玩具唯一识别码
    private Long toyGuid;

    private Date createTime;
    //绑定用户id
    private Long bindUid;
    //绑定游戏
    private Integer bindGame;
    //绑定渠道
    private Integer bindSite;
    //绑定区服
    private Integer bindArea;
    //绑定状态（序列号）
    private int bindStatus = ToyBindStatus.UNBIND.getId();
    //绑定时间
    private Date bindTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getBindUid() {
        return bindUid;
    }

    public void setBindUid(Long bindUid) {
        this.bindUid = bindUid;
    }

    public Integer getBindGame() {
        return bindGame;
    }

    public void setBindGame(Integer bindGame) {
        this.bindGame = bindGame;
    }

    public Integer getBindSite() {
        return bindSite;
    }

    public void setBindSite(Integer bindSite) {
        this.bindSite = bindSite;
    }

    public Integer getBindArea() {
        return bindArea;
    }

    public void setBindArea(Integer bindArea) {
        this.bindArea = bindArea;
    }

    public int getBindStatus() {
        return bindStatus;
    }

    public void setBindStatus(int bindStatus) {
        this.bindStatus = bindStatus;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    public Long getToyCode() {
        return toyCode;
    }

    public void setToyCode(Long toyCode) {
        this.toyCode = toyCode;
    }

    public Integer getToyType() {
        return toyType;
    }

    public void setToyType(Integer toyType) {
        this.toyType = toyType;
    }

    public Date getProdTime() {
        return prodTime;
    }

    public void setProdTime(Date prodTime) {
        this.prodTime = prodTime;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public Long getToyGuid() {
        return toyGuid;
    }

    public void setToyGuid(Long toyGuid) {
        this.toyGuid = toyGuid;
    }
}