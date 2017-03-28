package com.fanfandou.admin.system.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wangzhenwei on 2016/3/15.
 * Description 角色实体类
 */
public class Role implements Serializable {

    private int id;
    //角色名字
    private String role;
    //创建时间
    private Date createTime;
    //描述
    private String description;
    //0可用1不可用
    private int available;
    //备注
    private String memo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createtime) {
        this.createTime = createtime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("role", role)
                .append("createtime", createTime)
                .append("description", description)
                .append("available", available)
                .append("memo", memo)
                .toString();
    }
}
