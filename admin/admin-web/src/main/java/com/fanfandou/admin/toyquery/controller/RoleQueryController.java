package com.fanfandou.admin.toyquery.controller;

import com.fanfandou.admin.toyquery.entity.RoleQueryView;
import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.entity.resource.SiteCode;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.game.entity.GameRole;
import com.fanfandou.platform.api.game.service.GameRoleService;
import com.fanfandou.platform.api.user.entity.UserAccount;
import com.fanfandou.platform.api.user.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


/**
 * author zhongliang.
 * Description:角色查询操作.
 */
@RestController
@RequestMapping(value = "/roleQuery")
public class RoleQueryController extends BaseLogger {

    @Autowired
    private GameRoleService gameRoleService;

    @Autowired
    private AccountService accountService;


    /**
     * 跳转到article List页面
     *
     * @return list页面
     */
    @RequestMapping(value = "/roleQueryInit")
    public ModelAndView toArticleList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/toyquery/RoleQuery");
        logger.debug("toyquery >>>> " + "进来了");
        return mav;
    }

    /**
     * 查询玩家角色信息.
     */
    @ResponseBody
    @RequestMapping(value = "/getRoleInfo")
    public RoleQueryView getByRoleInfo(int gameId, int areaId, int userId, int roleId, String roleName) throws ServiceException {
        GameRole gameRole = gameRoleService.getGameRole(gameId, areaId, userId, roleId, roleName);
        List<UserAccount> userAccounts = new ArrayList<>();
        if (gameRole != null) {
            userAccounts = accountService.getAccountsByUid(gameRole.getUserId());
        }
        RoleQueryView roleQueryView = new RoleQueryView();
        roleQueryView.setGameRole(gameRole);
        roleQueryView.setUserAccounts(userAccounts);
        return roleQueryView;
    }


}
