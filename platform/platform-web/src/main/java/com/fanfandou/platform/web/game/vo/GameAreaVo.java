package com.fanfandou.platform.web.game.vo;

import com.fanfandou.platform.api.game.entity.MaintenanceStatus;

import java.io.Serializable;

/**
 * Created by wudi.
 * Descreption:区服信息VO类.
 * Date:2016/6/13
 */
public class GameAreaVo implements Serializable {

    private Integer gameId;

    private String areaCode;

    private String areaName;

    private int displayOrder;
    //新服/火爆/推荐..
    private int areaTag;
    //空闲/爆满/流畅/繁忙..
    private int loadStatus;
    //正常/维护中
    private int maintenanceStatus = MaintenanceStatus.NORMAL.getId();

    private String availableTime;

    private String areaDesc;

    private String clientEnterAddr;


    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getAreaTag() {
        return areaTag;
    }

    public void setAreaTag(int areaTag) {
        this.areaTag = areaTag;
    }

    public int getLoadStatus() {
        return loadStatus;
    }

    public void setLoadStatus(int loadStatus) {
        this.loadStatus = loadStatus;
    }

    public int getMaintenanceStatus() {
        return maintenanceStatus;
    }

    public void setMaintenanceStatus(int maintenanceStatus) {
        this.maintenanceStatus = maintenanceStatus;
    }

    public String getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(String availableTime) {
        this.availableTime = availableTime;
    }

    public String getAreaDesc() {
        return areaDesc;
    }

    public void setAreaDesc(String areaDesc) {
        this.areaDesc = areaDesc;
    }

    public String getClientEnterAddr() {
        return clientEnterAddr;
    }

    public void setClientEnterAddr(String clientEnterAddr) {
        this.clientEnterAddr = clientEnterAddr;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }
}
