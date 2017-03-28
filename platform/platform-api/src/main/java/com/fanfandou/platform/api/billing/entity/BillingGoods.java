package com.fanfandou.platform.api.billing.entity;

import com.fanfandou.common.entity.ActStatus;
import com.fanfandou.common.entity.ValidStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

public class BillingGoods implements Serializable {
    private Integer goodsId;
    //商品类型
    private int goodsType;
    //商城类型
    private ShopType shopType = ShopType.RMBSHOP;
    //商品名字
    private String goodsName;
    //商品描述
    private String goodsDesc;
    //商品图片
    private String goodsPic;
    //商品总价
    private Integer goodsAmount;
    //商品数量
    private Integer goodsCount;
    //商品单价
    private Integer unitPrice;
    //奖励包ID
    private String awardId;
    //游戏ID
    private Integer gameId;
    //渠道ID
    private Integer siteId;
    //区服ID（多选）EX:"1,2,3"
    private String areaIds;
    //商品CODE
    private String goodsCode;
    //上架时间
    private Date goodsMarkTime;
    //关联的商品ID
    private String relatedGoodsId;
    //折扣
    private double disaccount;
    //首充策略
    private FirstBuyPolicy firstBuyPolicy;
    //是否有效
    private ValidStatus validStatus = ValidStatus.VALID;
    //创建商品的时间
    private Date createTime;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public int getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(int goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc == null ? null : goodsDesc.trim();
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic == null ? null : goodsPic.trim();
    }

    public Integer getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(Integer goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getAwardId() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId == null ? null : awardId.trim();
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getAreaIds() {
        return areaIds;
    }

    public void setAreaIds(String areaIds) {
        this.areaIds = areaIds == null ? null : areaIds.trim();
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    public Date getGoodsMarkTime() {
        return goodsMarkTime;
    }

    public void setGoodsMarkTime(Date goodsMarkTime) {
        this.goodsMarkTime = goodsMarkTime;
    }

    public String getRelatedGoodsId() {
        return relatedGoodsId;
    }

    public void setRelatedGoodsId(String relatedGoodsId) {
        this.relatedGoodsId = relatedGoodsId == null ? null : relatedGoodsId.trim();
    }

    public double getDisaccount() {
        return disaccount;
    }

    public void setDisaccount(double disaccount) {
        this.disaccount = disaccount;
    }

    public ValidStatus getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(ValidStatus validStatus) {
        this.validStatus = validStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public FirstBuyPolicy getFirstBuyPolicy() {
        return firstBuyPolicy;
    }

    public void setFirstBuyPolicy(FirstBuyPolicy firstBuyPolicy) {
        this.firstBuyPolicy = firstBuyPolicy;
    }

    public ShopType getShopType() {
        return shopType;
    }

    public void setShopType(ShopType shopType) {
        this.shopType = shopType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}