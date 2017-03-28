package com.fanfandou.platform.web.billing.service;

import com.alibaba.fastjson.JSON;
import com.fanfandou.admin.api.system.entity.DataDictionary;
import com.fanfandou.admin.api.system.service.DataDictionaryService;
import com.fanfandou.common.constant.DicItemKeyConstant;
import com.fanfandou.common.constant.DicKeyConstant;
import com.fanfandou.common.entity.resource.DicItem;
import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.billing.entity.BillingGoods;
import com.fanfandou.platform.api.billing.entity.Currency;
import com.fanfandou.platform.api.billing.entity.GoodsItem;
import com.fanfandou.platform.api.billing.entity.GoodsItemPackage;
import com.fanfandou.platform.api.billing.entity.OrderParam;
import com.fanfandou.platform.api.billing.entity.ShopType;
import com.fanfandou.platform.api.billing.exception.BillingException;
import com.fanfandou.platform.api.billing.service.BillingService;
import com.fanfandou.platform.api.billing.service.GoodsService;
import com.fanfandou.platform.api.billing.service.OrderService;
import com.fanfandou.platform.api.billing.service.WalletSerivce;
import com.fanfandou.platform.api.game.service.GameAreaService;
import com.fanfandou.platform.api.game.service.OperationDispatchService;
import com.fanfandou.platform.api.user.entity.AccountType;
import com.fanfandou.platform.api.user.entity.UserAccount;
import com.fanfandou.platform.api.user.service.AccountService;
import com.fanfandou.platform.api.user.service.UserService;
import com.fanfandou.platform.web.billing.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wudi.
 * Descreption:支付服务实现调用.
 * Date:2016/5/18
 */
@Service
public class BillingServiceClient {

    @Autowired
    private BillingService billingService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private GameAreaService gameAreaService;

    @Autowired
    private OperationDispatchService operationDispatchService;





    /**
     * 创建订单ID
     * @param orderParam 生成ID所需参数实体.
     * @return 返回订单号
     */
    public String createOrderId(OrderParam orderParam) throws ServiceException {
        return orderService.createOrderId(orderParam);
    }

    /**
     * 三方支付.
     * @param orderId 我们自己的订单号.
     * @param reOrderId 三方订单号.
     * @param currency 币种ID.
     * @param amount 玩家支付金额(分).
     * @throws ServiceException ServiceException.
     */
    public void thirdCharge(String orderId, String reOrderId, Currency currency, int amount) throws ServiceException {
        billingService.charge(orderId, reOrderId, currency, amount);
    }

    /**
     * 钱包支付.
     * @param orderId 我们自己的订单号.
     * @throws ServiceException ServiceException.
     */
    public void walletCharge(String orderId) throws ServiceException {
        billingService.walletPay(orderId);
    }

    /**
     * 客户端查询商品列表.
     * @throws ServiceException ServiceException
     */
    public List<GoodsVo> queryGoods(int gameId, int siteId, String areaCode, long userId, int shopType) throws ServiceException {
        List<BillingGoods> billingGoodsList = goodsService.queryGoods(gameId, siteId, areaCode, userId, shopType);
        List<GoodsVo> goodsVos = new ArrayList<GoodsVo>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for (BillingGoods billingGoods : billingGoodsList) {
            GoodsVo goodsVo = new GoodsVo();
            goodsVo.setGameId(billingGoods.getGameId());
            goodsVo.setGoodsId(billingGoods.getGoodsId());
            goodsVo.setDiscount(billingGoods.getDisaccount());
            goodsVo.setGoodsAmount(billingGoods.getGoodsAmount());
            goodsVo.setGoodsCode(billingGoods.getGoodsCode());
            goodsVo.setGoodsDesc(billingGoods.getGoodsDesc());
            goodsVo.setGoodsMarkTime(simpleDateFormat.format(billingGoods.getGoodsMarkTime()));
            goodsVo.setGoodsName(billingGoods.getGoodsName());
            goodsVo.setGoodsCount(billingGoods.getGoodsCount());
            goodsVo.setRelatedGoodsId(billingGoods.getRelatedGoodsId());
            goodsVos.add(goodsVo);
        }
        return goodsVos;
    }

    /**
     * 通过宝石购买商品.
     * @param gameId 游戏ID
     * @param areaCode 区服CODE
     * @param userId 用户ID
     * @param money money
     * @param goodsId 道具itemId
     */
    public void gemPurchase(int gameId, String areaCode, long userId, int money, int goodsId) throws ServiceException {
        //获取areaId
        int areaId = gameAreaService.getGameAreaByCode(gameId, areaCode).getId();
        billingService.purchaseByGem(goodsId, money, GameCode.getById(gameId), areaId, userId);
    }


    /**
     * 查询单个商品.
     * @param goodsId 商品ID.
     */
    public GoodsVo getGoods(int goodsId) throws ServiceException {
        BillingGoods billingGoods = goodsService.queryGoodsById(goodsId);
        return GoodsVo.convertGoodsVo(billingGoods);
    }

    /**
     * 发送玩具激活码给游戏服.
     */
    public void sendToycode(int gameId, String areaCode, String userId, int itemId, String code) throws ServiceException {
        int areaId = gameAreaService.getGameAreaByCode(gameId, areaCode).getId();
        UserAccount userAccount = accountService.getAccount(AccountType.THIRD_OAUTH, userId);
        billingService.sendToycode(gameId, areaId, userAccount.getUserId(), itemId, code);
    }

}
