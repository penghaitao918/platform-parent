package com.fanfandou.admin.api.system.entity;

import com.alibaba.fastjson.JSON;
import com.fanfandou.common.entity.resource.DicItem;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Description:数据字典key的实体类.
 */
public class DataDictionary implements Serializable {
    private int id;
    //字典名称
    private String dictionaryName;
    //元素json
    private String itemJson;
    //创建时间
    private Date createTime;

    private int gameId;

    private int siteId;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName;
    }

    public String getItemJson() {
        return itemJson;
    }

    public void setItemJson(String itemJson) {
        this.itemJson = itemJson;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date create_time) {
        this.createTime = create_time;
    }

    /*public List<DicItem> getItemJsonList() {
        if(itemJson!=null){
            List<DicItem> dicItems =  JSON.parseArray(this.itemJson, DicItem.class);
            return dicItems;
        }
      return null;
    }*/
    @Override
    public String toString() {
        return "DataDictionary{" +
                "id=" + id +
                ", dictionaryName='" + dictionaryName + '\'' +
                ", itemJson='" + itemJson + '\'' +
                ", createTime=" + createTime +
                ", gameId=" + gameId +
                ", siteId=" + siteId +
                '}';
    }
}
