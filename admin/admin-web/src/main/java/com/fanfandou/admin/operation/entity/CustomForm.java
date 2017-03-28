package com.fanfandou.admin.operation.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongliang on 2016/7/13.
 * Description 表单
 */
public class CustomForm implements Serializable {
   private String customFormCode;
    private String customFormName;


    @Override
    public String toString() {
        return "CustomForm{" +
                "customFormCode='" + customFormCode + '\'' +
                ", customFormName='" + customFormName + '\'' +
                '}';
    }

    public String getCustomFormCode() {
        return customFormCode;
    }

    public void setCustomFormCode(String customFormCode) {
        this.customFormCode = customFormCode;
    }

    public String getCustomFormName() {
        return customFormName;
    }

    public void setCustomFormName(String customFormName) {
        this.customFormName = customFormName;
    }
}
