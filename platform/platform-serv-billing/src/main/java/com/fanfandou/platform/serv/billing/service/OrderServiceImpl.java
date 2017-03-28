package com.fanfandou.platform.serv.billing.service;

import com.alibaba.fastjson.JSON;
import com.fanfandou.admin.api.system.entity.DataDictionary;
import com.fanfandou.admin.api.system.service.DataDictionaryService;
import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.constant.DicItemKeyConstant;
import com.fanfandou.common.constant.DicKeyConstant;
import com.fanfandou.common.entity.resource.DicItem;
import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.sequence.TableSequenceGenerator;
import com.fanfandou.common.service.cache.CacheService;
import com.fanfandou.platform.api.billing.entity.BillingGoods;
import com.fanfandou.platform.api.billing.entity.BillingOrder;
import com.fanfandou.platform.api.billing.entity.BillingOrderExample;
import com.fanfandou.platform.api.billing.entity.GoodsItem;
import com.fanfandou.platform.api.billing.entity.GoodsItemPackage;
import com.fanfandou.platform.api.billing.entity.OrderParam;
import com.fanfandou.platform.api.billing.entity.OrderStatus;
import com.fanfandou.platform.api.billing.entity.PayStatus;
import com.fanfandou.platform.api.billing.entity.RepairOrder;
import com.fanfandou.platform.api.billing.entity.ShopType;
import com.fanfandou.platform.api.billing.exception.BillingException;
import com.fanfandou.platform.api.billing.service.GoodsService;
import com.fanfandou.platform.api.billing.service.OrderService;
import com.fanfandou.platform.api.billing.service.WalletSerivce;
import com.fanfandou.platform.api.constant.IcachedConstant;
import com.fanfandou.platform.api.constant.TableSequenceConstant;
import com.fanfandou.platform.api.game.service.GameAreaService;
import com.fanfandou.platform.api.game.service.OperationDispatchService;
import com.fanfandou.platform.serv.billing.dao.BillingGoodsMapper;
import com.fanfandou.platform.serv.billing.dao.BillingOrderMapper;
import com.fanfandou.platform.serv.billing.dao.RepairOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by wudi.
 * Descreption:订单实现类.
 * Date:2016/5/3
 */
@Service("orderService")
public class OrderServiceImpl extends BaseLogger implements OrderService {

    @Autowired
    private TableSequenceGenerator tableSequenceGenerator;

    @Autowired
    private BillingOrderMapper billingOrderMapper;

    @Autowired
    private BillingGoodsMapper billingGoodsMapper;

    @Autowired
    private GameAreaService gameAreaService;

    @Autowired
    private RepairOrderMapper repairOrderMapper;

    @Autowired
    private OperationDispatchService operationDispatchService;

    @Autowired
    private WalletSerivce walletService;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private DataDictionaryService dataDictionaryService;

    @Autowired
    private GoodsService goodsService;

    @Override
    public void createOrder(String orderId) throws ServiceException {
        BillingOrder billingOrder = cacheService
                .get(IcachedConstant.BILLING_ORDER_PARAMS + orderId, BillingOrder.class);

        if (billingOrder == null) {
            throw new BillingException(BillingException.PAY_ORDER_NOT_EXISTS, "订单不存在");
        }

        billingOrder.setOrderStatus(OrderStatus.PAID);
        billingOrder.setPayStatus(PayStatus.PAID);
        billingOrder.setOrderId(orderId);

        billingOrderMapper.insertSelective(billingOrder);
        cacheService.del(IcachedConstant.BILLING_ORDER_PARAMS + orderId);
        logger.debug("createOrder complete");
    }

    @Override
    public String createOrderId(OrderParam orderParam) throws ServiceException {
        int areaId = gameAreaService.getGameAreaByCode(orderParam.getGameId(), orderParam.getAreaCode()).getId();
        BillingGoods billingGoods = goodsService.queryGoodsById(orderParam.getGoodsId());
        orderParam.setGoodsCode(billingGoods.getGoodsId() + "");
        BillingOrder billingOrder = BillingOrder.convertBillingOrder(orderParam);
        billingOrder.setAreaId(areaId);
        billingOrder.setOrderStatus(OrderStatus.UNPAID);
        billingOrder.setPayStatus(PayStatus.UNPAID);

        billingOrder.setPayAmount(billingGoods.getGoodsAmount());

        //订单前6位数字
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String orderDate = sdf.format(new Date());

        //生成随机2位数字
        String ran = String.valueOf(new Random().nextInt(100));

        //生成主键
        long seqValue = tableSequenceGenerator.generate(TableSequenceConstant
                .PLATFORM_BILLING_PAY_ORDER);

        billingOrder.setId(seqValue);

        String orderId = orderDate + ran + seqValue;

        //缓存入redis
        cacheService.put(IcachedConstant.BILLING_ORDER_PARAMS + orderId, billingOrder, 1800);
        logger.debug("createOrderId complete");
        return orderId;
    }

