package com.fanfandou.platform.api.activity.entity;

import com.fanfandou.common.entity.ValidStatus;

import java.io.Serializable;
import java.util.Date;

public class PromoteCodeBatch implements Serializable {
    private Integer batchId;

    private Integer siteId;

    private String channel;

    private Integer gameId;

    private Integer gameAreaId;
    //批次名称
    private String batchName;

    //礼包描述（问候语）
    private String awardGreet;
    //礼包物品包ID
    private Integer packageId;
    //礼包领取类型(暂时忽略)
    private Integer promoteBatchType;
    //暂时忽略
    private String awardRole;
    //礼包数量
    private Integer amount;
    //礼包已使用数量
    private Integer usedAmount;
    //礼包领取规则(实体类 → json)
    private String usingRule;
    //礼包可使用的开始时间
    private Date availableStartDate;
    //礼包可使用的结束时间
    private Date availableEndDate;
    //礼包生成状态(暂时忽略)
    private int generateStatus;
    //批次描述
    private String batchDesc;

    private ValidStatus validStatus = ValidStatus.INVALID;
    //礼包生成的角色ID
    private Integer createAdminUserId;
    //创建时间
    private Date createDate;
    //创建IP
    private String createIp;

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getGameAreaId() {
        return gameAreaId;
    }

    public void setGameAreaId(Integer gameAreaId) {
        this.gameAreaId = gameAreaId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName == null ? null : batchName.trim();
    }

    public String getAwardGreet() {
        return awardGreet;
    }

    public void setAwardGreet(String awardGreet) {
        this.awardGreet = awardGreet == null ? null : awardGreet.trim();
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public Integer getPromoteBatchType() {
        return promoteBatchType;
    }

    public void setPromoteBatchType(Integer promoteBatchType) {
        this.promoteBatchType = promoteBatchType;
    }

    public String getAwardRole() {
        return awardRole;
    }

    public void setAwardRole(String awardRole) {
        this.awardRole = awardRole == null ? null : awardRole.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getUsedAmount() {
        return usedAmount;
    }

    public void setUsedAmount(Integer usedAmount) {
        this.usedAmount = usedAmount;
    }

    public String getUsingRule() {
        return usingRule;
    }

    public void setUsingRule(String usingRule) {
        this.usingRule = usingRule == null ? null : usingRule.trim();
    }

    public Date getAvailableStartDate() {
        return availableStartDate;
    }

    public void setAvailableStartDate(Date availableStartDate) {
        this.availableStartDate = availableStartDate;
    }

    public Date getAvailableEndDate() {
        return availableEndDate;
    }

    public void setAvailableEndDate(Date availableEndDate) {
        this.availableEndDate = availableEndDate;
    }

    public int getGenerateStatus() {
        return generateStatus;
    }

    public void setGenerateStatus(int generateStatus) {
        this.generateStatus = generateStatus;
    }

    public String getBatchDesc() {
        return batchDesc;
    }

    public void setBatchDesc(String batchDesc) {
        this.batchDesc = batchDesc == null ? null : batchDesc.trim();
    }

    public ValidStatus getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(ValidStatus validStatus) {
        this.validStatus = validStatus;
    }

    public Integer getCreateAdminUserId() {
        return createAdminUserId;
    }

    public void setCreateAdminUserId(Integer createAdminUserId) {
        this.createAdminUserId = createAdminUserId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp == null ? null : createIp.trim();
    }
}