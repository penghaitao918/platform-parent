package com.fanfandou.admin.system.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:数据字典key的实体类.
 */
public class DiyForm implements Serializable {
    private int id;
    //表单code
    private String formCode;
    //表单名称
    private String formName;
    //游戏id
    private int gameId;
    //物品类型
    private String itemType;
    //表单元素json
    private String formJson;
    //创建时间
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFormCode() {
        return formCode;
    }

    public void setFormCode(String formCode) {
        this.formCode = formCode;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getFormJson() {
        return formJson;
    }

    public void setFormJson(String formJson) {
        this.formJson = formJson;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "DiyForm{" +
                "id=" + id +
                ", formCode='" + formCode + '\'' +
                ", formName='" + formName + '\'' +
                ", gameId=" + gameId +
                ", itemType='" + itemType + '\'' +
                ", formJson='" + formJson + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
