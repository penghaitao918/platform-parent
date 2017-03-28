package com.fanfandou.platform.serv.game.service;

import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.constant.PublicCachedKeyConstant;
import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.entity.resource.SourceCodeFactory;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.sequence.TableSequenceGenerator;
import com.fanfandou.common.service.cache.CacheService;
import com.fanfandou.platform.api.constant.TableSequenceConstant;
import com.fanfandou.platform.api.game.entity.AreaGroup;
import com.fanfandou.platform.api.game.entity.GameArea;
import com.fanfandou.platform.api.game.exception.GameException;
import com.fanfandou.platform.api.game.service.GameAreaService;
import com.fanfandou.platform.serv.game.dao.AreaGroupMapper;
import com.fanfandou.platform.serv.game.dao.GameAreaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wudi.
 * Descreption:区服接口实现.
 * Date:2016/5/27
 */
@Service("gameAreaService")
public class GameAreaServiceImpl extends BaseLogger implements GameAreaService {

    @Autowired
    private GameAreaMapper gameAreaMapper;

    @Autowired
    private TableSequenceGenerator tableSequenceGenerator;

    @Autowired
    private AreaGroupMapper areaGroupMapper;

    @Autowired
    private CacheService cacheService;

    @Override
    public void createGameArea(GameArea gameArea) throws ServiceException {
        gameArea.setId((int) tableSequenceGenerator.generate(TableSequenceConstant.PLATFORM_GAME_AREA));
        gameArea.setCreateTime(new Date());
        if (gameArea.getAreaCode() == null || gameArea.getAreaName() == null
                || gameArea.getAreaTag() == 0 || gameArea.getGameId() == 0 || gameArea.getSiteIds() == null
                || gameArea.getSupportVersionMax() == null || gameArea.getSupportVersionMin() == null) {
            throw new GameException(GameException.GAME_AREA_LACK_PARAMS, "区服信息缺失");
        }
        if (gameAreaMapper.checkCodeExist(gameArea.getGameId(), gameArea.getAreaCode()) > 0) {
            throw new GameException(GameException.GAME_AREA_DULPLICATE, "区服code重复");
        }

        gameAreaMapper.insertSelective(gameArea);
        //更新缓存
        updateCache(gameArea);

    }

    @Override
    public void updateGameAreaById(GameArea gameArea) throws ServiceException {
        gameAreaMapper.updateByPrimaryKeySelective(gameArea);
    }

