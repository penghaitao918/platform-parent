package com.fanfandou.admin.api.operation.entity;

import com.fanfandou.common.entity.ValidStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class GamePatch implements Serializable {
    private Long id;
    //游戏ID
    private Integer gameId;
    //渠道ID
    private Integer siteId;
    //补丁名称
    private String patchName;
    //补丁详情
    private String patchDesc;
    //补丁下载地址
    private String patchUrl;
    //补丁大小(kb)
    private int patchSize;
    //补丁版本号
    private int patchVersion;
    //设备
    private DeviceType deviceType = DeviceType.ANDROID;
    //是否开启白名单
    private ValidStatus whiteStatus = ValidStatus.VALID;
    //白名单内容
    private String whiteContent;
    //是否生效
    private ValidStatus validStatus = ValidStatus.VALID;

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

    public String getPatchName() {
        return patchName;
    }

    public void setPatchName(String patchName) {
        this.patchName = patchName == null ? null : patchName.trim();
    }

    public String getPatchDesc() {
        return patchDesc;
    }

    public void setPatchDesc(String patchDesc) {
        this.patchDesc = patchDesc == null ? null : patchDesc.trim();
    }

    public String getPatchUrl() {
        return patchUrl;
    }

    public void setPatchUrl(String patchUrl) {
        this.patchUrl = patchUrl == null ? null : patchUrl.trim();
    }

    public int getPatchSize() {
        return patchSize;
    }

    public void setPatchSize(int patchSize) {
        this.patchSize = patchSize;
    }

    public int getPatchVersion() {
        return patchVersion;
    }

    public void setPatchVersion(int patchVersion) {
        this.patchVersion = patchVersion;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public ValidStatus getWhiteStatus() {
        return whiteStatus;
    }

    public void setWhiteStatus(ValidStatus whiteStatus) {
        this.whiteStatus = whiteStatus;
    }

    public String getWhiteContent() {
        return whiteContent;
    }

    public void setWhiteContent(String whiteContent) {
        this.whiteContent = whiteContent == null ? null : whiteContent.trim();
    }

    public ValidStatus getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(ValidStatus validStatus) {
        this.validStatus = validStatus;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}