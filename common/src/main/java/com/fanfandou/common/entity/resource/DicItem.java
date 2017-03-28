package com.fanfandou.common.entity.resource;


import java.io.Serializable;

/**
 * Description: 数据字典中的内容单元，一般key-value形式.
 * <p/>
 * Date: 2016-04-07 13:55.
 * author: zhongliang.
 */
public class DicItem implements Serializable {
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
