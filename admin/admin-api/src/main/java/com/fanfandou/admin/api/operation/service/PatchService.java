package com.fanfandou.admin.api.operation.service;

import com.fanfandou.admin.api.operation.entity.DeviceType;
import com.fanfandou.admin.api.operation.entity.GamePatch;
import com.fanfandou.admin.api.operation.entity.GameVersionCheck;
import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wudi.
 * Descreption:游戏补丁管理接口.
 * Date:2017/2/23
 */
public interface PatchService {

    /**
     * 补丁版本检查以及信息获取.
     */
    GameVersionCheck checkGameVersion(DeviceType deviceType, GameCode gameCode, int siteId,
                                      int resourceVersion, String ip) throws ServiceException;

    /**
     * 版本补丁创建.
     */
    @Transactional(rollbackFor = {ServiceException.class, RuntimeException.class})
    void createGamePatch(GamePatch gamePatch) throws ServiceException;

    /**
     * 从数据库模糊查询出本页数据并排序.
     */
    PageResult<GamePatch> getPageList(int gameId, int siteId, Page page) throws Exception;

    /**
     * 删除版本补丁.
     */
    void delPatch(String patchIds);

    GamePatch selectById(int id);

    /**
     * 版本补丁修改.
     */
    @Transactional(rollbackFor = {ServiceException.class, RuntimeException.class})
    void updateGamePatch(GamePatch gamePatch) throws ServiceException;

}
