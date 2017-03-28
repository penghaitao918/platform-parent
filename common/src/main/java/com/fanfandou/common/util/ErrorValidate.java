package com.fanfandou.common.util;

import java.lang.annotation.*;

/**
 * Created by wudi.
 * Descreption:自定义注解
 * Date:2016/3/15
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ErrorValidate {

}
