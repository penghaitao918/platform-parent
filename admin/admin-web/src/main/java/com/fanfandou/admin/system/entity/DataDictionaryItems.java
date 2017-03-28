package com.fanfandou.admin.system.entity;

import java.io.Serializable;

/**
 * Description:数据字典key的实体类.
 */
public class DataDictionaryItems implements Serializable {
    private int id;
    //元素id
    private int itemId;
    //元素名称
    private String itemName;
    //对应key id
    private int dictionaryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(int dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    @Override
    public String toString() {
        return "DataDictionaryItems{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", dictionaryId=" + dictionaryId +
                '}';
    }
}
