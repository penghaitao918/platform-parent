package com.fanfandou.platform.web.game.vo;

import java.io.Serializable;

/**
 * Created by wudi.
 * Descreption:玩家角色VO类.
 * Date:2016/9/20
 */
public class RoleVo implements Serializable {

    private long roleId;

    private String roleName;

    private String roleHead;

    private int roleLevel;

    private String areaCode;

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName != null ? roleName : "";
    }

    public String getRoleHead() {

        return roleHead;
    }

    public void setRoleHead(String roleHead) {
        this.roleHead = roleHead != null ? roleHead : "";
    }

    public int getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(int roleLevel) {
        this.roleLevel = roleLevel;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}
