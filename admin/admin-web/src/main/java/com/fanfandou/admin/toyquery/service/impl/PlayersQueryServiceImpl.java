package com.fanfandou.admin.toyquery.service.impl;

import com.alibaba.fastjson.JSON;
import com.fanfandou.admin.api.exception.AdminException;
import com.fanfandou.admin.api.system.entity.DataDictionary;
import com.fanfandou.admin.api.system.service.DataDictionaryService;
import com.fanfandou.admin.system.dao.DiyFormMapper;
import com.fanfandou.admin.system.entity.DiyForm;
import com.fanfandou.admin.system.service.DiyFormService;
import com.fanfandou.admin.toyquery.service.PlayersQueryService;
import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.constant.DicKeyConstant;
import com.fanfandou.common.constant.PublicCachedKeyConstant;
import com.fanfandou.common.entity.resource.DicItem;
import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.service.cache.CacheService;
import com.fanfandou.platform.api.game.entity.GameArea;
import com.fanfandou.platform.api.game.entity.GameRole;
import com.fanfandou.platform.api.game.service.GameAreaService;
import com.fanfandou.platform.api.game.service.GameRoleService;
import com.fanfandou.platform.api.game.service.OperationDispatchService;
import com.fanfandou.platform.api.user.entity.UserAccount;
import com.fanfandou.platform.api.user.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhongliang on 2016/3/15.
 * Description service实现类
 */
@Service("playersQueryService")
public class PlayersQueryServiceImpl extends BaseLogger implements PlayersQueryService {
    @Autowired
    private OperationDispatchService operationDispatchService;
    @Autowired
    private DataDictionaryService dataDictionaryService;
    @Autowired
    private GameRoleService gameRoleService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private GameAreaService gameAreaService;

    @Override
    public Map<Integer, Object> sendGmCommand(int gameId, int areaId, int areaCode, String uniKey, String gmMsgType, Integer userId, Map<Integer, Object> gmMap) throws Exception {
        DicItem dicItem = dataDictionaryService.getDicItemByConstant(uniKey, gmMsgType);
        return operationDispatchService.sendGmCommand(GameCode.getById(gameId), areaId, areaCode, userId, dicItem, gmMap);
    }

    @Override
    public String getPlayersInfo(int gameId, int userId, int serverId, int serverIdKey, int accountKey, String roleName, int roleId, String gmMsgType) throws Exception {
        logger.info("start getPlayersInfo......................");
        GameRole gameRole = gameRoleService.getGameRole(gameId, serverId, userId, roleId, roleName);
        logger.info("start gameRole : " + gameRole.toString());
        Map<Integer, Object> gmMap = new HashMap<>();
        if (gameRole != null && gameRole.getUserId() != null) {
            //查询gameAreaCode
            GameArea gameArea = gameAreaService.getGameAreaById(serverId);
            int gameCode = Integer.parseInt(gameArea.getAreaCode());
            //强转
            long uid1 = gameRole.getUserId();
            int uid = (int) uid1;
            gmMap.put(accountKey, uid);
            gmMap.put(serverIdKey, gameCode);


            //匹配gm指令、国际化转换
            Map<Integer, Object> map = this.sendGmCommand(gameId, serverId, gameCode, DicKeyConstant.GM_DATA_ORDER, gmMsgType, uid, gmMap);//AreaCode代替areaId
            Map<String, String> maps = new HashMap<>();
            DataDictionary dataDictionary = dataDictionaryService.getValueByUniform(DicKeyConstant.GM_DATA_PDU, gameId, -1);
            if (dataDictionary != null) {
                List<DicItem> dicItems = JSON.parseArray(dataDictionary.getItemJson(), DicItem.class);
                for (Object key : map.keySet()) {   //只能遍历key
                    for (int i = 0; i < dicItems.size(); i++) {
                        int Key = (int) key;
                        int dicKey = Integer.parseInt(dicItems.get(i).getKey());
                        if (Key == dicKey) {
                            maps.put(dicItems.get(i).getValue(), map.get(Key).toString());
                        }
                    }

                }
            }
            logger.info("maps.size())" + maps.size());
            return JSON.toJSONString(maps);
        }
        return null;
    }

    @Override
    public String updatePlayerInfo(int gameId, int userId, int serverId, String roleName, int roleId, String gmMsgType, String itemData) throws Exception {
        GameRole gameRole = gameRoleService.getGameRole(gameId, serverId, userId, roleId, roleName);
        List<DicItem> dicItemUpdates = JSON.parseArray(itemData, DicItem.class);
        Map<Integer, Object> gmMap = new HashMap<>();
        if (gameRole != null && gameRole.getUserId() != null) {
            //查询gameAreaCode
            GameArea gameArea = gameAreaService.getGameAreaById(serverId);
            int gameCode = Integer.parseInt(gameArea.getAreaCode());
            //强转
            long uid1 = gameRole.getUserId();
            int uid = (int) uid1;
            DataDictionary dataDictionary = dataDictionaryService.getValueByUniform(DicKeyConstant.GM_DATA_PDU, gameId, -1);
            if (dataDictionary != null) {
                List<DicItem> dicItems = JSON.parseArray(dataDictionary.getItemJson(), DicItem.class);
                List<DicItem> dicItemUpdate = JSON.parseArray(itemData, DicItem.class);
                for (int i = 0; i < dicItems.size(); i++) {
                    for (int j = 0; j < dicItemUpdate.size(); j++) {
                        if (dicItems.get(i).getValue().equals(dicItemUpdate.get(j).getKey())) {
                            gmMap.put(Integer.parseInt(dicItems.get(i).getKey()), dicItemUpdate.get(j).getValue());
                        }
                    }
                }

            }
            //匹配gm指令、国际化转换
            Map<Integer, Object> map = this.sendGmCommand(gameId, serverId, gameCode, DicKeyConstant.GM_DATA_ORDER, gmMsgType, uid, gmMap);//AreaCode代替areaId

            return JSON.toJSONString(map);
        }
        return null;
    }


}
