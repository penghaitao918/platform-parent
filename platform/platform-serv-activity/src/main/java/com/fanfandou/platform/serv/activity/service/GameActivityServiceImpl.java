package com.fanfandou.platform.serv.activity.service;

import com.fanfandou.admin.api.system.service.DataDictionaryService;
import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.constant.DicItemKeyConstant;
import com.fanfandou.common.constant.DicKeyConstant;
import com.fanfandou.common.constant.PublicCachedKeyConstant;
import com.fanfandou.common.entity.ValidStatus;
import com.fanfandou.common.entity.resource.DicItem;
import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.sequence.TableSequenceGenerator;
import com.fanfandou.common.service.cache.CacheService;
import com.fanfandou.platform.api.activity.entity.GameActivity;
import com.fanfandou.platform.api.activity.entity.GameActivityExample;
import com.fanfandou.platform.api.activity.exception.ActivityException;
import com.fanfandou.platform.api.activity.service.GameActivityService;
import com.fanfandou.platform.api.constant.TableSequenceConstant;
import com.fanfandou.platform.api.game.service.OperationDispatchService;
import com.fanfandou.platform.serv.activity.dao.GameActivityMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wudi.
 * Descreption:游戏活动实现.
 * Date:2016/11/22
 */
@Service("gameActivityService")
public class GameActivityServiceImpl extends BaseLogger implements GameActivityService {

    @Autowired
    private GameActivityMapper gameActivityMapper;

    @Autowired
    private TableSequenceGenerator tableSequenceGenerator;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private OperationDispatchService operationDispatchService;

    @Autowired
    private DataDictionaryService dataDictionaryService;

    @Override
    public void createActivity(GameActivity gameActivity) throws ServiceException {
        logger.debug("开始访问createActivity");
        if (gameActivity.getActivityId() == 0 || StringUtils.isEmpty(gameActivity.getSiteIds())
                || gameActivity.getGameId() == 0 || StringUtils.isEmpty(gameActivity.getTaskId())) {
            throw new ActivityException(ActivityException.GAME_ACITIVITY_LACK_PARAMS, "参数缺失");
        }
        long id = tableSequenceGenerator.generate(TableSequenceConstant
                .PLATFORM_GAME_ACITIVITY);
        gameActivity.setId(id);
        GameActivityExample gameActivityExample = new GameActivityExample();
        gameActivityExample.createCriteria().andIdEqualTo(gameActivity.getId().intValue());
        if (gameActivityMapper.countByExample(gameActivityExample) > 0) {
            logger.debug("开始访问createActivity ID重复");
            throw new ActivityException(ActivityException.GAME_ACITIVITY_DUPLECATE_ID, "ID重复");
        }

        gameActivityMapper.insertSelective(gameActivity);
        refreshActivityCache(gameActivity);
        //pushActivityMsg(gameActivity);
    }

    /**
     * 推送更新消息消息.
     */
    public void pushActivityMsg(GameActivity gameActivity, DicItem dicItem) throws ServiceException {
        logger.debug("pushActivityMsg >>> 开始查询字典");
      /*  DicItem dicItem = dataDictionaryService.getDicItemByConstant(DicKeyConstant.PLATFORM_PUSHINFO_PUSHTYPE,
                DicItemKeyConstant.PLATFORM_PUSHINFO_ACTIVITY_GAME_UPDATE);*/
        logger.debug("pushActivityMsg >>> 字典查询完毕");
        String areaIds[] = gameActivity.getAreaIds().split(",");
        for (String areaId : areaIds) {
            if (!StringUtils.isEmpty(areaId)) {
                logger.debug("pushActivityMsg >>> 开始推送 " + areaId + "区服");
                operationDispatchService.pushMsgToClient(GameCode.getById(gameActivity.getGameId()),
                        Integer.parseInt(areaId), 0, dicItem, gameActivity.getId(), "");
                logger.debug("pushActivityMsg >>> " + areaId + "区服推送完毕");
            }
        }
    }

