package com.fanfandou.common.log4j;

import org.apache.log4j.Level;

import java.io.Serializable;

/**
 * Created by wudi.
 * Descreption:自定义日志级别.
 * Date:2017/3/14
 */
public class DefindLevel extends Level implements Serializable {


    /**
     * Instantiate a Level object.
     */
    protected DefindLevel(int level, String levelStr, int syslogEquivalent) {
        super(level, levelStr, syslogEquivalent);
    }

}
