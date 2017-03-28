package com.fanfandou.platform.serv.billing.service;

import com.alibaba.fastjson.JSON;
import com.fanfandou.admin.api.system.entity.DataDictionary;
import com.fanfandou.admin.api.system.service.DataDictionaryService;
import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.constant.DicItemKeyConstant;
import com.fanfandou.common.constant.DicKeyConstant;
import com.fanfandou.common.entity.resource.DicItem;
import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.sequence.TableSequenceGenerator;
import com.fanfandou.common.service.cache.CacheService;
import com.fanfandou.platform.api.billing.entity.BillingGoods;
import com.fanfandou.platform.api.billing.entity.BillingOrder;
import com.fanfandou.platform.api.billing.entity.BillingWalletBalance;
import com.fanfandou.platform.api.billing.entity.BillingWalletDetail;
import com.fanfandou.platform.api.billing.entity.Currency;
import com.fanfandou.platform.api.billing.entity.GoodsItem;
import com.fanfandou.platform.api.billing.entity.GoodsItemPackage;
import com.fanfandou.platform.api.billing.entity.PayType;
import com.fanfandou.platform.api.billing.entity.WalletDetailType;
import com.fanfandou.platform.api.billing.exception.BillingException;
import com.fanfandou.platform.api.billing.service.BillingService;
import com.fanfandou.platform.api.billing.service.GoodsService;
import com.fanfandou.platform.api.billing.service.OrderService;
import com.fanfandou.platform.api.billing.service.WalletSerivce;
import com.fanfandou.platform.api.constant.IcachedConstant;
import com.fanfandou.platform.api.constant.TableSequenceConstant;
import com.fanfandou.platform.api.game.service.OperationDispatchService;
import com.fanfandou.platform.serv.billing.dao.BillingWalletBalanceMapper;
import com.fanfandou.platform.serv.billing.dao.BillingWalletDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wudi.
 * Descreption:支付实现.
 * Date:2016/5/5
 */
@Service("billingService")
public class BillingServiceImpl extends BaseLogger implements BillingService {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private WalletSerivce walletSerivce;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BillingWalletDetailMapper billingWalletDetailMapper;

    private BillingWalletBalanceMapper billingWalletBalanceMapper;

    @Autowired
    private TableSequenceGenerator tableSequenceGenerator;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private DataDictionaryService dataDictionaryService;

    @Autowired
    private OperationDispatchService operationDispatchService;

    @Override
    public void charge(String orderId, String srcOrderId, Currency currency, int chargeAmount) throws ServiceException {
        BillingOrder billingOrder = cacheService
                .get(IcachedConstant.BILLING_ORDER_PARAMS + orderId, BillingOrder.class);

        if (billingOrder == null) {
            throw new BillingException(BillingException.PAY_ORDER_INVALID, "无效订单");
        }

        //根据productId获取商品信息
        BillingGoods billingGoods = goodsService.queryGoodsById(Integer.parseInt(billingOrder.getGoodsCode()));
        logger.debug("查询商品信息完毕");
        int payAmount = billingGoods.getGoodsAmount();//分
        double discount = billingGoods.getDisaccount();

        //折扣计算
        if (discount < 1.0) {
            payAmount = (int) (payAmount * (1 - discount));
        }
        //如果支付金额小于订单支付金额，则支付失败，存入钱包
        if (chargeAmount < payAmount) {
            logger.info("支付金额小于订单支付金额，则支付失败，存入钱包");
            if (!billingOrder.getPayType().equals(PayType.THIRD)) {
                BillingWalletBalance billingWalletBalance = walletSerivce.depositWallet(billingOrder.getUserId(),
                        chargeAmount);
                logger.debug("存钱完成");
                //流水记录
                operateWalletDetail(billingWalletBalance.getWalletId(), orderId, chargeAmount, currency,
                        chargeAmount, "",
                        WalletDetailType.INFLOW, new Date());
                logger.debug("流水记录完成");
            }
            throw new BillingException(BillingException.PAY_AMOUNT_AERRO, "支付金额错误");
        }
        //如果支付金额大于订单支付金额，则支付成功，余额存入钱包
        if (chargeAmount > payAmount) {
            logger.info("如果支付金额大于订单支付金额，则支付成功，余额存入钱包");
            if (!billingOrder.getPayType().equals(PayType.THIRD)) {
                int balance = chargeAmount - payAmount;
                BillingWalletBalance billingWalletBalance = walletSerivce.depositWallet(billingOrder.getUserId(),
                        balance);
                logger.debug("余额存钱完成");
                //流水记录
                operateWalletDetail(billingWalletBalance.getWalletId(), orderId, balance, currency, balance,
                        billingOrder.getOrderDesc(), WalletDetailType.INFLOW, new Date());
                logger.debug("流水记录完成");
            }
        }
        //扣款成功，创建完整订单
        logger.info("开始订单创建记录");
        orderService.createOrder(orderId);
        logger.info("完成订单创建记录，开始发货");
        //根据订单信息发货
        orderService.deliverOrder(orderId);

    }

