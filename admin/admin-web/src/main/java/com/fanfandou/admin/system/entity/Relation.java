package com.fanfandou.admin.system.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by wangzhenwei on 2016/3/22.
 * Description 关联表
 */
public class Relation implements Serializable {
    private int id;
    private int id1;
    private int id2;
    /*type为1时，id1 = userId，id2 = roleId
      type为2时，id1 = roleId，id2 = permissionId
      type为3时，id1 = userId，id2 = permissionId
     */
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id1", id1)
                .append("id2", id2)
                .append("type", type)
                .toString();
    }
}
