package com.fanfandou.admin.operation.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhongliang on 2016/7/13.
 * Description 表单元素
 */
public class FormObject implements Serializable {
   private String formCode;
    private String formName;
    private String formType;
    private String formRequired;
    private String formUnit;
    private String formValue;

    public String getFormValue() {
        return formValue;
    }

    public void setFormValue(String formValue) {
        this.formValue = formValue;
    }

    @Override
    public String toString() {
        return "FormObject{" +
                "formCode='" + formCode + '\'' +
                ", formName='" + formName + '\'' +
                ", formType='" + formType + '\'' +
                ", formRequired='" + formRequired + '\'' +
                ", formUnit='" + formUnit + '\'' +
                '}';
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

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public String getFormRequired() {
        return formRequired;
    }

    public void setFormRequired(String formRequired) {
        this.formRequired = formRequired;
    }

    public String getFormUnit() {
        return formUnit;
    }

    public void setFormUnit(String formUnit) {
        this.formUnit = formUnit;
    }
}
