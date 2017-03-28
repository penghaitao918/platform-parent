package com.fanfandou.common.base;

import com.fanfandou.common.log4j.DefindLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: 通用log父类，要求所有类都继承此类,直接使用logger打印日志.
 * <p/>
 * Date: 2016-02-22 15:47.
 * author: Arvin.
 */
public class BaseLogger {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected DefindLogger playerLogger = DefindLogger.getLogger(getClass());
}
