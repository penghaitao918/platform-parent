package com.platform.serv.billing.service;

import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.entity.ActStatus;
import com.fanfandou.common.entity.ValidStatus;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.service.cache.CacheService;
import com.fanfandou.platform.api.billing.entity.BillingGoods;
import com.fanfandou.platform.api.billing.entity.BillingOrder;
import com.fanfandou.platform.api.billing.entity.Currency;
import com.fanfandou.platform.api.billing.entity.FirstBuyPolicy;
import com.fanfandou.platform.api.billing.entity.GoodsItemPackage;
import com.fanfandou.platform.api.billing.entity.OrderParam;
import com.fanfandou.platform.api.billing.service.BillingService;
import com.fanfandou.platform.api.billing.service.GoodsService;
import com.fanfandou.platform.api.billing.service.OrderService;
import com.fanfandou.platform.api.billing.service.WalletSerivce;
import com.fanfandou.platform.api.constant.IcachedConstant;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wudi.
 * Descreption:支付单元测试.
 * Date:2016/5/10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/*.xml")
public class BillingTest extends BaseLogger {

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private BillingService billingService;

    @Autowired
    private WalletSerivce walletSerivce;

    @Autowired
    private CacheService cacheService;

    private List<String> orders;

    @Test
    public void createOrder() throws ServiceException {
        orders = new ArrayList<String>();
        // for (int i = 0; i < 10; i++) {
        OrderParam orderParam = new OrderParam();
        orderParam.setUserId(10010);
        orderParam.setSiteId(1);
        orderParam.setGameId(1);
        orderParam.setAmount(100);
        orderParam.setCurrency(Currency.FANFANDOU.getId());
        orderParam.setGoodsCode("pro001");
        orderParam.setIpAddress("127.0.0.1");
        orderParam.setOrderDesc("这是第" + 20 + "条测试订单");
        orderParam.setPayAmount(100);
        //orderParam.setPayType(PayType.FANFANDOU);
        orderParam.setGoodsCount(1000);
        orderParam.setReOrderId("10086");
        String orderId = orderService.createOrderId(orderParam);
        System.out.println("orderId:" + orderId);
        orders.add(orderId);
        // }

        /*for (int i =0 ; i < orders.size(); i++) {
            billingService.charge(orders.get(i), "sdg125521245", Currency.FANFANDOU, 101);
        }*/
    }

    @Test
    public void test1() {
        String str = "";
        String []splidStr = str.split(",");
        for (int i=0 ; i< splidStr.length; i++){
            logger.info("test1" + i);
        }
    }

    @Test
    public void createOrderComplete() throws ServiceException {
        orderService.createOrder("2016056531");
    }

    @Test
    public void getOrdersTest() throws ServiceException {
        BillingOrder billingOrder = orderService.getOrders("2016056531");
        System.out.println("order counts = " + billingOrder.getPayAmount());
    }

    @Test
    public void getOrdersByPage() throws Exception {
        Page curPage = new Page();
        curPage.setPageIndex(1);//第几页
        curPage.setPageSize(6);//多少条数据
        curPage.setSort("desc");//排序升降
        curPage.setOrder("create_time");//根据什么排序

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date from = sdf.parse("2016-05-12 10:00:13");
        Date to = sdf.parse("2016-05-12 11:11:03");

        PageResult pageResult = orderService.getOrders(10010, 1, 1, 1, curPage, from, null);
        System.out.println("搜索到的条目：" + pageResult.getRows().size());
        List<BillingOrder> orders = pageResult.getRows();
        for (BillingOrder order : orders) {
            System.out.println("orderDesc:" + order.getOrderDesc());
        }
    }


    public void Base64Test() {
        String ba64 = "cXdldWlxd2V1aXF3MTIzJjk4Nzg1NA==";
        String decodeBa64 = Base64.decodeBase64(ba64).toString();
        System.out.println("Base64Test = " + decodeBa64);

    }

    @Test
    public void walletPay() throws ServiceException {
        billingService.walletPay("20160584221");
    }

    @Test
    public void charge() throws ServiceException {
        billingService.charge("2016058151", "sdg125521245", Currency.FANFANDOU, 101);

    }

    @Test
    public void goodsTest() throws ServiceException {
        BillingGoods billingGoods = new BillingGoods();
        billingGoods.setAreaIds("1,2,3");
        billingGoods.setDisaccount(1);
        billingGoods.setGameId(1);
        billingGoods.setCreateTime(new Date());
        billingGoods.setAwardId("award.06");
        billingGoods.setGoodsAmount(700);
        billingGoods.setGoodsCode("gvg.06");
        billingGoods.setGoodsCount(7000);
        billingGoods.setGoodsDesc("700元商品");
        billingGoods.setGoodsMarkTime(new Date());
        billingGoods.setGoodsName("700元商品");
        billingGoods.setGoodsType(1);
        billingGoods.setRelatedGoodsId("re.gvg.06");
        billingGoods.setSiteId(9);
        billingGoods.setUnitPrice(10);
        billingGoods.setValidStatus(ValidStatus.VALID);
        goodsService.createGoods(billingGoods);
    }

    @Test
    public void updateGoodsTest() throws ServiceException {
        BillingGoods billingGoods = new BillingGoods();
        billingGoods.setAreaIds("1,2");
        //billingGoods.setDisaccount(1);
        billingGoods.setGoodsId(16);
        goodsService.updateGoodsById(billingGoods);
    }

    @Test
    public void queryGoodsByTool() throws ServiceException {
        Page curPage = new Page();
        curPage.setPageIndex(1);//第几页
        curPage.setPageSize(6);//多少条数据
        curPage.setSort("desc");//排序升降
        curPage.setOrder("create_time");//根据什么排序int gameId, int siteId, String goodsName, int goodsTypeId, int goodsAmount, String goodsCode, Page page
        PageResult pageResult = goodsService.queryGoods(1, 0, "",curPage);
        System.out.println("queryGoodsByTool = " + ((BillingGoods)pageResult.getRows().get(0)).getShopType());
    }

    @Test
    public void queryOneGoods() throws ServiceException {
        BillingGoods billingGoods = goodsService.queryGoodsById(11);
        System.out.println(billingGoods.toString());
    }

    @Test
    public void queryByGameId() throws ServiceException {
        List<BillingGoods> goodsList = goodsService.queryGoodsByGameId(1);
        System.out.printf("queryByGameId >>> " + goodsList.get(0).toString());
    }

    @Test
    public void queryGoods() throws ServiceException {
        //增加首充概念,改玩家为首充
        cacheService.put(IcachedConstant.BILLING_IS_FIRSTBUY + "gvg.01" + 1009857, true);
        //配置首充策略
        FirstBuyPolicy firstBuyPolicy = new FirstBuyPolicy();
        firstBuyPolicy.setOperateCount(500);
        firstBuyPolicy.setFirstBuyOperation("subtract");
        cacheService.put(IcachedConstant.BILLING_FIRSTBUY_POLICY + "gvg.01", firstBuyPolicy);
        //查询
        List<BillingGoods> goodsList = goodsService.queryGoods(1, 9,"1", 1009857,1);
        for (BillingGoods billingGoods : goodsList) {
            logger.debug("queryGoods 商品名称 = " + billingGoods.getGoodsName() + "宝石数量:" + billingGoods.getGoodsCount());
            FirstBuyPolicy policy = billingGoods.getFirstBuyPolicy();
            if (policy != null) {
                logger.debug("queryGoods 首充策略操作 = " + policy.getFirstBuyOperation());
            }

        }

    }

    /**
     * 生成首充策略
     */
    @Test
    public void putFirstBuyPolicy() {
        BillingGoods billingGoods = new BillingGoods();

        int goodsId = 1;
        //生成策略ID 常量 + goodsId
        String policyId = IcachedConstant.BILLING_FIRSTBUY_POLICY_ID + goodsId;
        //配置首充策略
        FirstBuyPolicy firstBuyPolicy = new FirstBuyPolicy();
        firstBuyPolicy.setFirstBuyId(policyId);
        firstBuyPolicy.setFirstBuyOperation("*");
        firstBuyPolicy.setOperateCount(2);
        //存入缓存。 Key = 常量 + goodsId
        cacheService.put(IcachedConstant.BILLING_FIRSTBUY_POLICY + goodsId, firstBuyPolicy);

        //生成物品奖励包ID Key = 常量 + goodsId
        String packageId = IcachedConstant.BILLING_FIRST_AWARD_PACKAGE_ID + goodsId;//如果是非首充物品包：IcachedConstant.BILLING_AWARD_PACKAGE_ID + goodsId
        billingGoods.setAwardId(packageId);
        //生成物品奖励包
        GoodsItemPackage goodsItemPackage = new GoodsItemPackage();
        goodsItemPackage.setAwardPackageId(packageId);
        goodsItemPackage.setPackageType(1);
        goodsItemPackage.setValue(1000);
       // iCached.put(IcachedConstant.BILLING_AWARD_PACKAGE + goodsId, goodsItemPackage);//正常非首充奖励包：

        //首充策略ID 与 奖励包相关联
        cacheService.put(policyId, goodsItemPackage);
    }

    @Test
    public void getFirstBuyPolicy() throws ServiceException {
        int goodsId = 1;
        int gameId = 1;
        int siteId = 4;
        String areaId = "52";
        long userId = 10086;
        //返回给客户端信息
        List<BillingGoods> billingGoodses = goodsService.queryGoods(gameId, siteId, areaId, userId,1);
        logger.info("getFirstBuyPolicy billingGoodsesSize>> " + billingGoodses.size());
        for (BillingGoods billingGoods : billingGoodses) {
            logger.debug("getFirstBuyPolicy >> goodsName = " + billingGoods.getGoodsName());
            if (billingGoods.getFirstBuyPolicy() != null) {
                logger.debug("getFirstBuyPolicy policy = " + billingGoods.getFirstBuyPolicy().getFirstBuyOperation());
                //后台读取首充信息
                //第一步先根据商品ID拿到首充策略
                FirstBuyPolicy firstBuyPolicy = billingGoods.getFirstBuyPolicy();
                //拿到首充策略ID
                String firstPolicyId = firstBuyPolicy.getFirstBuyId();
                logger.debug("getFirstBuyPolicy >>> " + firstBuyPolicy.getFirstBuyOperation());

                //根据策略ID拿到物品包
                GoodsItemPackage goodsItemPackage = cacheService.get(firstPolicyId, GoodsItemPackage.class);
                logger.debug("getFirstBuyPolicy goodsItemPackage>>> " + goodsItemPackage.getValue());
            } else {
                logger.info("getFirstBuyPolicy >> 没有该首充策略");
            }
        }


    }

    /**
     * 是否支持单笔首充.
     */
    @Test
    public void firstBuySingle(){
        int gameId = 1;
        int siteId = 4;
        int areaId = 52;
        String firstBuySingle = IcachedConstant.BILLING_IS_FIRST_SINGLE + gameId + siteId + areaId;
        cacheService.put(firstBuySingle,false);
    }

    @Test
    public void parsetest() {
        long time = 1474271145000L;
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("现在的时间为：" + sdf.format(date));
    }

    /**
     * 首充重置.
     */
    @Test
    public void resetFirstPay() {
        //根据gameId, siteId, areaId, goodsid, userId 来重置首充
        /*int gameId = 1;
        String siteIds = "1,2,3";
        String areaIds = "1,2,3";
        long userId = 10086;
        String goodsIds = "1,2,3";
        //首充判断的key规则为
        //String isFirstBuy = String.valueOf(iCached.get(IcachedConstant
              //  .BILLING_IS_FIRSTBUY + gameId + siteId + areaId + goodsId + userId));
        String firstBuyConstant = IcachedConstant.BILLING_IS_FIRSTBUY + gameId;
        String[] siteId = siteIds.split(",");
        for (int site = 0;site < siteId.length; site++) {
            for (int area = 0; area < areaIds.length(); area++) {
                question : 1，钱包支付是否只要传订单号就OK ， 2：发货的时候是根据订单的价格还是根据商品价格
                3，首充倍数赠送，是根据客户端的订单发货，还是获取策略，据此发货
            }
        }*/
        cacheService.del("aaa");
        logger.debug("清完缓存aaa");

    }


}
