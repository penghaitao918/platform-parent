package com.fanfandou.platform.api.game.service;

import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.game.entity.GameToy;
import com.fanfandou.platform.api.game.entity.GameToyBatch;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Description: 玩具相关接口.
 * <p/>
 * Date: 2016-04-12 17:31.
 * author: Arvin.
 */
public interface GameToyService {

    /**
     * 使用一个整数区间创建一批玩具code.
     * @param bachCode 批次号
     * @param maxCode 最大值
     * @param proTime 玩具生成时间.
     */
    void createBatch(String bachCode, int maxCode, Date proTime, int toyType, int gameId, int siteId) throws ServiceException;

    /**
     * 取到已激活玩具的类型集合.
     * @param userId 用户ID
     * @param toyType 玩具类型
     */
    List<Integer> getActedToyType(long userId, int toyType) throws ServiceException;

    /**
     * 批量保存.
     *
     * @param toys list
     */
    void saveBatch(List<GameToy> toys);

    /**
     * 玩具绑定到用户.
     *
     * @param toyGuid 玩具激活码
     * @param toyType 玩具类型
     * @param userId  userId
     * @param siteId  siteId
     * @param gameId  gameId
     */
    @Transactional(rollbackFor = {ServiceException.class, RuntimeException.class})
    void bindToUser(long toyGuid, int toyType, long userId, int siteId, int gameId) throws ServiceException;

    /**
     * 验证玩具.
     *
     * @param toyGuid 玩具激活码
     * @param userId  userId
     * @param siteId  siteId
     * @param gameId  gameId
     * @return boolean
     */
    boolean verifyToy(long toyGuid, int toyType, long userId, int siteId, int gameId) throws ServiceException;

    /**
     * 获取该激活码下所绑定的Uid.
     */
    GameToy getGameToy(long toyGuid);

    /**
     * 获取玩具列表，带分页.
     *
     * @param page 分页信息
     * @param toy  查询条件
     * @return 分页结果
     */
    PageResult<GameToy> getToys(Page page, GameToy toy, Date from, Date to) throws ServiceException;

    /**
     * 分页获取玩具批次信息.
     * @param page 分页信息
     * @param gameToyBatch 玩具批次
     * @param from 时间起点
     * @param to 时间终点
     */
    PageResult<GameToyBatch> getToyBatches(Page page, GameToyBatch gameToyBatch, Date from, Date to)
            throws ServiceException;

    PageResult<GameToyBatch> getToyBatchesTest(Page page, GameToyBatch gameToyBatch, Date from, Date to)
            throws ServiceException;

    /**
     * 批量失效.
     * @param ids 玩具ID.
     * @throws ServiceException
     */
    void invalidCode(List<Long> ids) throws ServiceException;
}
