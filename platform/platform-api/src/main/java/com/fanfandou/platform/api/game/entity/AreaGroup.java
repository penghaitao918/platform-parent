package com.fanfandou.platform.api.game.entity;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AreaGroup implements Serializable {
    private Integer id;

    private Integer gameId;

    private String areaGroupCode;

    private String areaGroupName;

    private String areaGroupDesc;

    private Integer displayOrder;

    private String clientEnterAddr;

    private String serverEnterAddr;

    private EnterAddress serverEnterAddrObj;

    private Date createTime;

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

    public String getAreaGroupCode() {
        return areaGroupCode;
    }

    public void setAreaGroupCode(String areaGroupCode) {
        this.areaGroupCode = areaGroupCode == null ? null : areaGroupCode.trim();
    }

    public String getAreaGroupName() {
        return areaGroupName;
    }

    public void setAreaGroupName(String areaGroupName) {
        this.areaGroupName = areaGroupName == null ? null : areaGroupName.trim();
    }

    public String getAreaGroupDesc() {
        return areaGroupDesc;
    }

    public void setAreaGroupDesc(String areaGroupDesc) {
        this.areaGroupDesc = areaGroupDesc == null ? null : areaGroupDesc.trim();
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
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
        this.clientEnterAddr = clientEnterAddr == null ? null : clientEnterAddr.trim();
    }

    public String getServerEnterAddr() {
        return serverEnterAddr;
    }

    public void setServerEnterAddr(String serverEnterAddr) {
        this.serverEnterAddr = serverEnterAddr == null ? null : serverEnterAddr.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public String tosString() {
        return "id = " + this.id + ",areaGroupCode = " + areaGroupCode + ",areaGroupName = " + areaGroupName
                + ",clientEnterAddr = " + clientEnterAddr + ",gameId = " + gameId;
    }
}