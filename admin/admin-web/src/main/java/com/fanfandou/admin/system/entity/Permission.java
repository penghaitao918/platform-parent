package com.fanfandou.admin.system.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wangzhenwei on 2016/3/23.
 * Description 权限表
 */
public class Permission implements Serializable {
    private int id;
    //资源id
    private int resourceId;
    //操作id
    private int actionId;
    //权限类型
    private String type;
    //权限值（资源+操作）
    private String permissionValue;
    //创建时间
    private Date createTime;
    //可用
    private int available;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPermissionValue() {
        return permissionValue;
    }

    public void setPermissionValue(String permissionValue) {
        this.permissionValue = permissionValue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
                .append("resourceId", resourceId)
                .append("actionId", actionId)
                .append("type", type)
                .append("permissionValue", permissionValue)
                .append("createTime", createTime)
                .append("available", available)
                .toString();
    }
}
