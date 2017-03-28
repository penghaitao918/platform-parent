package com.fanfandou.common.log4j;

import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Created by wudi.
 * Descreption:日志过滤器.
 * Date:2017/3/14
 */
public class LogFilter extends Filter {

    /**
     * Do we return ACCEPT when a match occurs. Default is
     * <code>false</code>, so that later filters get run by default
     */
    boolean acceptOnMatch = false;

    int levelMin;
    int levelMax;


    @Override
    public int decide(LoggingEvent loggingEvent) {
        int inputLevel = loggingEvent.getLevel().toInt();
        if (inputLevel >= levelMin && inputLevel <= levelMax) {
            return Filter.ACCEPT;
        }
        return Filter.DENY;
    }

    /**
     * Get the value of the <code>LevelMax</code> option.
     */
    public int getLevelMax() {
        return levelMax;
    }

    /**
     * Get the value of the <code>LevelMin</code> option.
     */
    public int getLevelMin() {
        return levelMin;
    }

    /**
     * Get the value of the <code>AcceptOnMatch</code> option.
     */
    public boolean getAcceptOnMatch() {
        return acceptOnMatch;
    }

    /**
     * Set the <code>LevelMax</code> option.
     */
    public void setLevelMax(int levelMax) {
        this.levelMax = levelMax;
    }

    /**
     * Set the <code>LevelMin</code> option.
     */
    public void setLevelMin(int levelMin) {
        this.levelMin = levelMin;
    }

    /**
     * Set the <code>AcceptOnMatch</code> option.
     */
    public void setAcceptOnMatch(boolean acceptOnMatch) {
        this.acceptOnMatch = acceptOnMatch;
    }
}
