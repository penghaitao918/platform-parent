package com.fanfandou.admin.operation.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wangzhenwei on 2016/6/30.
 * Description 游戏物品类型表
 */
public class ItemType implements Serializable {

    private int id;
    private int itemCode;
    private String itemName;
    private int itemType;
    //描述
    private String itemExtend;
    private int gameId;
    private Date createTime;
    private int available;

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemExtend() {
        return itemExtend;
    }

    public void setItemExtend(String itemExtend) {
        this.itemExtend = itemExtend;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("itemCode", itemCode)
                .append("itemName", itemName)
                .append("itemType", itemType)
                .append("itemExtend", itemExtend)
                .append("gameId", gameId)
                .append("createTime", createTime)
                .append("available", available)
                .toString();
    }
}
