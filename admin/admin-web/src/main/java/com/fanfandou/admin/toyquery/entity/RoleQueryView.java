package com.fanfandou.admin.toyquery.entity;

import com.fanfandou.platform.api.game.entity.GameRole;
import com.fanfandou.platform.api.user.entity.UserAccount;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhongliang on 2016/7/13.
 * Description 表单
 */
public class RoleQueryView implements Serializable{

    private GameRole gameRole;

    private List<UserAccount> userAccounts;

    public GameRole getGameRole() {
        return gameRole;
    }

    public void setGameRole(GameRole gameRole) {
        this.gameRole = gameRole;
    }

    public List<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(List<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }
}
