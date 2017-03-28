package com.fanfandou.admin.server.operation.serviceImpl;

import com.fanfandou.admin.api.operation.entity.DeviceType;
import com.fanfandou.admin.api.operation.entity.GamePatch;
import com.fanfandou.admin.api.operation.entity.GamePatchExample;
import com.fanfandou.admin.api.operation.entity.GameUpdateConfig;
import com.fanfandou.admin.api.operation.entity.GameVersionCheck;
import com.fanfandou.admin.api.operation.service.PatchService;
import com.fanfandou.admin.server.operation.dao.GamePatchMapper;
import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.constant.PublicCachedKeyConstant;
import com.fanfandou.common.entity.ValidStatus;
import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.sequence.TableSequenceGenerator;
import com.fanfandou.common.service.cache.CacheService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wudi.
 * Descreption:补丁相关方法实现.
 * Date:2017/2/23
 */
@Service("patchService")
public class PatchServiceImpl extends BaseLogger implements PatchService {

    @Autowired
    private GamePatchMapper gamePatchMapper;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private TableSequenceGenerator tableSequenceGenerator;

    @Override
    public GameVersionCheck checkGameVersion(DeviceType deviceType, GameCode gameCode, int siteId,
                                             int resourceVersion, String ip) throws ServiceException {
        GameVersionCheck gameVersionCheck = new GameVersionCheck();
        //先区分游戏和渠道，以及白名单IP
        //1，根据游戏和渠道，设备号获取所有补丁信息
        List<GamePatch> gamePatches = getPatchList(deviceType, gameCode, siteId, resourceVersion);
        if (!CollectionUtils.isEmpty(gamePatches)) {
            //2,过滤白名单.
            for (GamePatch gamePatch : gamePatches) {
                boolean returnValue = false;
                if (gamePatch.getWhiteStatus().equals(ValidStatus.VALID)) {
                    if (StringUtils.isEmpty(gamePatch.getWhiteContent()) || gamePatch.getWhiteContent().contains(ip)) {
                        returnValue = true;
                    }
                } else {
                    returnValue = true;
                }
                if (returnValue) {
                    GameUpdateConfig gameUpdateConfig = new GameUpdateConfig();
                    gameVersionCheck.setResourceNeedUpdate(true);
                    gameUpdateConfig.setUpdateFileName(gamePatch.getPatchName());
                    gameUpdateConfig.setUpdatePackageUrl(gamePatch.getPatchUrl());
                    gameUpdateConfig.setUpdatePackageSize(gamePatch.getPatchSize());
                    gameVersionCheck.getResourceUpdateConfigs().add(gameUpdateConfig);
                }
            }
        }
        return gameVersionCheck;
    }


    /**
     * 刷新缓存.
     */
    public void refreshCache(DeviceType deviceType, GameCode gameCode, int siteId, int resourceVersion,
                             GamePatch gamePatch) {
        cacheService.hPut(PublicCachedKeyConstant.GAME_PATCH_UPDATE + gameCode.getId() + siteId
                + deviceType.getId() + resourceVersion, gamePatch.getId() + "", gamePatch);
    }

    /**
     * 清除缓存.
     */
    public void cleanCache(DeviceType deviceType, GameCode gameCode, int siteId, int resourceVersion, int patchId) {
        cacheService.hDel(PublicCachedKeyConstant.GAME_PATCH_UPDATE + gameCode.getId() + siteId
                + deviceType.getId() + resourceVersion, patchId + "");
    }