    @Override
    public void deleteGameAreaById(int id) throws ServiceException {
        GameArea gameArea = gameAreaMapper.selectByPrimaryKey(id);
        cacheService.hDel(PublicCachedKeyConstant.GAME_CLIENT_AREA_LIST + gameArea.getGameId(), gameArea.getAreaCode());
        gameAreaMapper.deleteByPrimaryKey(id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public GameArea getGameAreaByCode(int gameId, String areaCode) throws ServiceException {
        GameArea gameArea = cacheService.hGet(PublicCachedKeyConstant.GAME_CLIENT_AREA_LIST + gameId,
                areaCode, GameArea.class);
        if (gameArea == null) {
            logger.debug("getGameAreaByCode gameAreas cache is null, read db");
            List<GameArea> gameAreas = gameAreaMapper.getAreasByGameCode(gameId, areaCode);
            if (gameAreas.size() == 0) {
                throw new GameException(GameException.GAME_AREA_NULL);
            }
            gameArea = gameAreas.get(0);
            cacheService.hPut(PublicCachedKeyConstant.GAME_CLIENT_AREA_LIST + gameId, areaCode, gameArea);
        }
        return gameArea;
    }

    @Override
    public void refreshGameAreaCache() {
        Map<Integer, GameCode> games = SourceCodeFactory.getGames();
        for (Map.Entry<Integer, GameCode> entry : games.entrySet()) {
            int gameId = entry.getValue().getId();
            List<GameArea> gameAreas = gameAreaMapper.getAreasByGameId(gameId);
            for (GameArea gameArea : gameAreas) {
                cacheService.hPut(PublicCachedKeyConstant.GAME_CLIENT_AREA_LIST + gameId, gameArea.getAreaCode(), gameArea);
            }
        }
    }

    @Override
    public GameArea getGameAreaById(int id) throws ServiceException {
        return gameAreaMapper.selectByPrimaryKey(id);
    }


    @Override
    public PageResult getGameAreasForPage(Integer gameId, Integer siteId, Page page) throws ServiceException {
        if (page.getOrder() == null) {
            page.setOrder("id");
        }
        if (page.getSort() == null) {
            page.setSort("asc");
        }
        if (gameId != null) {
            if (gameId == -1 || gameId == 0) {
                gameId = null;
            }
        }
        if (siteId != null) {
            if (siteId == -1 || siteId == 0) {
                siteId = null;
            }
        }

        int startNum = (page.getPageIndex() - 1) * page.getPageSize();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("gameId", gameId);
        paramMap.put("site", siteId);
        paramMap.put("startNum", startNum);
        paramMap.put("endNum", page.getPageSize());
        paramMap.put("order", page.getOrder());
        paramMap.put("sort", page.getSort());
        List<GameArea> currentAreaList = gameAreaMapper.getAreasSelective(paramMap);

        int totalCount = this.gameAreaMapper.getTotalArea(paramMap);
        logger.debug("getGameAreasForPage >>> totalCount = " + totalCount);
        page.setTotalCount(totalCount);
        PageResult<GameArea> pageResult = new PageResult<GameArea>();
        pageResult.setPage(page);
        pageResult.setRows(currentAreaList);

        return pageResult;
    }


    @Override
    public List<GameArea> getGameAreas(int gameId, int siteId) throws ServiceException {

        List<GameArea> currentAreaList = gameAreaMapper.getAreasByGameId(gameId);
        List<GameArea> areaList = new ArrayList<GameArea>();
        if (siteId != 0) {
            for (GameArea gameArea : currentAreaList) {
                if (gameArea.getSiteIds().contains(siteId + "")) {
                    areaList.add(gameArea);
                }
            }
        }
        return areaList;
    }

    @Override
    public List<GameArea> getAreasByGameId(int gameId) throws ServiceException {
        List<GameArea> gameAreas = cacheService.hGetValues(PublicCachedKeyConstant.GAME_CLIENT_AREA_LIST + gameId,
                GameArea.class);
        if (gameAreas == null || gameAreas.size() == 0) {
            logger.debug("getAreasByGameId gameAreas is null, read cache");
            gameAreas = gameAreaMapper.getAreasByGameId(gameId);
            for (GameArea gameArea : gameAreas) {
                cacheService.hPut(PublicCachedKeyConstant.GAME_CLIENT_AREA_LIST + gameId, gameArea.getAreaCode(), gameArea);
            }

        }
        return gameAreas;
    }


    @Override
    public void updateCache(GameArea gameArea) throws ServiceException {
        cacheService.hPut(PublicCachedKeyConstant.GAME_CLIENT_AREA_LIST + gameArea.getGameId(),
                gameArea.getAreaCode(), gameArea);
    }

    //--------------------------------------------------------------------------------AreaGroup-----------------------

    @Override
    public PageResult<AreaGroup> getAreaGroupPage(int gameId, Page page) throws ServiceException {
        if (page.getOrder() == null) {
            page.setOrder("id");
        }
        if (page.getSort() == null) {
            page.setSort("asc");
        }
        if (gameId == 0) {
            gameId = -1;
        }

        int startNum = (page.getPageIndex() - 1) * page.getPageSize();
        page.setPageIndex(startNum);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("gameId", gameId);
        paramMap.put("startNum", startNum);
        paramMap.put("endNum", page.getPageSize());
        paramMap.put("order", page.getOrder());
        paramMap.put("sort", page.getSort());
        List<AreaGroup> areaGroupList = areaGroupMapper.getAreaGroupPage(paramMap);
        int areaGroupSize = areaGroupMapper.getAreaGroupSize(paramMap);
        page.setTotalCount(areaGroupSize);
        PageResult<AreaGroup> pageResult = new PageResult<AreaGroup>();
        pageResult.setPage(page);
        pageResult.setRows(areaGroupList);
        return pageResult;
    }

    @Override
    public AreaGroup getAreaGroupById(int id) throws ServiceException {
        return areaGroupMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateAreaGroupById(AreaGroup areaGroup) throws ServiceException {
        areaGroupMapper.updateByPrimaryKeySelective(areaGroup);
    }

    @Override
    public void deleteAreaGroupById(String ids) throws ServiceException {
        String[] areaIds = ids.split(",");
        for (String areaId : areaIds) {
            int count = gameAreaMapper.getAreasCountForGroup(Integer.parseInt(areaId));
            if (count > 0) {
                throw new GameException(GameException.GAME_AREA_CANNOT_DEL, "区服无法删除");
            }
            areaGroupMapper.deleteByPrimaryKey(Integer.parseInt(areaId));
        }
    }

    /**
     * 通过gameId获取大区信息.
     */
    public List<AreaGroup> getAreaGroupByGameId(int gameId) throws ServiceException {
        return areaGroupMapper.getAreaGroupByGameId(gameId);
    }

    @Override
    public void createAreaGroup(AreaGroup areaGroup) throws ServiceException {
        areaGroup.setCreateTime(new Date());
        areaGroup.setId((int) tableSequenceGenerator.generate(TableSequenceConstant.PLATFORM_GAME_GROUP_AREA));
        areaGroup.setCreateTime(new Date());
        if (areaGroup.getAreaGroupName() == null || areaGroup.getAreaGroupCode() == null
                || areaGroup.getGameId() == 0 || areaGroup.getClientEnterAddr() == null) {
            throw new GameException(GameException.GAME_AREA_LACK_PARAMS, "区服信息缺失");
        }
        if (areaGroup.getClientEnterAddr() == null && areaGroup.getServerEnterAddr() == null) {
            throw new GameException(GameException.GAME_AREA_ADDRESS_ISNULL, "连接地址不能为空");
        }

        if (areaGroupMapper.checkCodeExist(areaGroup.getGameId(), areaGroup.getAreaGroupCode()) > 0) {
            throw new GameException(GameException.GAME_AREA_DULPLICATE, "区服code重复");
        }
        areaGroupMapper.insertSelective(areaGroup);
    }

}