    @Override
    public void updateActivity(GameActivity gameActivity) throws ServiceException {
        gameActivityMapper.updateByPrimaryKeySelective(gameActivity);
        gameActivity = gameActivityMapper.selectByPrimaryKey(gameActivity.getId().intValue());
        refreshActivityCache(gameActivity);
        DicItem dicItem = dataDictionaryService.getDicItemByConstant(DicKeyConstant.PLATFORM_PUSHINFO_PUSHTYPE,
                DicItemKeyConstant.PLATFORM_PUSHINFO_ACTIVITY_GAME_UPDATE);
        pushActivityMsg(gameActivity, dicItem);
    }

    @Override
    public PageResult getGameActivityList(Integer gameId, Integer siteId, Integer areaId, Page page) throws ActivityException {
        if (page.getOrder() == null) {
            page.setOrder("id");
        }
        if (page.getSort() == null) {
            page.setSort("asc");
        }
        int startNum = (page.getPageIndex() - 1) * page.getPageSize();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("gameId", gameId);
        paramMap.put("site", siteId);
        paramMap.put("startNum", startNum);
        paramMap.put("endNum", page.getPageSize());
        paramMap.put("order", page.getOrder());
        paramMap.put("sort", page.getSort());
        List<GameActivity> currentAreaList = gameActivityMapper.selectBySelective(paramMap);

        int totalCount = this.gameActivityMapper.getAllCount(paramMap);
        logger.debug("queryGameActivityList >>> totalCount = " + totalCount);
        page.setTotalCount(totalCount);
        PageResult<GameActivity> pageResult = new PageResult<GameActivity>();
        pageResult.setPage(page);
        pageResult.setRows(currentAreaList);

        return pageResult;
    }

    @Override
    public List<GameActivity> getGameActivityList(int gameId) throws ActivityException {
        List<GameActivity> gameActivities = cacheService.hGetValues(PublicCachedKeyConstant
                .GAME_ACITIVITY_LIST + gameId, GameActivity.class);
        if (CollectionUtils.isEmpty(gameActivities)) {
            logger.debug("活动不在缓存中");
            GameActivityExample gameActivityExample = new GameActivityExample();
            gameActivityExample.createCriteria().andGameIdEqualTo(gameId).andValidStatusEqualTo(ValidStatus.VALID.getId());

            GameActivityExample.Criteria criteria = gameActivityExample.createCriteria();
            criteria.andValidStatusEqualTo(ValidStatus.INVALID.getId());

            gameActivityExample.or(criteria);

            gameActivities = gameActivityMapper.selectByExample(gameActivityExample);
            for (GameActivity gameActivity : gameActivities) {
                cacheService.hPut(PublicCachedKeyConstant.GAME_ACITIVITY_LIST + gameId,
                        gameActivity.getId() + "", gameActivity);
            }
        }
        return gameActivities;
    }

    @Override
    public GameActivity getGameActivityContent(int gameId, int id) throws ActivityException {
        GameActivity gameActivity = cacheService.hGet(PublicCachedKeyConstant.GAME_ACITIVITY_LIST + gameId,
                id + "", GameActivity.class);
        if (gameActivity == null) {
            GameActivityExample gameActivityExample = new GameActivityExample();
            gameActivityExample.createCriteria().andIdEqualTo(id).andGameIdEqualTo(gameId)
                    .andValidStatusEqualTo(ValidStatus.VALID.getId());

            GameActivityExample.Criteria criteria = gameActivityExample.createCriteria();
            criteria.andValidStatusEqualTo(ValidStatus.INVALID.getId());

            gameActivityExample.or(criteria);

            List<GameActivity> gameActivities = gameActivityMapper.selectByExample(gameActivityExample);
            if (CollectionUtils.isEmpty(gameActivities)) {
                throw new ActivityException(ActivityException.GAME_ACITIVITY_ISEMPTY);
            }
            gameActivity = gameActivities.get(0);
        }
        return gameActivity;
    }