    /**
     * 获取需更新的补丁信息.
     */
    @SuppressWarnings("unchecked")
    public List<GamePatch> getPatchList(DeviceType deviceType, GameCode gameCode, int siteId,
                                        int resourceVersion) {
        List<GamePatch> gamePatches = cacheService.hGetValues(PublicCachedKeyConstant.GAME_PATCH_UPDATE
                + gameCode.getId() + siteId + deviceType.getId() + resourceVersion, GamePatch.class);

        List<GamePatch> clientPatches = new ArrayList<>();

        if (CollectionUtils.isEmpty(gamePatches)) {
            GamePatchExample gamePatchExample = new GamePatchExample();
            GamePatchExample.Criteria criteria = gamePatchExample.createCriteria();
            criteria.andGameIdEqualTo(gameCode.getId()).andDeviceTypeEqualTo(deviceType.getId())
                    .andPatchVersionGreaterThan(resourceVersion);

            gamePatches = gamePatchMapper.selectByExample(gamePatchExample);
            for (GamePatch gamePatch : gamePatches) {
                if (gamePatch.getSiteId() == 0 || gamePatch.getSiteId() == siteId) {
                    cacheService.hPut(PublicCachedKeyConstant.GAME_PATCH_UPDATE + gameCode.getId() + siteId
                            + deviceType.getId() + resourceVersion, gamePatch.getId() + "", gamePatch);
                    clientPatches.add(gamePatch);
                }
            }
        } else {
            clientPatches.addAll(gamePatches);
        }
        return clientPatches;
    }


    @Transactional(rollbackFor = {ServiceException.class, RuntimeException.class})
    public void createGamePatch(GamePatch gamePatch) throws ServiceException {
        gamePatchMapper.insert(gamePatch);
        //刷新缓存
        refreshCache(gamePatch.getDeviceType(), GameCode.getById(gamePatch.getGameId()), gamePatch.getSiteId(), gamePatch.getPatchVersion(), gamePatch);
    }


    public PageResult<GamePatch> getPageList(int gameId, int siteId, Page page) throws Exception {
        if (page.getOrder() == null || page.getOrder().equals("")) {
            page.setOrder("id");
        }
        if (page.getSort() == null || page.getSort().equals("")) {
            page.setSort("desc");
        }

        int num1 = (page.getPageIndex() - 1) * page.getPageSize();

        Map paramMap = new HashMap();
        paramMap.put("gameId", gameId);
        paramMap.put("siteId", siteId);
        paramMap.put("str1", page.getOrder());
        paramMap.put("str2", page.getSort());
        paramMap.put("num1", num1);
        paramMap.put("num2", page.getPageSize());
        List<GamePatch> gamePatchList = this.gamePatchMapper.pageList(paramMap);
        Map map = new HashMap();
        map.put("gameId", gameId);
        map.put("siteId", siteId);
        int totalCount = this.gamePatchMapper.totalCount(map);
        page.setTotalCount(totalCount);
        PageResult<GamePatch> pageResult = new PageResult<>();
        pageResult.setPage(page);
        pageResult.setRows(gamePatchList);
        return pageResult;
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void delPatch(String patchIds) {
        String[] patchs = patchIds.split(",");
        List<Integer> idList = new ArrayList<Integer>();
        for (int i = 0; i < patchs.length; i++) {
            idList.add(Integer.parseInt(patchs[i]));
        }
        for (int i = 0; i < idList.size(); i++) {
            int id = idList.get(i);
            GamePatch gamePatch = this.gamePatchMapper.selectById(id);
            //清除缓存
            cleanCache(gamePatch.getDeviceType(), GameCode.getById(gamePatch.getGameId()), gamePatch.getSiteId(), gamePatch.getPatchVersion(), id);
            this.gamePatchMapper.delete(id);
        }


    }

    public GamePatch selectById(int id) {
        return this.gamePatchMapper.selectById(id);
    }

    @Override
    public void updateGamePatch(GamePatch gamePatch) throws ServiceException {
        this.gamePatchMapper.updateByPrimaryKey(gamePatch);
        //刷新缓存
        refreshCache(gamePatch.getDeviceType(), GameCode.getById(gamePatch.getGameId()), gamePatch.getSiteId(), gamePatch.getPatchVersion(), gamePatch);
    }
}
