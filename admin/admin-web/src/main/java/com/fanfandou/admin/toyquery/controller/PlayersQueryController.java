package com.fanfandou.admin.toyquery.controller;

import com.fanfandou.admin.api.system.service.DataDictionaryService;
import com.fanfandou.admin.toyquery.service.PlayersQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * author zhongliang.
 * Description:玩家基本信息查询操作.
 */
@RestController
@RequestMapping(value = "/toyQuery")
public class PlayersQueryController {

    @Autowired
    PlayersQueryService playersQueryService;
    @Autowired
    DataDictionaryService dataDictionaryService;

    /**
     * 跳转到玩家基础查询页面
     *
     * @return list页面
     */
    @RequestMapping(value = "/playersQueryInit")
    public ModelAndView toPlayersList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/toyquery/PlayersQuery");

        return mav;
    }

    /**
     * 跳转到玩家基础查询页面
     *
     * @return list页面
     */
    @RequestMapping(value = "/heroQueryInit")
    public ModelAndView toHeroList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/toyquery/HeroQuery");

        return mav;
    }

    /**
     * 跳转到玩家基础查询页面
     *
     * @return list页面
     */
    @RequestMapping(value = "/goodsQueryInit")
    public ModelAndView toGoodsList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/toyquery/GoodsQuery");

        return mav;
    }

    /**
     * 发送GM指令，玩家查询
     */
    @ResponseBody
    @RequestMapping(value = "/getPlayersInfo")
    public String getPlayersInfo(int gameId, int userId, int serverId, int serverIdKey, int accountKey, String roleName, int roleId, String gmMsgType) throws Exception {
        if (roleName == null) {
            roleName = "";
        }
        return this.playersQueryService.getPlayersInfo(gameId, userId, serverId, serverIdKey, accountKey, roleName, roleId, gmMsgType);
    }

    /**
     * 发送GM指令，玩家基础修改
     */
    @ResponseBody
    @RequestMapping(value = "/updatePlayersInfo")
    public String updatePlayersInfo(int gameId, int userId, int serverId, String roleName, int roleId, String gmMsgType, String itemData) throws Exception {
        if (roleName == null) {
            roleName = "";
        }
        return this.playersQueryService.updatePlayerInfo(gameId, userId, serverId, roleName, roleId, gmMsgType, itemData);
    }

    /**
     * 发送GM指令，玩家英雄查询
     */
    @ResponseBody
    @RequestMapping(value = "/getHeroInfo")
    public String getHeroInfo(int gameId, int userId, int serverId, int serverIdKey, int accountKey, String roleName, int roleId, String gmMsgType) throws Exception {
        if (roleName == null) {
            roleName = "";
        }
        this.playersQueryService.getPlayersInfo(gameId, userId, serverId, serverIdKey, accountKey, roleName, roleId, gmMsgType);
        return "";
    }

    /**
     * 发送GM指令，玩家英雄修改
     */
    @ResponseBody
    @RequestMapping(value = "/setHeroInfo")
    public String getHeroInfo(int gameId, int userId, int serverId, String roleName, int roleId, String gmMsgType, String itemData) throws Exception {
        if (roleName == null) {
            roleName = "";
        }
        this.playersQueryService.updatePlayerInfo(gameId, userId, serverId, roleName, roleId, gmMsgType, itemData);
        return "";
    }

    /**
     * 发送GM指令，玩家物品查询
     */
    @ResponseBody
    @RequestMapping(value = "/getGoodsInfo")
    public String getGoodsInfo(int gameId, int userId, int serverId, int serverIdKey, int accountKey, String roleName, int roleId, String gmMsgType) throws Exception {
        if (roleName == null) {
            roleName = "";
        }
        this.playersQueryService.getPlayersInfo(gameId, userId, serverId, serverIdKey, accountKey, roleName, roleId, gmMsgType);
        return "[{\"GetItemResItemId\":1,\"GetItemResItemCount\":23},{\"GetItemResItemId\":2,\"GetItemResItemCount\":12},{\"GetItemResItemId\":3,\"GetItemResItemCount\":17}]";
    }


    /**
     * 发送GM指令，玩家物品修改
     */
    @ResponseBody
    @RequestMapping(value = "/setGoodsInfo")
    public String setGoodsInfo(int gameId, int userId, int serverId, String roleName, int roleId, String gmMsgType, String itemData) throws Exception {
        if (roleName == null) {
            roleName = "";
        }
        return this.playersQueryService.updatePlayerInfo(gameId, userId, serverId, roleName, roleId, gmMsgType, itemData);
    }

    /**
     * 发送GM指令，玩家邮件查询
     */
    @ResponseBody
    @RequestMapping(value = "/getMailInfo")
    public String getMailInfo(int gameId, int userId, int serverId, int serverIdKey, int accountKey, String roleName, int roleId, String gmMsgType) throws Exception {
        if (roleName == null) {
            roleName = "";
        }
        this.playersQueryService.getPlayersInfo(gameId, userId, serverId, serverIdKey, accountKey, roleName, roleId, gmMsgType);
        return "";
    }


    /**
     * 发送GM指令，玩家邮件修改
     */
    @ResponseBody
    @RequestMapping(value = "/setMailInfo")
    public String setMailInfo(int gameId, int userId, int serverId, String roleName, int roleId, String gmMsgType, String itemData) throws Exception {
        if (roleName == null) {
            roleName = "";
        }
        return this.playersQueryService.updatePlayerInfo(gameId, userId, serverId, roleName, roleId, gmMsgType, itemData);
    }

    /**
     * 发送GM指令，玩家符文查询
     */
    @ResponseBody
    @RequestMapping(value = "/getRuneInfo")
    public String getRuneInfo(int gameId, int userId, int serverId, int serverIdKey, int accountKey, String roleName, int roleId, String gmMsgType) throws Exception {
        if (roleName == null) {
            roleName = "";
        }
        this.playersQueryService.getPlayersInfo(gameId, userId, serverId, serverIdKey, accountKey, roleName, roleId, gmMsgType);
        return "";
    }

    /**
     * 发送GM指令，玩家符文修改
     */
    @ResponseBody
    @RequestMapping(value = "/setRuneInfo")
    public String setRuneInfo(int gameId, int userId, int serverId, String roleName, int roleId, String gmMsgType, String itemData) throws Exception {
        if (roleName == null) {
            roleName = "";
        }
        return this.playersQueryService.updatePlayerInfo(gameId, userId, serverId, roleName, roleId, gmMsgType, itemData);
    }

    /**
     * 发送GM指令，玩家好友查询
     */
    @ResponseBody
    @RequestMapping(value = "/getFriendInfo")
    public String getFriendInfo(int gameId, int userId, int serverId, int serverIdKey, int accountKey, String roleName, int roleId, String gmMsgType) throws Exception {
        if (roleName == null) {
            roleName = "";
        }
        this.playersQueryService.getPlayersInfo(gameId, userId, serverId, serverIdKey, accountKey, roleName, roleId, gmMsgType);
        return "";
    }

    /**
     * 发送GM指令，玩家好友修改
     */
    @ResponseBody
    @RequestMapping(value = "/setFriendInfo")
    public String setFriendInfo(int gameId, int userId, int serverId, String roleName, int roleId, String gmMsgType, String itemData) throws Exception {
        if (roleName == null) {
            roleName = "";
        }
        return this.playersQueryService.updatePlayerInfo(gameId, userId, serverId, roleName, roleId, gmMsgType, itemData);
    }
}
