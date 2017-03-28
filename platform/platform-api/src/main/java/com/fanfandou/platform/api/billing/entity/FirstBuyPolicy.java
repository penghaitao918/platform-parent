package com.fanfandou.platform.api.billing.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by wudi.
 * Descreption:首充策略.
 * Date:2016/5/19
 */
public class FirstBuyPolicy implements Serializable {
    //首充策略ID
    private String firstBuyId;

    //是否是首充
    private boolean isFirstPay = true;

    //首充奖励操作，比如*
    private String firstBuyOperation;

    //首充奖励数量
    private int operateCount;

    public String getFirstBuyId() {
        return firstBuyId;
    }

    public void setFirstBuyId(String firstBuyId) {
        this.firstBuyId = firstBuyId;
    }

    public String getFirstBuyOperation() {
        return firstBuyOperation;
    }

    public void setFirstBuyOperation(String firstBuyOperation) {
        this.firstBuyOperation = firstBuyOperation;
    }

    public int getOperateCount() {
        return operateCount;
    }

    public void setOperateCount(int operateCount) {
        this.operateCount = operateCount;
    }

    public boolean isFirstPay() {
        return isFirstPay;
    }

    public void setFirstPay(boolean firstPay) {
        isFirstPay = firstPay;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
