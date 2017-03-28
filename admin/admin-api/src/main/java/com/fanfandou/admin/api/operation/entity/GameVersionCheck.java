package com.fanfandou.admin.api.operation.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wudi.
 * Descreption:补丁版本判断.
 * Date:2017/2/23
 */
public class GameVersionCheck implements Serializable {

    /**
     * 资源是否要更新.
     */
    private boolean resourceNeedUpdate;

    /**
     * 游戏资源版本号.
     */
    private int resourceVerCode;

    /**
     * 游戏资源补丁名称.
     */
    private String resourceVerName;

    /**
     * 描述.
     */
    private String resourceVerDesc;

    /**
     * 补丁更新配置.
     */
    private GameUpdateConfig appUpdateConfig;

    /**
     * 多配置.
     */
    private List<GameUpdateConfig> resourceUpdateConfigs = new ArrayList<GameUpdateConfig>();

    public boolean isResourceNeedUpdate() {
        return resourceNeedUpdate;
    }

    public void setResourceNeedUpdate(boolean resourceNeedUpdate) {
        this.resourceNeedUpdate = resourceNeedUpdate;
    }

    public int getResourceVerCode() {
        return resourceVerCode;
    }

    public void setResourceVerCode(int resourceVerCode) {
        this.resourceVerCode = resourceVerCode;
    }

    public String getResourceVerName() {
        return resourceVerName;
    }

    public void setResourceVerName(String resourceVerName) {
        this.resourceVerName = resourceVerName;
    }

    public String getResourceVerDesc() {
        return resourceVerDesc;
    }

    public void setResourceVerDesc(String resourceVerDesc) {
        this.resourceVerDesc = resourceVerDesc;
    }

    public GameUpdateConfig getAppUpdateConfig() {
        return appUpdateConfig;
    }

    public void setAppUpdateConfig(GameUpdateConfig appUpdateConfig) {
        this.appUpdateConfig = appUpdateConfig;
    }

    public List<GameUpdateConfig> getResourceUpdateConfigs() {
        return resourceUpdateConfigs;
    }

    public void setResourceUpdateConfigs(List<GameUpdateConfig> resourceUpdateConfigs) {
        this.resourceUpdateConfigs = resourceUpdateConfigs;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
