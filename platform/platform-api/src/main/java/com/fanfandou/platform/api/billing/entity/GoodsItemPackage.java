package com.fanfandou.platform.api.billing.entity;


import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wudi.
 * Descreption:充值物品奖励包.
 * Date:2016/6/2
 */
public class GoodsItemPackage implements Serializable {

    private String awardPackageId;

    private List<GoodsItem> goodsItems = new ArrayList<>();

    private int packageType;

    private int value;

    private String packageDesc;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAwardPackageId() {
        return awardPackageId;
    }

    public void setAwardPackageId(String awardPackageId) {
        this.awardPackageId = awardPackageId;
    }

    public List<GoodsItem> getGoodsItems() {
        return goodsItems;
    }

    public void setGoodsItems(List<GoodsItem> goodsItems) {
        this.goodsItems = goodsItems;
    }

    public int getPackageType() {
        return packageType;
    }

    public void setPackageType(int packageType) {
        this.packageType = packageType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getPackageDesc() {
        return packageDesc;
    }

    public void setPackageDesc(String packageDesc) {
        this.packageDesc = packageDesc;
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }
}
