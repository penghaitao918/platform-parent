package com.fanfandou.platform.api.billing.service;

import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.billing.entity.BillingOrder;
import com.fanfandou.platform.api.billing.entity.BillingOrderExample;
import com.fanfandou.platform.api.billing.entity.OrderParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by wudi.
 * Descreption:订单接口定义.
 * Date:2016/4/28
 */
public interface OrderService {

    /**
     * 实际创建订单.根据订单号从redis中渠道相关信息补全订单.
     * @throws ServiceException ServiceException.
     */
    void createOrder(String orderId) throws ServiceException;


    /**
     * 实际订单号，并把相关信息存入redis.
     * @return 订单实体.
     * @throws ServiceException ServiceException.
     */
    String createOrderId(OrderParam orderParam) throws ServiceException;

    /**
     * 订单发货.
     * @param orderId 订单ID
     */
    @Transactional(rollbackFor = {ServiceException.class, RuntimeException.class})
    void deliverOrder(String orderId) throws ServiceException;

    /**
     * 补单.
     */
    void repairOrder(String orderId,String repairReason, long userId, int gameId, int siteId) throws
            ServiceException;


    /**
     * 根据相关信息查询订单.
     * @param userId 用户id
     * @param gameId 游戏
     * @param siteId 渠道
     * @param from 起始时间
     * @param to 结束时间
     * @return 订单信息
     */
    PageResult getOrders(long userId, int siteId, int gameId, int areaId, Page curPage, Date from, Date to)
            throws ServiceException;

    /**
     * 通过example查询.
     */
    List<BillingOrder> getOrders(BillingOrderExample billingOrderExample) throws ServiceException;


    /**
     * 通过订单号查询.
     */
    BillingOrder getOrders(String orderId) throws ServiceException;



    /**
     * 创建补单.
     * @param orderId 订单ID
     * @param repairReason 补单说明
     * @param userId 账户ID
     * @param gameId 游戏
     * @param siteId 渠道
     */
    void createRepairOrder(String orderId, String repairReason, long userId, int siteId, int gameId)
            throws  ServiceException;
}
