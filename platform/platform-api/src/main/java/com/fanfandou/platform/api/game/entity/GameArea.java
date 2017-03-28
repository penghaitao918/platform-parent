package com.fanfandou.platform.api.game.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fanfandou.common.entity.ActStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameArea implements Serializable {
    private Integer id;

    private Integer gameId;

    private AreaGroup areaGroup;

    private String siteIds;

    private String areaCode;

    private String areaName;
    //新服/火爆/推荐..
    private int areaTag;
    //空闲/爆满/流畅/繁忙..
    private int loadStatus;
    //正常/维护中

    private MaintenanceStatus maintenanceStatus = MaintenanceStatus.NORMAL;

    private ActStatus validStatus = ActStatus.ACTED;

    private Date availableTime;

    private String supportVersionMin;

    private String supportVersionMax;

    private Integer displayOrder;

    private String areaDesc;

    private Date createTime;

    private String clientEnterAddr;

    private String serverEnterAddr;

    private List<EnterAddress> clientEnterAddrList = new ArrayList<>();

    private EnterAddress serverEnterAddrObj = new EnterAddress();

    private String serverName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public AreaGroup getAreaGroup() {
        return areaGroup;
    }

    public void setAreaGroup(AreaGroup areaGroup) {
        this.areaGroup = areaGroup;
    }

    public String getSiteIds() {
        return siteIds;
    }

    public void setSiteIds(String siteIds) {
        this.siteIds = siteIds == null ? null : siteIds.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
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

    public MaintenanceStatus getMaintenanceStatus() {
        return maintenanceStatus;
    }

    public void setMaintenanceStatus(MaintenanceStatus maintenanceStatus) {
        this.maintenanceStatus = maintenanceStatus;
    }

    public ActStatus getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(ActStatus validStatus) {
        this.validStatus = validStatus;
    }

    public Date getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(Date availibleTime) {
        this.availableTime = availibleTime;
    }

    public String getSupportVersionMin() {
        return supportVersionMin;
    }

    public void setSupportVersionMin(String supportVersionMin) {
        this.supportVersionMin = supportVersionMin == null ? null : supportVersionMin.trim();
    }

    public String getSupportVersionMax() {
        return supportVersionMax;
    }

    public void setSupportVersionMax(String supportVersionMax) {
        this.supportVersionMax = supportVersionMax == null ? null : supportVersionMax.trim();
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getAreaDesc() {
        return areaDesc;
    }

    public void setAreaDesc(String areaDesc) {
        this.areaDesc = areaDesc == null ? null : areaDesc.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<EnterAddress> getClientEnterAddrList() {
        return JSON.parseArray(clientEnterAddr,EnterAddress.class);
    }

    public void setClientEnterAddrList(List<EnterAddress> clientEnterAddrList) {
        this.clientEnterAddr = JSON.toJSONString(clientEnterAddrList);
    }

    public EnterAddress getServerEnterAddrObj() {
        return JSONObject.toJavaObject(JSON.parseObject(serverEnterAddr),
                EnterAddress.class);
    }

   public void setServerEnterAddrObj(EnterAddress serverEnterAddObj) {
        this.serverEnterAddr = JSON.toJSONString(serverEnterAddObj);
    }


    public String getClientEnterAddr() {
        return clientEnterAddr;
    }

    public void setClientEnterAddr(String clientEnterAddr) {
        this.clientEnterAddr = clientEnterAddr;
    }

    public String getServerEnterAddr() {
        return serverEnterAddr;
    }

    public void setServerEnterAddr(String serverEnterAddr) {
        this.serverEnterAddr = serverEnterAddr;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}