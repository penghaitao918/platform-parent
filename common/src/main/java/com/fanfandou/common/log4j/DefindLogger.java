package com.fanfandou.common.log4j;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.net.SyslogAppender;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Created by wudi.
 * Descreption:自定义文件日志.
 * Date:2017/3/14
 */
public class DefindLogger {

    private final static int priority = 40010;

    private final Logger logger;

    private static final String FQCN;

    // 以下为自定义的日志级别， 自定义等级 40100 > 40000(error)>()
    public static final Level PLAYER_INFO_LEVEL = new DefindLevel(priority, "PLAYER_INFO", SyslogAppender.LOG_LOCAL0);

    static {
        FQCN = DefindLevel.class.getName();
    }

    private DefindLogger(Class<?> clazz) {
        logger = org.apache.log4j.Logger.getLogger(clazz);
    }

    private DefindLogger() {
        logger = org.apache.log4j.Logger.getRootLogger();
    }

    public static DefindLogger getLogger(Class<?> clazz) {
        return new DefindLogger(clazz);
    }

    public static DefindLogger getRootLogger() {
        return new DefindLogger();
    }

    /**
     * 自定义存储玩家信息接口.
     */
    public void  playerInfo(Object message) {
        forcedLog(logger, PLAYER_INFO_LEVEL, message);
    }

    /**
     * 自定义存储玩家信息接口.
     */
    public void playerInfo(Object message, Throwable t) {
        forcedLog(logger, PLAYER_INFO_LEVEL, message, t);
    }

    private static void forcedLog(org.apache.log4j.Logger logger, Level level, Object message) {
        logger.callAppenders(new LoggingEvent(FQCN, logger, level, message, null));
    }

    private static void forcedLog(org.apache.log4j.Logger logger, Level level, Object message, Throwable t) {
        logger.callAppenders(new LoggingEvent(FQCN, logger, level, message, t));
    }
}
