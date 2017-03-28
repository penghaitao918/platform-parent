package com.fanfandou.platform.api.billing.service;

import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.billing.entity.BillingGoods;
import com.fanfandou.platform.api.billing.entity.BillingGoodsExample;
import com.fanfandou.platform.api.billing.entity.GoodsType;

import java.util.List;

/**
 * Created by wudi.
 * Descreption:商品接口定义.
 * Date:2016/4/28
 */
public interface GoodsService {

    /**
     * 创建商品.
     */
    void createGoods(BillingGoods billingGoods) throws ServiceException;


    /**
     * 根据商品ID修改配方.
     */
    void updateGoodsById(BillingGoods billingGoods) throws ServiceException;


    /**
     * 根据游戏ID获取所有有效的商品列表.
     * @param gameId 游戏ID
     * @return 商品列表
     */
    List<BillingGoods> queryGoodsByGameId(int gameId) throws ServiceException;

    /**
     * 根据游戏ID和商品CODE获取单个商品信息.
     * @param gameId 游戏ID
     * @param goodsId goodsId
     */
    BillingGoods queryGoodsByCode(int gameId, int goodsId) throws ServiceException;

    /**
     * 刷新商品列表缓存.
     */
    void refreshBillingGoodsCache();

    /**
     * 商品查询（客户端查询）.
     * @param gameId    游戏id
     * @param siteId     渠道id
     * @param areaCode    区服code
     * @param userId    用户id
     */
    List<BillingGoods> queryGoods(int gameId, int siteId, String areaCode, long userId, int shopType) throws
            ServiceException;

    /**
     * 商品查询（工具查询）.
     * @param gameId    游戏id
     * @param siteId     渠道id
     */
    PageResult queryGoods(Integer gameId, Integer siteId, String goodsName, Page page) throws ServiceException;



    /**
     * 通过example来查询，调用者自己组装.
     */
    List<BillingGoods> queryGoods(BillingGoodsExample billingGoodsExample) throws ServiceException;

    /**
     * 根据商品code查询.
     */
    BillingGoods queryGoods(String goodsCode) throws ServiceException;

    /**
     * 根据商品ID查询.
     */
    BillingGoods queryGoodsById(int goodsId) throws ServiceException;

    /**
     * 删除一个商品配方.
     */
    void delGoods(int goodsId) throws ServiceException;

    /**
     * 创建商品类型.
     */
    void createGoodsType(String code) throws ServiceException;

    /**
     * 修改商品类型.
     */
    void updateGoodsType(int id, String code) throws ServiceException;

    /**
     * 查询所有商品ID.
     * @return 所有商品类型集合.
     */
    List<GoodsType> queryGoodsType() throws ServiceException;

    /**
     * 根据ID删除商品类型.
     */
    void deleteGoodsType(int id) throws ServiceException;

    /**
     * 一键添加物品包.
     * @param goodsIds 选中的商品ID集合
     * @param itemIds 道具ID集合
     */
    void awardPackageGenerate(List<Integer> goodsIds, List<Integer> itemIds, int packageType, String desc, int value)
            throws ServiceException;

    /**
     * 一键添加首充策略.
     */
    void firstBuyGenerate(List<Integer> goodsIds, String firstOperation, int operateCount) throws ServiceException;


}
