package com.fanfandou.common.db;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据源标识.
 *
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
	/**
	 * baidu.
	 * @return name.
     */
	String name() default DataSource.master;

	/**
	 * 默认数据源.
	 */
	String master = "write";
}
