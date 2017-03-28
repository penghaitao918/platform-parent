package com.fanfandou.admin.system.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by wangzhenwei on 2016/7/15.
 * Description 日期格式化
 */
@ControllerAdvice
public class DateFormatAdvice {
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {

        //自动转换日期类型的字段格式
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));*/

    }
}
