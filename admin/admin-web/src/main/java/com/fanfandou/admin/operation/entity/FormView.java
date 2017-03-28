package com.fanfandou.admin.operation.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhongliang on 2016/7/13.
 * Description 表单
 */
public class FormView {
   private CustomForm customForm;
    private List<FormObject> formObjects;

    public CustomForm getCustomForm() {
        return customForm;
    }

    public void setCustomForm(CustomForm customForm) {
        this.customForm = customForm;
    }

    public List<FormObject> getFormObjects() {
        return formObjects;
    }

    public void setFormObjects(List<FormObject> formObjects) {
        this.formObjects = formObjects;
    }
}
