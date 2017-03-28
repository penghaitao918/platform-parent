package com.fanfandou.platform.api.activity.entity;

import com.fanfandou.common.entity.ActStatus;
import com.fanfandou.common.entity.ValidStatus;

import java.io.Serializable;
import java.util.Date;

public class PromoteCode implements Serializable {
    private Long codeId;
    //礼包码
    private String promoteCode;
    //批次ID
    private Integer batchId;

    private ValidStatus validStatus;
    //创建时间
    private Date createDate;
    //分发的渠道
    private Integer drawSiteId;
    //分发的渠道
    private String drawChannel;
    //分发的游戏
    private Integer drawGameId;
    //分发的区服
    private Integer drawGameAreaId;
    //礼包使用状态
    private ActStatus drawStatus = ActStatus.UNACT;
    //礼包使用的用户ID
    private Integer drawUserId;
    //礼包使用的角色ID
    private String drawRoleId;
    //礼包使用时间
    private Date drawDate;
    //礼包使用IP
    private String drawIp;
    //发送状态
    private ActStatus deliverStatus = ActStatus.UNACT;
    //发送时间
    private Date deliverDate;

    public Long getCodeId() {
        return codeId;
    }

    public void setCodeId(Long codeId) {
        this.codeId = codeId;
    }

    public String getPromoteCode() {
        return promoteCode;
    }

    public void setPromoteCode(String promoteCode) {
        this.promoteCode = promoteCode == null ? null : promoteCode.trim();
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public ValidStatus getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(ValidStatus validStatus) {
        this.validStatus = validStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getDrawSiteId() {
        return drawSiteId;
    }

    public void setDrawSiteId(Integer drawSiteId) {
        this.drawSiteId = drawSiteId;
    }

    public String getDrawChannel() {
        return drawChannel;
    }

    public void setDrawChannel(String drawChannel) {
        this.drawChannel = drawChannel == null ? null : drawChannel.trim();
    }

    public Integer getDrawGameId() {
        return drawGameId;
    }

    public void setDrawGameId(Integer drawGameId) {
        this.drawGameId = drawGameId;
    }

    public Integer getDrawGameAreaId() {
        return drawGameAreaId;
    }

    public void setDrawGameAreaId(Integer drawGameAreaId) {
        this.drawGameAreaId = drawGameAreaId;
    }

    public ActStatus getDrawStatus() {
        return drawStatus;
    }

    public void setDrawStatus(ActStatus drawStatus) {
        this.drawStatus = drawStatus;
    }

    public Integer getDrawUserId() {
        return drawUserId;
    }

    public void setDrawUserId(Integer drawUserId) {
        this.drawUserId = drawUserId;
    }

    public String getDrawRoleId() {
        return drawRoleId;
    }

    public void setDrawRoleId(String drawRoleId) {
        this.drawRoleId = drawRoleId == null ? null : drawRoleId.trim();
    }

    public Date getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(Date drawDate) {
        this.drawDate = drawDate;
    }

    public String getDrawIp() {
        return drawIp;
    }

    public void setDrawIp(String drawIp) {
        this.drawIp = drawIp == null ? null : drawIp.trim();
    }

    public ActStatus getDeliverStatus() {
        return deliverStatus;
    }

    public void setDeliverStatus(ActStatus deliverStatus) {
        this.deliverStatus = deliverStatus;
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }
}