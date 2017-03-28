package com.fanfandou.platform.web.activity.vo;

import java.util.Date;

/**
 * Created by wudi.
 * Descreption:游戏活动VO.
 * Date:2016/11/23
 */
public class GameActivityVo {

    private int id;

    private int activityId;

    private String activityContent;

    private String startTime;

    private String endTime;

    private int validStatus;

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(int validStatus) {
        this.validStatus = validStatus;
    }
}
