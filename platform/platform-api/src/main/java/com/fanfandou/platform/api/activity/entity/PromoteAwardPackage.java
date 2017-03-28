package com.fanfandou.platform.api.activity.entity;

import com.fanfandou.common.entity.ValidStatus;

import java.io.Serializable;
import java.util.Date;

public class PromoteAwardPackage implements Serializable {
    private Integer packageId;
    //礼包名字
    private String packageName;

    private Integer gameId;
    //奖品类型
    private PromoteCategory promoteCategory = PromoteCategory.GIFT;
    //礼包内容
    private String itemsPackage;
    //问候语句
    private String packageGreet;
    //奖品描述
    private String packageDesc;

    private ValidStatus validStatus = ValidStatus.VALID;
    //礼包生成者ID
    private String createAdminUserId;
    //创建时间
    private Date createDate;
    //创建IP
    private String createIp;

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName == null ? null : packageName.trim();
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public PromoteCategory getPromoteCategory() {
        return promoteCategory;
    }

    public void setPromoteCategory(PromoteCategory promoteCategory) {
        this.promoteCategory = promoteCategory;
    }

    public String getItemsPackage() {
        return itemsPackage;
    }

    public void setItemsPackage(String itemsPackage) {
        this.itemsPackage = itemsPackage == null ? null : itemsPackage.trim();
    }

    public String getPackageGreet() {
        return packageGreet;
    }

    public void setPackageGreet(String packageGreet) {
        this.packageGreet = packageGreet == null ? null : packageGreet.trim();
    }

    public String getPackageDesc() {
        return packageDesc;
    }

    public void setPackageDesc(String packageDesc) {
        this.packageDesc = packageDesc == null ? null : packageDesc.trim();
    }

    public ValidStatus getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(ValidStatus validStatus) {
        this.validStatus = validStatus;
    }

    public String getCreateAdminUserId() {
        return createAdminUserId;
    }

    public void setCreateAdminUserId(String createAdminUserId) {
        this.createAdminUserId = createAdminUserId == null ? null : createAdminUserId.trim();
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