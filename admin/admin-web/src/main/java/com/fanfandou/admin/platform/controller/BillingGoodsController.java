package com.fanfandou.admin.platform.controller;


import com.fanfandou.common.entity.ActStatus;
import com.fanfandou.common.entity.ValidStatus;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.billing.entity.BillingGoods;
import com.fanfandou.platform.api.billing.entity.ShopType;
import com.fanfandou.platform.api.billing.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


/**
 * author zhongliang.
 * Description:商品配方管理操作.
 */
@RestController
@RequestMapping(value = "/platform/billingGoods")
public class BillingGoodsController {


    @Autowired
    private GoodsService goodsService;

    /**
     * 跳转到商品列表页面
     *
     * @return 列表页面
     */
    @RequestMapping(value = "/billingGoodsInit")
    public ModelAndView toListMenu() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/platform/BillingGoodsList");
        return mav;
    }

    /**
     * 跳转到首充策略页面
     *
     * @return 首充页面
     */
    @RequestMapping(value = "/firstBuyPolicyInit")
    public ModelAndView firstBuyPolicyList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/platform/FirstBuyPolicy");
        return mav;
    }

    /**
     * 跳转到首充策略页面
     *
     * @return 首充页面
     */
    @RequestMapping(value = "/awardPackageInit")
    public ModelAndView awardPackage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/platform/AwardPackage");
        return mav;
    }

    /**
     * 跳转到商品添加页面
     *
     * @return 添加页面
     */
    @RequestMapping(value = "/billingGoodsAddInit")
    public ModelAndView toBillingAdd() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/platform/BillingGoodsAdd");
        return mav;
    }


    /**
     * 跳转到商品修改页面
     *
     * @return 修改页面
     */
    @RequestMapping(value = "/billingGoodsUpdateInit")
    public ModelAndView toBillingUpdate() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/platform/BillingGoodsUpdate");
        return mav;
    }

    /**
     * 跳转到商品编辑页面
     *
     * @return 修改页面
     */
    @RequestMapping(value = "/billingGoodsEditInit")
    public ModelAndView toBillingEdit() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/platform/BillingGoodsEdit");
        return mav;
    }


    /**
     * 奖励包查询.
     *//*
    @ResponseBody
    @RequestMapping("/awardPackage")
    public PageResult<AwardPackage> getAwardPackage(AwardPackage awardPackage, Page page) {

        List<AwardPackage> awardPackages=new ArrayList<AwardPackage>();
        AwardPackage awardPackage1=new AwardPackage();
        awardPackage1.setAwardId(1);
        awardPackage1.setAwardName("初级奖励");
        awardPackage1.setAwardType(1);
        awardPackage1.setAwardCount(5);
        awardPackage1.setAwardDesc("新手奖励");

        AwardPackage awardPackage2=new AwardPackage();
        awardPackage2.setAwardId(2);
        awardPackage2.setAwardName("中级奖励");
        awardPackage2.setAwardType(1);
        awardPackage2.setAwardCount(5);
        awardPackage2.setAwardDesc("新手奖励");

        AwardPackage awardPackage3=new AwardPackage();
        awardPackage3.setAwardId(3);
        awardPackage3.setAwardName("高级奖励");
        awardPackage3.setAwardType(1);
        awardPackage3.setAwardCount(5);
        awardPackage3.setAwardDesc("新手奖励");

        AwardPackage awardPackage4=new AwardPackage();
        awardPackage4.setAwardId(4);
        awardPackage4.setAwardName("特级奖励");
        awardPackage4.setAwardType(1);
        awardPackage4.setAwardCount(5);
        awardPackage4.setAwardDesc("新手奖励");

        AwardPackage awardPackage5=new AwardPackage();
        awardPackage5.setAwardId(5);
        awardPackage5.setAwardName("特殊奖励");
        awardPackage5.setAwardType(2);
        awardPackage5.setAwardCount(5);
        awardPackage5.setAwardDesc("新手奖励");

        AwardPackage awardPackage6=new AwardPackage();
        awardPackage6.setAwardId(6);
        awardPackage6.setAwardName("额外奖励");
        awardPackage6.setAwardType(2);
        awardPackage6.setAwardCount(5);
        awardPackage6.setAwardDesc("新手奖励");

        if( awardPackage.getAwardName() == "" && awardPackage.getAwardType() == -1) {

            if( page.getPageIndex() == 1){
                awardPackages.add(awardPackage1);
                awardPackages.add(awardPackage2);
                awardPackages.add(awardPackage3);
                awardPackages.add(awardPackage4);
                awardPackages.add(awardPackage5);

            } else {
                awardPackages.add(awardPackage6);

            }


        } else if ( awardPackage.getAwardName() != "" && awardPackage.getAwardType() == 1 )
        {
            awardPackages.add(awardPackage1);
            awardPackages.add(awardPackage2);
            awardPackages.add(awardPackage3);
            awardPackages.add(awardPackage4);

        } else if ( awardPackage.getAwardName() != "" && awardPackage.getAwardType() == 1 )
        {
            awardPackages.add(awardPackage5);
            awardPackages.add(awardPackage6);

        } else {
            awardPackages.add(awardPackage1);
            awardPackages.add(awardPackage2);
            awardPackages.add(awardPackage3);
        }
        PageResult<AwardPackage> pageResult= new PageResult<AwardPackage>();
        page.setTotalCount(6);
        pageResult.setPage(page);
        pageResult.setRows(awardPackages);

        return pageResult;
    }
*/

    /**
     * 分页查询
     *
     * @param gameId 游戏id
     * @param siteId 渠道id
     * @param page   page对象
     * @return 分页对象集合
     * @throws ServiceException
     */
    @ResponseBody
    @RequestMapping("/pageList")
    public PageResult<BillingGoods> getPageList(Integer gameId, Integer siteId, Page page, String goodsName) throws ServiceException {

        return this.goodsService.queryGoods(gameId, siteId, goodsName, page);
    }

    /**
     * 根据id删除.
     *
     * @param goodIds 物品id
     */
    @ResponseBody
    @RequestMapping(value = "/goodsDelete")
    public void goodsDelete(String goodIds) throws ServiceException {
        String[] idsList = goodIds.split(",");
        for (int i = 0; i < idsList.length; i++) {
            this.goodsService.delGoods(Integer.parseInt(idsList[i]));
        }

    }

    /**
     * 根据id查.
     *
     * @param id id
     */
    @ResponseBody
    @RequestMapping(value = "/getGoods/{id}")
    public BillingGoods edit(@PathVariable(value = "id") int id) throws ServiceException {
        BillingGoods billingGoods = goodsService.queryGoodsById(id);
        return billingGoods;
    }


    @RequestMapping(value = "/insert")
    public void insert(BillingGoods billingGoods, int valid, int shopTypeId) throws ServiceException {
        billingGoods.setValidStatus(ValidStatus.valueOf(valid));
        billingGoods.setShopType(ShopType.valueOf(shopTypeId));
        this.goodsService.createGoods(billingGoods);
    }


    @RequestMapping(value = "/update")
    public void update(BillingGoods billingGoods, int valid, int shopTypeId) throws ServiceException {
        billingGoods.setValidStatus(ValidStatus.valueOf(valid));
        billingGoods.setShopType(ShopType.valueOf(shopTypeId));
        this.goodsService.updateGoodsById(billingGoods);
    }

    @RequestMapping(value = "/firstBuyPolicyAdd")
    public void firstBuyPolicy(String operation, int operateCount, String goodsIds) throws ServiceException {
        List<Integer> list = new ArrayList<Integer>();
        String[] idsList = goodsIds.split(",");
        for (int i = 0; i < idsList.length; i++) {
            list.add(Integer.parseInt(idsList[i]));
        }
        this.goodsService.firstBuyGenerate(list, operation, operateCount);
    }

    @RequestMapping(value = "/packageAdd")
    public void packageAdd(int packageType, String packageDesc, int value, String goodsItemsIds, String goodsIds) throws ServiceException {
        List<Integer> list = new ArrayList<Integer>();
        String[] idsList = goodsIds.split(",");
        for (int i = 0; i < idsList.length; i++) {
            list.add(Integer.parseInt(idsList[i]));
        }

        List<Integer> goodsItemsIdlist = new ArrayList<Integer>();
        String[] goodsItems = goodsIds.split(",");
        for (int i = 0; i < goodsItems.length; i++) {
            goodsItemsIdlist.add(Integer.parseInt(goodsItems[i]));
        }

        this.goodsService.awardPackageGenerate(list, goodsItemsIdlist, packageType, packageDesc, value);
        System.out.print(packageDesc);
    }
}
