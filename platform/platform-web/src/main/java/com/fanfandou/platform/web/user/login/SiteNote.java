package com.fanfandou.platform.web.user.login;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wudi.
 * Descreption:渠道注解.
 * Date:2016/4/5
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SiteNote {

    /**
     * 渠道名称
     */
    String name() default SiteNote.master;

    String master = "unknown";

}
