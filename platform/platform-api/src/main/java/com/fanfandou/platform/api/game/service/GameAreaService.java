package com.fanfandou.platform.api.game.service;

import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.game.entity.AreaGroup;
import com.fanfandou.platform.api.game.entity.GameArea;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description: 区服操作接口.
 * <p/>
 * Date: 2016-05-20 11:31.
 * author: Arvin.
 */
public interface GameAreaService {

    /**
     * 区服创建.
     */
    @Transactional(rollbackFor = {ServiceException.class, RuntimeException.class})
    void createGameArea(GameArea gameArea) throws ServiceException;


    /**
     * 根据主键ID修改区服信息.
     */
    @Transactional(rollbackFor = {ServiceException.class, RuntimeException.class})
    void updateGameAreaById(GameArea gameArea) throws ServiceException;

    /**
     * 根据主键修改区服信息.
     */
    @Transactional(rollbackFor = {ServiceException.class, RuntimeException.class})
    void deleteGameAreaById(int id) throws ServiceException;

    /**
     * 根据游戏ID和区服code获取单个区服信息.（优先缓存中取）
     * @param gameId 游戏ID
     * @param areaCode 区服code
     */
    GameArea getGameAreaByCode(int gameId, String areaCode) throws ServiceException;

    /**
     * 刷新区服缓存.
     */
    void refreshGameAreaCache();

    /**
     * 根据主键查询区服信息.
     *
     * @param id 主键
     * @return 区服信息实体类
     * @throws ServiceException ServiceException
     */
    GameArea getGameAreaById(int id) throws ServiceException;

    /**
     * 工具查询区服列表.
     */
    PageResult getGameAreasForPage(Integer gameId, Integer siteId, Page page) throws ServiceException;

    /**
     * 工具查询区服列表.
     */
    List<GameArea> getGameAreas(int gameId, int siteId) throws ServiceException;

    /**
     * 通过游戏ID拿到区服信息.（优先缓存中取）
     */
    List<GameArea> getAreasByGameId(int gameId) throws ServiceException;

    /**
     * 更新客户端区服缓存.
     * @param gameArea gameArea
     */
    void updateCache(GameArea gameArea) throws ServiceException;

    //------------------------------------------------------AreaGroup--------------------------------------------------

    /**
     * 分页查询大区服.
     */
    PageResult<AreaGroup> getAreaGroupPage(int gameId, Page page) throws ServiceException;

    /**
     * 根据主键ID获取大区信息.
     * @param id 主键ID.
     */
    AreaGroup getAreaGroupById(int id) throws ServiceException;

    /**
     * 根据主键ID修改大区服信息.
     */
    @Transactional(rollbackFor = {ServiceException.class, RuntimeException.class})
    void updateAreaGroupById(AreaGroup areaGroup) throws ServiceException;

    /**
     * 根据主键删除大区信息.
     */
    @Transactional(rollbackFor = {ServiceException.class, RuntimeException.class})
    void deleteAreaGroupById(String ids) throws ServiceException;

    /**
     * 创建大区服.
     */
    @Transactional(rollbackFor = {ServiceException.class, RuntimeException.class})
    void createAreaGroup(AreaGroup areaGroup) throws ServiceException;


    /**
     * 通过游戏ID获取大区信息.
     */
    List<AreaGroup> getAreaGroupByGameId(int gameId) throws ServiceException;




}