    @Override
    public void refreshActivityCache(GameActivity gameActivity) {
        logger.info("refreshActivityCache" + gameActivity.toString());
        if (gameActivity.getValidStatus().getId() == ValidStatus.REMOVED.getId()) {
            cacheService.hDel(PublicCachedKeyConstant.GAME_ACITIVITY_LIST + gameActivity.getGameId(),
                    gameActivity.getId() + "");
        } else {
            cacheService.hPut(PublicCachedKeyConstant.GAME_ACITIVITY_LIST + gameActivity.getGameId(),
                    gameActivity.getId() + "", gameActivity);
        }
    }

    @Override
    public GameActivity queryGameActivity(int id) throws ActivityException {
        return gameActivityMapper.selectByPrimaryKey(id);
    }

    @Override
    public void turnOffActivity(GameActivity gameActivity) throws ServiceException {
        gameActivityMapper.updateByPrimaryKeySelective(gameActivity);
        gameActivity = queryGameActivity(gameActivity.getId().intValue());
        refreshActivityCache(gameActivity);
        String dicConstant = DicItemKeyConstant.PLATFORM_PUSHINFO_ACTIVITY_GAME_CLOSE;
        if (gameActivity.getValidStatus().getId() == ValidStatus.REMOVED.getId()) {
            dicConstant = DicItemKeyConstant.PLATFORM_PUSHINFO_ACTIVITY_GAME_REMOVE;
        } else if (gameActivity.getValidStatus().getId() == ValidStatus.VALID.getId()) {
            dicConstant = DicItemKeyConstant.PLATFORM_PUSHINFO_ACTIVITY_GAME_UPDATE;
        }
        DicItem dicItem = dataDictionaryService.getDicItemByConstant(DicKeyConstant.PLATFORM_PUSHINFO_PUSHTYPE,
                dicConstant);
        pushActivityMsg(gameActivity, dicItem);
    }

    /**
     * 活动参数校验.
     */
    public GameActivity checkActivityIllegll(int gameId, int siteId, int areaId, int id)
            throws ServiceException {
        GameActivity gameActivity = cacheService.hGet(PublicCachedKeyConstant.GAME_ACITIVITY_LIST + gameId,
                id + "", GameActivity.class);
        if (gameActivity == null) {
            GameActivityExample gameActivityExample = new GameActivityExample();
            gameActivityExample.createCriteria().andIdEqualTo(id);
            gameActivity = queryGameActivity(id);
            if (gameActivity == null) {
                throw new ActivityException(ActivityException.GAME_ACITIVITY_ISEMPTY, "没有改活动");
            }
        }
        if (gameActivity.getSiteIds().equals(",")) {
            return gameActivity;
        }
        if (gameId != gameActivity.getGameId() || !gameActivity.getSiteIds().contains("," + siteId + ",")
                || !gameActivity.getAreaIds().contains("," + areaId + ",") ) {
            throw new ActivityException(ActivityException.GAME_ACITIVITY_PARAMS_ERRRO, "参数错误");
        }
        return gameActivity;
    }

    @Override
    public void sendActivityTask(int gameId, int siteId, long userId, int areaId, int areaCode, int id)
            throws ServiceException {
        GameActivity gameActivity = checkActivityIllegll(gameId, siteId, areaId, id);
        long currentTime = new Date().getTime();
        long activityStartTime = gameActivity.getStartTime().getTime();
        long activityEndTime = gameActivity.getEndTime().getTime();
        if (currentTime > activityEndTime) {
            throw new ActivityException(ActivityException.GAME_ACITIVITY_PAST,"活动已过期");
        }
        if (currentTime < activityStartTime) {
            throw new ActivityException(ActivityException.GAMEA_ACTIVITY_UNSTART, "活动还未开始");
        }
        operationDispatchService.sendActivityTask(GameCode.getById(gameId), userId, areaId, areaCode, gameActivity.getActivityId(),
                gameActivity.getStartTime().getTime());
    }

    @Override
    public void settleActivityTask(int gameId, int siteId, long userId, int areaId, int areaCode, int id, int taskId)
            throws ServiceException {
        GameActivity gameActivity = checkActivityIllegll(gameId, siteId, areaId, id);
        operationDispatchService.settleActivityTask(GameCode.getById(gameId), userId, areaId, areaCode,gameActivity.getActivityId(), taskId);
    }
}
