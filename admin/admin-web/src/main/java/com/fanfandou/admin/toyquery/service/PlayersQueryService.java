package com.fanfandou.admin.toyquery.service;

import com.fanfandou.admin.system.entity.DiyForm;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongliang on 2016/3/15.
 * Description 自定义表单service接口
 */
public interface PlayersQueryService {


    /**
     * 发送gm指令
     *
     * @param gameId    游戏id
     * @param areaCode    游戏code
     * @param uniKey    数据字典key
     * @param gmMsgType gm指令
     * @param userId    用户id
     * @return
     * @throws Exception
     */
    Map<Integer, Object> sendGmCommand(int gameId, int areaId,int areaCode, String uniKey, String gmMsgType, Integer userId, Map<Integer, Object> gmMap) throws Exception;

    /**
     *
     * @param gameId 游戏id
     * @param userId 用户id
     * @param serverId 区服id
     * @param serverIdKey 区服id key
     * @param accountKey 账号id key
     * @param roleName 角色名称
     * @param roleId 角色id
     * @param gmMsgType gm指令
     * @return
     * @throws Exception
     */
    String getPlayersInfo(int gameId, int userId, int serverId, int serverIdKey, int accountKey, String roleName, int roleId, String gmMsgType) throws Exception;


    String updatePlayerInfo(int gameId, int userId, int serverId, String roleName, int roleId, String gmMsgType,String itemData) throws Exception;
}
