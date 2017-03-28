package com.fanfandou.common.db;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * AOP拦截切换数据源.
 */
@Component
@Aspect
public class DataSourceChange {

    /**
     * aop切换数据源.
     *
     * @param join join.
     */
    @Before("execution(* com.fanfandou..dao..*.*(..)) && @annotation(com.fanfandou.common.db.DataSource)")
    public void change(JoinPoint join) {
        Annotation[] annotations = join.getThis().getClass().getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof DataSource) {
                DataSource data = (DataSource) annotation;
                DataSourceHolder.setDataSource(data.name());
                break;
            }
        }
    }
}
