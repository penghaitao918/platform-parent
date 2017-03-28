package com.fanfandou.platform.api.activity.service;

import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.activity.entity.GameActivity;
import com.fanfandou.platform.api.activity.exception.ActivityException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by wudi.
 * Descreption:游戏活动服务.
 * Date:2016/11/22
 */
public interface GameActivityService {

    /**
     * 新增游戏活动.
     * @param gameActivity 活动实体.
     */
    @Transactional(rollbackFor = {ServiceException.class, RuntimeException.class})
    void createActivity(GameActivity gameActivity) throws ServiceException;

    /**
     * 修改活动状态.
     */
    @Transactional(rollbackFor = {ServiceException.class, RuntimeException.class})
    void updateActivity(GameActivity gameActivity) throws ServiceException;

    /**
     * 活动批量分页查询.
     */
    PageResult getGameActivityList(Integer gameId, Integer siteId, Integer areaId, Page page) throws ActivityException;

    /**
     * 根据条件获取所有活动列表.
     */
    List<GameActivity> getGameActivityList(int gameId) throws ActivityException;

    /**
     * 查询单个活动信息.
     */
    GameActivity getGameActivityContent(int gameId, int activityId) throws ActivityException;

    /**
     * 刷新活动缓存.
     */
    void refreshActivityCache(GameActivity gameActivity);

    /**
     * 查询单个活动信息.
     * @param id 活动ID
     */
    GameActivity queryGameActivity(int id) throws ActivityException;

    /**
     * 修改活动状态.必须传入ID，和更改的状态.
     */
    @Transactional(rollbackFor = {ServiceException.class, RuntimeException.class})
    void turnOffActivity(GameActivity gameActivity) throws ServiceException;

    /**
     * 通知游戏服活动信息.
     */
    void sendActivityTask(int gameId, int siteId, long userId, int areaId,int areaCode, int id)
            throws ServiceException;

    /**
     * 通知游戏服活动结算.
     */
    void settleActivityTask(int gameId, int siteId, long userId, int areaId, int areaCode, int id, int taskId)
            throws ServiceException;


}
