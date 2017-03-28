package com.fanfandou.platform.api.activity.entity;

import java.io.Serializable;

/**
 * Created by wudi.
 * Descreption:礼包码使用限制规则.
 * Date:2017/3/8
 */
public class PromoteUsingRule implements Serializable {

    //礼包码领取限制规则
    private LimitedType limitedType = LimitedType.NOLIMIT;
    //礼包码限制使用次数
    private int limitedTimes;

    //规则描述
    private String ruleDesc;

    public String getRuleDesc() {
        return ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc;
    }

    public LimitedType getLimitedType() {
        return limitedType;
    }

    public void setLimitedType(LimitedType limitedType) {
        this.limitedType = limitedType;
    }

    public int getLimitedTimes() {
        return limitedTimes;
    }

    public void setLimitedTimes(int limitedTimes) {
        this.limitedTimes = limitedTimes;
    }
}
