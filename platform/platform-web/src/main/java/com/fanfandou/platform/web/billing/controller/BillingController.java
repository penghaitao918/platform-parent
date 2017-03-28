package com.fanfandou.platform.web.billing.controller;

import com.fanfandou.common.entity.result.JsonResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.util.ErrorValidate;
import com.fanfandou.common.util.HttpAddrUtil;
import com.fanfandou.platform.api.billing.entity.OrderParam;
import com.fanfandou.platform.web.billing.service.BillingServiceClient;
import com.fanfandou.platform.web.billing.vo.OrderVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wudi.
 * Descreption:支付controller类.
 * Date:2016/5/18
 */
@RequestMapping("/billing")
@RestController
@ErrorValidate
public class BillingController {

    @Autowired
    private BillingServiceClient billingServiceClient;

    /**
     * 创建订单号.
     * @param orderParam 创建订单所需参数.
     * @return 返回一个订单号
     * @throws ServiceException ServiceException.
     */
    @RequestMapping("/createOrder")
    public JsonResult createOrderId(OrderParam orderParam) throws ServiceException {
        OrderVo orderVo = new OrderVo();
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes()).getRequest();
        orderParam.setIpAddress(HttpAddrUtil.getRemoteAddr(httpServletRequest));
        orderVo.setOrderId(billingServiceClient.createOrderId(orderParam));
        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(orderVo);
        jsonResult.setStatus(JsonResult.SUCCESS);
        jsonResult.setMessage(JsonResult.SUCCESS_MSG);
        return jsonResult;
    }

    /**
     * 翻翻豆支付.
     * @param orderId 订单号.
     * @return 成功or失败
     * @throws ServiceException ServiceException
     */
    @RequestMapping("/charge")
    public JsonResult walletCharge(String orderId) throws ServiceException {
        billingServiceClient.walletCharge(orderId);
        return JsonResult.RESULT_SUCCESS;
    }

    /**
     * 查询商品配方.
     * @param gameId 游戏ID
     * @param siteId 渠道ID
     * @param areaCode 区服ID
     * @param userId 平台ID
     * @return 商品列表
     * @throws ServiceException ServiceException
     */
    @RequestMapping("/getGoodsList")
    public JsonResult getGoodsList(int gameId, int siteId, String areaCode, long userId, int shopType) throws
            ServiceException {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(billingServiceClient.queryGoods(gameId, siteId, areaCode, userId, shopType));
        jsonResult.setStatus(JsonResult.SUCCESS);
        jsonResult.setMessage(JsonResult.SUCCESS_MSG);
        return jsonResult;
    }

    /**
     * 宝石够买物品.
     * @param goodsId 道具ID
     */
    @RequestMapping("/gemPurchase")
    public JsonResult gemPurchase(int gameId, String areaCode, long userId, int money, int goodsId) throws
            ServiceException {
        billingServiceClient.gemPurchase(gameId, areaCode, userId, money, goodsId);
        JsonResult jsonResult = new JsonResult();

        jsonResult.setStatus(JsonResult.SUCCESS);
        jsonResult.setMessage(JsonResult.SUCCESS_MSG);
        return JsonResult.RESULT_SUCCESS;
    }

    /**
     * 根据goodsId获取商品信息.
     */
    @RequestMapping("/getGoodsItem")
    public JsonResult getGoodsItem(int goodsId) throws ServiceException {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(billingServiceClient.getGoods(goodsId));
        jsonResult.setStatus(JsonResult.SUCCESS);
        jsonResult.setMessage(JsonResult.SUCCESS_MSG);
        return jsonResult;
    }

}
