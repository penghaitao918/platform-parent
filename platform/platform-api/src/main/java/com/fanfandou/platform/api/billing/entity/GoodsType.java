package com.fanfandou.platform.api.billing.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class GoodsType implements Serializable {
    private Integer goodsTypeId;

    private String code;

    public Integer getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(Integer goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


}