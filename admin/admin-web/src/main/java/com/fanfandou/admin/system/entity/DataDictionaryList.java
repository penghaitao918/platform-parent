package com.fanfandou.admin.system.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description:数据字典key的实体类.
 */
public class DataDictionaryList implements Serializable {
    private int id;
    //key名称
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DataDictionaryList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
