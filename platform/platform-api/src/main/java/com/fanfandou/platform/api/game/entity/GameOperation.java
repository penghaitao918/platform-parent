package com.fanfandou.platform.api.game.entity;

import com.fanfandou.common.entity.ActStatus;
import com.fanfandou.common.entity.ValidStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

public class GameOperation {
    /**
     * id.
     */
    private Long optId;

    /**
     * 游戏id.
     */
    private Integer gameId;

    /**
     * 区服id.
     */
    private Integer areaId;

    /**
     * 用户id.
     */
    private Long userId;

    /**
     * 角色id.
     */
    private Long roleId;

    private List<String> roleIds;

    /**
     * 角色名称.
     */
    private String roleName;

    /**
     * 操作类型，见OperationType.
     */
    private OperationType optType = OperationType.SEND_ITEM;

    /**
     * 操作具体内容，指令集、物品集合等.
     */
    private String optContent;

    /**
     * 操作描述.
     */
    private String optDesc;

    /**
     * 是否有效.
     */
    private ValidStatus validStatus = ValidStatus.VALID;

    /**
     * 创建时间.
     */
    private Date createTime;

    /**
     * 发送次数.
     */
    private Integer tryTimes = 0;

    /**
     * 最后一次发送的时间.
     */
    private Date lastTryTime;

    /**
     * 发送状态.
     */
    private ActStatus deliverStatus = ActStatus.UNACT;

    /**
     * 发送成功的时间.
     */
    private Date deliverTime;

    /**
     * 发送区服名称.
     */
    private String deliverServerName;

    public GameOperation() {
    }

    public GameOperation(boolean isClear) {
        if (isClear) {
            optType = null;
            validStatus = null;
            deliverStatus = null;
            tryTimes = null;
        }
    }

    public Long getOptId() {
        return optId;
    }

    public void setOptId(Long optId) {
        this.optId = optId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public OperationType getOptType() {
        return optType;
    }

    public void setOptType(OperationType optType) {
        this.optType = optType;
    }

    public String getOptContent() {
        return optContent;
    }

    public void setOptContent(String optContent) {
        this.optContent = optContent == null ? null : optContent.trim();
    }

    public String getOptDesc() {
        return optDesc;
    }

    public void setOptDesc(String optDesc) {
        this.optDesc = optDesc == null ? null : optDesc.trim();
    }

    public ValidStatus getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(ValidStatus validStatus) {
        this.validStatus = validStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getTryTimes() {
        return tryTimes;
    }

    public void setTryTimes(Integer tryTimes) {
        this.tryTimes = tryTimes;
    }

    public Date getLastTryTime() {
        return lastTryTime;
    }

    public void setLastTryTime(Date lastTryTime) {
        this.lastTryTime = lastTryTime;
    }

    public ActStatus getDeliverStatus() {
        return deliverStatus;
    }

    public void setDeliverStatus(ActStatus deliverStatus) {
        this.deliverStatus = deliverStatus;
    }

    public Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    public String getDeliverServerName() {
        return deliverServerName;
    }

    public void setDeliverServerName(String deliverServerName) {
        this.deliverServerName = deliverServerName == null ? null : deliverServerName.trim();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}