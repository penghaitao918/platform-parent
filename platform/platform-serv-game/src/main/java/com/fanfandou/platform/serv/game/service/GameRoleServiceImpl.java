package com.fanfandou.platform.serv.game.service;

import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.sequence.TableSequenceGenerator;
import com.fanfandou.platform.api.constant.TableSequenceConstant;
import com.fanfandou.platform.api.game.entity.GameRole;
import com.fanfandou.platform.api.game.entity.GameRoleExample;
import com.fanfandou.platform.api.game.service.GameRoleService;
import com.fanfandou.platform.serv.game.dao.GameRoleMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Description: 游戏角色实现.
 * <p/>
 * Date: 2016-08-19 10:54.
 * author: Arvin.
 */
@Service("gameRoleService")
public class GameRoleServiceImpl implements GameRoleService {

    @Autowired
    private TableSequenceGenerator tableSequenceGenerator;

    @Autowired
    private GameRoleMapper gameRoleMapper;

    @Override
    public GameRole createRole(GameRole gameRole, GameCode gameCode) throws ServiceException {
        gameRole.setId(tableSequenceGenerator.generate(TableSequenceConstant.PLATFORM_GAME_ROLE));
        gameRole.setRoleId(tableSequenceGenerator.generate(TableSequenceConstant.PLATFORM_GAME_ROLE + gameCode.getCode()));
        gameRole.setCreateTime(new Date());
        gameRole.setLastLoginTime(new Date());
        gameRoleMapper.insert(gameRole);
        return  gameRole;
    }

    @Override
    public void updateRole(GameRole gameRole) {
        gameRoleMapper.updateByPrimaryKeySelective(gameRole);
    }

    @Override
    public GameRole getRoleById(GameCode gameCode, long roleId) {
        GameRoleExample gameRoleExample = new GameRoleExample();
        gameRoleExample.createCriteria()
                .andGameIdEqualTo(gameCode.getId())
                .andRoleIdEqualTo(roleId);
        List<GameRole> result = gameRoleMapper.selectByExample(gameRoleExample);
        if (CollectionUtils.isNotEmpty(result)) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public GameRole getRoleByUserId(GameCode gameCode, int areaId, long userId) {
        GameRoleExample gameRoleExample = new GameRoleExample();
        gameRoleExample.createCriteria()
                .andGameIdEqualTo(gameCode.getId())
                .andUserIdEqualTo(userId)
                .andAreaIdEqualTo(areaId);
        List<GameRole> result = gameRoleMapper.selectByExample(gameRoleExample);
        if (CollectionUtils.isNotEmpty(result)) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public GameRole getRoleByName(GameCode gameCode, int areaId, String roleName) {
        GameRoleExample gameRoleExample = new GameRoleExample();
        gameRoleExample.createCriteria()
                .andGameIdEqualTo(gameCode.getId())
                .andAreaIdEqualTo(areaId)
                .andRoleNameEqualTo(roleName);
        List<GameRole> result = gameRoleMapper.selectByExample(gameRoleExample);
        if (CollectionUtils.isNotEmpty(result)) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public List<GameRole> getRoleList(GameCode gameCode, long userId) {
        GameRoleExample gameRoleExample = new GameRoleExample();
        gameRoleExample.createCriteria().andGameIdEqualTo(gameCode.getId())
                .andUserIdEqualTo(userId);
        return gameRoleMapper.selectByExample(gameRoleExample);
    }

    @Override
    public GameRole getGameRole(int gameId, int areaId, int userId, int roleId, String roleName) {
        GameRole gameRole = new GameRole();
        if (roleId != -1) {
            gameRole = this.getRoleById(GameCode.getById(gameId), roleId);
        } else if (areaId != -1 && !StringUtils.isEmpty(roleName)) {
            gameRole = this.getRoleByName(GameCode.getById(gameId), areaId, roleName);
        } else if (areaId != -1 && userId != -1) {
            gameRole = this.getRoleByUserId(GameCode.getById(gameId), areaId, userId);
        }
        return gameRole;
    }
}