    @Override
    public void deliverOrder(String orderId) throws ServiceException {
        logger.debug("deliverOrder orderId = " + orderId);
        //根据订单查询订单详情
        BillingOrder billingOrder = billingOrderMapper.selectByOrderId(orderId);

        if (billingOrder == null) {
            throw new BillingException(BillingException.PAY_ORDER_DEDUCTE_FAIL, "订单无效");
        } else if (billingOrder.getPayStatus().equals(PayStatus.UNPAID)) {
            throw new BillingException(BillingException.PAY_ORDER_UNPAID, "订单未付款");
        }

         BillingGoods billingGoods =
                 billingGoodsMapper.selectByPrimaryKey(Integer.parseInt(billingOrder.getGoodsCode()));
        //订单状态正在发送，开始发货
        billingOrder.setOrderStatus(OrderStatus.DELIVERING);
        billingOrderMapper.updateByOrderIdSelective(billingOrder);
        boolean isFirstBuy = false;
        // TODO: 2016/11/4 首充策略
        //拿到订单商品内容，地址，并发货
        /*if (cacheService.get(IcachedConstant.BILLING_IS_FIRSTBUY + billingGoods
        .getGoodsCode() + billingOrder.getUserId(), Boolean.class) != null) {
            isFirstBuy = cacheService.get(IcachedConstant.BILLING_IS_FIRSTBUY + billingGoods
                    .getGoodsCode() + billingOrder.getUserId(), Boolean.class);
        }*/

       /* GoodsItemPackage awardPackage = cacheService.get(IcachedConstant
                .BILLING_AWARD_PACKAGE + billingGoods.getAwardId(), GoodsItemPackage.class);*/

        GameCode gameCode = GameCode.getById(billingOrder.getGameId());

        //判断是否为首充，并拿到首充的奖励
        /*if (!isFirstBuy) {
            awardPackage = cacheService.get(IcachedConstant.BILLING_FIRSTBUY_PACAKGE + billingGoods
                    .getAwardId(), GoodsItemPackage.class);
        }*/

        GoodsItemPackage goodsItemPackage = new GoodsItemPackage();
        GoodsItem goodsCountItem = new GoodsItem();
        DataDictionary dataDictionary = null;
        try {
            dataDictionary = dataDictionaryService.getValueByUniform(DicKeyConstant.OPERATION_ITEM_ITEMTYPE, -1, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<DicItem> dicItems = JSON.parseArray(dataDictionary.getItemJson(), DicItem.class);
        for (DicItem dicItem : dicItems) {
            String itemType = DicItemKeyConstant.GOODS_ITEM_TYPE_ITEM;
            if (dicItem.getKey().equals(itemType)) {
                goodsCountItem.setItemType(dicItem);
            }
        }
        goodsCountItem.setValue(billingGoods.getGoodsCount());
        goodsCountItem.setItemId(Integer.parseInt(billingGoods.getGoodsCode()));
        goodsItemPackage.setValue(billingGoods.getGoodsAmount());
        goodsItemPackage.getGoodsItems().add(0,goodsCountItem);
        //充值发货
        operationDispatchService.deliverPayPackage(gameCode, billingOrder.getUserId(),billingOrder.getAreaId(),goodsItemPackage);
        logger.debug("deliverOrder complete");
        //如果发货成功，则修改订单状态，已发货
        billingOrder.setOrderStatus(OrderStatus.DELIVERED);
        billingOrderMapper.updateByOrderIdSelective(billingOrder);
        //改变首充状态,该用户以及该笔商品已消费首充
        cacheService.put(IcachedConstant.BILLING_IS_FIRSTBUY + billingGoods.getGoodsCode() + billingOrder.getUserId(), true);

    }


    @Override
    public void repairOrder(String orderId, String repairReason, long userId, int gameId, int siteId)
            throws ServiceException {


    }

    @Override
    public PageResult getOrders(long userId, int siteId, int gameId, int areaId, Page curPage, Date
            from, Date to) throws ServiceException {
        if (curPage.getOrder() == null) {
            curPage.setOrder("id");
        }
        if (curPage.getSort() == null) {
            curPage.setSort("asc");
        }

        int startNum = (curPage.getPageIndex() - 1) * curPage.getPageSize();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId", userId);
        paramMap.put("siteId", siteId);
        paramMap.put("gameId", gameId);
        paramMap.put("areaId", areaId);
        paramMap.put("startNum", startNum);
        paramMap.put("endNum", curPage.getPageSize());
        paramMap.put("order", curPage.getOrder());
        paramMap.put("sort", curPage.getSort());
        paramMap.put("from", from);
        paramMap.put("to", to);

        List<BillingOrder> list = this.billingOrderMapper.getOrdersbyselective(paramMap);
        int totalCount = this.billingOrderMapper.totalCount(paramMap);
        curPage.setTotalCount(totalCount);
        PageResult<BillingOrder> pageResult = new PageResult<>();
        pageResult.setPage(curPage);
        pageResult.setRows(list);
        return pageResult;
    }

    @Override
    public List<BillingOrder> getOrders(BillingOrderExample billingOrderExample) throws ServiceException {
        return billingOrderMapper.selectByExample(billingOrderExample);
    }

    @Override
    public BillingOrder getOrders(String orderId) throws ServiceException {

        return billingOrderMapper.selectByOrderId(orderId);
    }

    @Override
    public void createRepairOrder(String orderId, String repairReason, long userId, int siteId, int gameId)
            throws ServiceException {
        RepairOrder repairOrder = new RepairOrder();
        repairOrder.setGameId(gameId);
        repairOrder.setSiteId(siteId);
        repairOrder.setOrderId(orderId);
        repairOrder.setRepairReason(repairReason);
        repairOrder.setUserId(userId);
        repairOrder.setRepairTime(new Date());
        repairOrderMapper.insertSelective(repairOrder);
    }
}
