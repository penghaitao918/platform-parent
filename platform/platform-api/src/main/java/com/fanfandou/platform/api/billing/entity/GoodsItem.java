package com.fanfandou.platform.api.billing.entity;

import com.fanfandou.common.entity.resource.DicItem;

import java.io.Serializable;

/**
 * Created by wudi.
 * Descreption:物品奖励包条目.
 * Date:2016/6/2
 */
public class GoodsItem implements Serializable {
    //物品条目ID
    private int itemId;
    //物品条目类型
    private DicItem itemType;
    //物品条目名称
    private String itemName;
    //物品条目值
    private int value;
    //拓展字段
    private String extraData;


    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public DicItem getItemType() {
        return itemType;
    }

    public void setItemType(DicItem itemType) {
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getExtraData() {
        return extraData;
    }

    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }
}
