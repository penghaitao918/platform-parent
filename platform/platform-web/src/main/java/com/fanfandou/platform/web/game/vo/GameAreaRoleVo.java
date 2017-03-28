package com.fanfandou.platform.web.game.vo;

import java.util.List;

/**
 * Created by wudi.
 * Descreption:区服以及所在区服角色信息.
 * Date:2017/2/22
 */
public class GameAreaRoleVo {

    private List<GameAreaVo> gameAreaVoList;

    private List<RoleVo> roleVoList;

    public List<GameAreaVo> getGameAreaVoList() {
        return gameAreaVoList;
    }

    public void setGameAreaVoList(List<GameAreaVo> gameAreaVoList) {
        this.gameAreaVoList = gameAreaVoList;
    }

    public List<RoleVo> getRoleVoList() {
        return roleVoList;
    }

    public void setRoleVoList(List<RoleVo> roleVoList) {
        this.roleVoList = roleVoList;
    }
}