    /**
     * 流水操作（流入/流出 记录）.
     *
     * @param walletId         钱包ID
     * @param orderId          订单ID
     * @param amount           价格
     * @param currency         币种
     * @param currencyAmount   实际价格
     * @param detailDesc       流水描述
     * @param walletDetailType 操作（流入/流出）
     * @param operateDate      操作时间
     */
    public void operateWalletDetail(long walletId, String orderId, int amount, Currency currency, int
            currencyAmount, String detailDesc, WalletDetailType walletDetailType, Date operateDate) throws
            ServiceException {

        BillingWalletDetail billingWalletDetail = new BillingWalletDetail();
        billingWalletDetail.setWalletDetailid(tableSequenceGenerator.generate(TableSequenceConstant
                .PLATFORM_BILLING_WALLET_DETAIL));
        billingWalletDetail.setWalletId(walletId);
        billingWalletDetail.setBillingOrderId(orderId);
        billingWalletDetail.setAmount(amount);
        billingWalletDetail.setCurrencyAmount(currencyAmount);
        billingWalletDetail.setCurrency(currency);
        billingWalletDetail.setCreateTime(operateDate);
        billingWalletDetail.setDetailDesc(detailDesc);
        billingWalletDetail.setDetailType(walletDetailType);
        billingWalletDetailMapper.insertSelective(billingWalletDetail);
    }

    @Override
    public void walletPay(String orderId) throws ServiceException {

        BillingOrder billingOrder = cacheService
                .get(IcachedConstant.BILLING_ORDER_PARAMS + orderId, BillingOrder.class);

        if (billingOrder == null) {
            throw new BillingException(BillingException.PAY_ORDER_INVALID, "无效订单");
        }

        //根据productId获取商品信息
        BillingGoods billingGoods = goodsService.queryGoods(billingOrder.getGoodsCode());

        if (billingGoods == null) {
            throw new BillingException(BillingException.GOODS_NOT_EXIST, "商品配方不存在");
        }

        int payAmount = billingGoods.getGoodsAmount();//分
        int count = billingGoods.getGoodsCount();//商品宝石数量
        double discount = billingGoods.getDisaccount();

        //折扣计算
        if (discount < 1.0) {
            payAmount = (int) (payAmount * (1 - discount));
        }

        //余额查询
        BillingWalletBalance billingWalletBalance = walletSerivce.queryBalance(billingOrder.getUserId());

        if (billingWalletBalance == null) {
            logger.debug("没有钱包，临时创建");
            billingWalletBalance = new BillingWalletBalance();
            walletSerivce.addWallet(billingWalletBalance, billingOrder.getUserId(), 0);
        }

        //钱包扣款
        if (billingWalletBalance.getAmount() >= payAmount) {
            BillingWalletBalance withdrawWallet = walletSerivce.withdrawWallet(billingOrder.getUserId(), payAmount);
            operateWalletDetail(withdrawWallet.getWalletId(), orderId, payAmount, Currency.FANFANDOU, payAmount,
                    billingOrder.getOrderDesc(), WalletDetailType.OUTFLOW, new Date());
        } else {
            throw new BillingException(BillingException.WALLET_BALANCE_NOT_ENOUGH, "钱包余额不足");
        }
        //扣款成功，创建完整订单
        orderService.createOrder(orderId);
        //根据订单信息发货
        orderService.deliverOrder(orderId);
    }

    @Override
    public void purchaseByGem(int goodsId, int amount, GameCode gameCode, int areaId, long userId) throws ServiceException {
        BillingGoods goods = goodsService.queryGoodsById(goodsId);
        if (goods == null) {
            throw new BillingException(BillingException.GOODS_NOT_EXIST, "商品配方不存在");
        } else if (goods.getGoodsAmount() != amount) {
            throw new BillingException(BillingException.PAY_AMOUNT_AERRO, "商品价格错误");
        }
        //TODO 调用支付接口
        GoodsItemPackage goodsItemPackage = new GoodsItemPackage();

        List<GoodsItem> goodsItems = new ArrayList<>();
        //组装itemlist
        GoodsItem goodsItem = new GoodsItem();

        DataDictionary dataDictionary = null;
        try {
            dataDictionary = dataDictionaryService.getValueByUniform(DicKeyConstant.OPERATION_ITEM_ITEMTYPE, -1, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<DicItem> dicItems = JSON.parseArray(dataDictionary.getItemJson(), DicItem.class);
        for (DicItem dicItem : dicItems) {
            if (dicItem.getKey().equals(DicItemKeyConstant.GOODS_ITEM_TYPE_ITEM)) {
                goodsItem.setItemType(dicItem);
            }
        }
        goodsItemPackage.setValue(amount);
        goodsItem.setItemId(Integer.parseInt(goods.getGoodsCode()));
        goodsItems.add(goodsItem);
        //塞人pakcage中
        goodsItemPackage.setGoodsItems(goodsItems);

        operationDispatchService.routePurchaseByGem(gameCode, areaId, userId, goodsItemPackage);
    }

    @Override
    public void sendToycode(int gameId, int areaId, long userId,  int goodsId, String code) throws ServiceException {
        BillingGoods goods = goodsService.queryGoodsById(goodsId);
        DicItem dicItem = dataDictionaryService.getDicItemByConstant(DicKeyConstant.PLATFORM_PUSHINFO_PUSHTYPE,
                    DicItemKeyConstant.PLATFORM_PUSHINFO_PUSH_TOYS_CODE);
        operationDispatchService.pushMsgToClient(GameCode.getById(gameId), areaId, userId,
                dicItem ,Integer.parseInt(goods.getGoodsCode()), code);
    }
}
