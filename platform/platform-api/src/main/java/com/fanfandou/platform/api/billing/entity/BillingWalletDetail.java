package com.fanfandou.platform.api.billing.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

public class BillingWalletDetail implements Serializable {
    private Long walletDetailid;

    private Long walletId;

    private String billingOrderId;

    private Currency currency;

    private Integer amount;

    private Integer currencyAmount;

    private String detailDesc;

    private WalletDetailType detailType = WalletDetailType.INFLOW;

    private Date createTime;

    public Long getWalletDetailid() {
        return walletDetailid;
    }

    public void setWalletDetailid(Long walletDetailid) {
        this.walletDetailid = walletDetailid;
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public String getBillingOrderId() {
        return billingOrderId;
    }

    public void setBillingOrderId(String billingOrderId) {
        this.billingOrderId = billingOrderId == null ? null : billingOrderId.trim();
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getCurrencyAmount() {
        return currencyAmount;
    }

    public void setCurrencyAmount(Integer currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc == null ? null : detailDesc.trim();
    }

    public WalletDetailType getDetailType() {
        return detailType;
    }

    public void setDetailType(WalletDetailType detailType) {
        this.detailType = detailType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}