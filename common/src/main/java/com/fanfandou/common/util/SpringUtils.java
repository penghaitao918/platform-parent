
package com.fanfandou.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring 工具类.
 *
 * @author Arvin.
 *         date 2011-10-13
 */

@Component("springUtils")
public class SpringUtils implements ApplicationContextAware {


    private static ApplicationContext applicationContext;

    public SpringUtils() {

    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    /**
     * 根据beanName 获取 spring bean.
     *
     * @param beanName beanName
     * @return Object
     */
    public static Object getBean(String beanName) {
        if (beanName == null) {
            return null;
        }
        return applicationContext.getBean(beanName);
    }

    /**
     * 根据bean type 获取springBean.
     *
     * @param clazz class
     * @return obj
     */
    public static Object getBeanByType(Class clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 获取 Spring applicationContext.
     *
     * @return context
     */
    public static ApplicationContext getContext() {
        return applicationContext;
    }


}
