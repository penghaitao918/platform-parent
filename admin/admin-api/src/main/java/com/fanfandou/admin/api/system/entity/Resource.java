package com.fanfandou.admin.api.system.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by shengbo on 2016/3/23.
 * Description Resource 实体类.
 */
public class Resource implements Serializable{
    private int id;
    private String resCode;
    private String url;
    private Date createTime;
    private int available;

    private ResEnum resType = ResEnum.game;

    public ResEnum getResType() {
        return resType;
    }

    public void setResType(ResEnum resType) {
        this.resType = resType;
    }

    public String getResCode() {
        return resCode;
    }
    public void setResCode(String resCode) {
        this.resCode = resCode;
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


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
