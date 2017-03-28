package com.fanfandou.admin.api.system.service;

import com.fanfandou.admin.api.system.entity.ResEnum;
import com.fanfandou.admin.api.system.entity.Resource;
import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.entity.resource.SiteCode;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * author shengbo.
 * Desciption: 资源列表Service.
 */
public interface ResService {

    /**
     * 查询所有.
     */
    List<Resource> selectAll();

    /**
     * 查询by id.
     */
    Resource selectById(int id);

    /**
     * 添加游戏.
     */
    void addGameCode(Resource res);

    /**
     * 添加渠道.
     */
    void addSiteCode(Resource res);

    /**
     * 删除资源.
     */
    void delGame(int id);

    /**
     * 更新资源.
     */
    void updateGame(Resource res);

    /**
     * 更新游戏code.
     */
    void updateGameCode(ResEnum resType);

    /**
     * 更新渠道code.
     */
    void updateSiteCode(ResEnum resType);

    /**
     * 从数据库模糊查询出本页数据并排序.
     */
    PageResult<Resource> getPageList(String name, Page page);

    /**
     * 批量删除.
     */
    void delResList(List<Integer> idList);

    /**
     * 根据id和类型查找资源.
     * @param resId id
     * @param resType 类型
     * @return 资源
     */
    Resource selByIdType(int resId,int resType);

    /**
     * 一键修改状态.
     * @param ResIds 资源id字符串
     * @param available 状态 0 ，1
     */
    void optAvailable(String ResIds, int available) throws ServiceException;

    /**
     * 查询game集合，如果缓存没有，查询数据库.
     * @return id code
     *
     */
    Map<Integer, GameCode> getGameMap();

    /**
     * 查询site集合，如果缓存没有，查询数据库.
     * @return id code
     */
    Map<Integer, SiteCode> getSiteMap();
}
