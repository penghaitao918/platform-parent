package com.fanfandou.admin.system.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description:菜单的实体类.
 */
public class Menu implements Serializable {
    private int id;
    //菜单名称
    private String menuName;
    //是否父级菜单
    private int isParent;
    //路径
    private String url;
    //父菜单id
    private int parentId;
    //创建时间
    private Date createTime;
    //可用
    private int available;
    //菜单顺序
    private int showOrder;
    //图标
    private String menuIcon;

    public int getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(int showOrder) {
        this.showOrder = showOrder;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    private List<Menu> childrenList = new ArrayList<Menu>();

    public List<Menu> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<Menu> childrenList) {
        this.childrenList = childrenList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getIsParent() {
        return isParent;
    }

    public void setIsParent(int isParent) {
        this.isParent = isParent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
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
                .append("menuName", menuName)
                .append("isParent", isParent)
                .append("url", url)
                .append("parentId", parentId)
                .append("createTime", createTime)
                .append("available", available)
                .append("showOrder", showOrder)
                .append("menuIcon", menuIcon)
                .toString();
    }
}
