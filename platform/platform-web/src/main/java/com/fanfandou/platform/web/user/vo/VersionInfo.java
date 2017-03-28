package com.fanfandou.platform.web.user.vo;


import com.fanfandou.admin.api.operation.entity.GameUpdateConfig;
import com.fanfandou.admin.api.operation.entity.GameVersionCheck;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mike
 * Date: 13-9-25
 * Time: 下午5:17
 * To change this template use File | Settings | File Templates.
 */
public class VersionInfo implements Serializable {

    private boolean resourceNeedUpdate;
    private GameUpdateConfig appUpdateConfig;
    private List<GameUpdateConfig> resourceUpdateConfigs = new ArrayList<GameUpdateConfig>();


    public VersionInfo(GameVersionCheck gameVersionCheck) {
        resourceNeedUpdate = gameVersionCheck.isResourceNeedUpdate();

        appUpdateConfig = gameVersionCheck.getAppUpdateConfig();
        resourceUpdateConfigs = gameVersionCheck.getResourceUpdateConfigs();
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
}
