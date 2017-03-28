package com.fanfandou.platform.web.billing.vo;

import com.fanfandou.platform.api.billing.entity.BillingGoods;

/**
 * Created by wudi.
 * Descreption:商品VO.
 * Date:2016/9/19
 */
public class GoodsVo {

    private int goodsId;

    private int gameId;

    private int goodsAmount;

    private String goodsCode;

    private int goodsCount;

    private String goodsDesc;

    private String goodsMarkTime;

    private String goodsName;

    private String relatedGoodsId;

    private double discount;

    /**
     * vo的转化.
     */
    public static GoodsVo convertGoodsVo(BillingGoods billingGoods) {
        GoodsVo goodsVo = new GoodsVo();
        goodsVo.setGoodsId(billingGoods.getGoodsId());
        goodsVo.setDiscount(billingGoods.getDisaccount());
        goodsVo.setGameId(billingGoods.getGameId());
        goodsVo.setGoodsAmount(billingGoods.getGoodsAmount());
        goodsVo.setGoodsCode(billingGoods.getGoodsCode());
        goodsVo.setGoodsCount(billingGoods.getGoodsCount());
        goodsVo.setGoodsDesc(billingGoods.getGoodsDesc());
        goodsVo.setGoodsName(billingGoods.getGoodsName());
        goodsVo.setRelatedGoodsId(billingGoods.getRelatedGoodsId());
        return goodsVo;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(int goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getGoodsMarkTime() {
        return goodsMarkTime;
    }

    public void setGoodsMarkTime(String goodsMarkTime) {
        this.goodsMarkTime = goodsMarkTime;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getRelatedGoodsId() {
        return relatedGoodsId;
    }

    public void setRelatedGoodsId(String relatedGoodsId) {
        this.relatedGoodsId = relatedGoodsId;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }
}
