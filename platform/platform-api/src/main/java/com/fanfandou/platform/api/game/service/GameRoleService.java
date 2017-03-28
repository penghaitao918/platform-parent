package com.fanfandou.platform.api.game.service;

import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.game.entity.GameRole;

import java.util.Date;
import java.util.List;

/**
 * Description: 提供游戏角色相关操作.
 * <p/>
 * Date: 2016-08-10 14:59.
 * author: Arvin.
 */
public interface GameRoleService {

    GameRole createRole(GameRole gameRole, GameCode gameCode) throws ServiceException;

    void updateRole(GameRole gameRole);

    GameRole getRoleById(GameCode gameCode, long roleId);

    GameRole getRoleByUserId(GameCode gameCode, int areaId, long userId);

    GameRole getRoleByName(GameCode gameCode, int areaId, String roleName);

    List<GameRole> getRoleList(GameCode gameCode, long userId);

    GameRole getGameRole(int gameId, int areaId, int userId, int roleId, String roleName);
}
