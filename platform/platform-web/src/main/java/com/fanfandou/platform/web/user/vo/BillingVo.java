package com.fanfandou.platform.web.user.vo;

import com.fanfandou.common.entity.resource.SiteCode;

import java.io.Serializable;

/**
 * Created by wudi.
 * Descreption:支付vo.
 * Date:2016/4/9
 */
public class BillingVo implements Serializable {

    private String orderId;

    private int site;

    private Integer gameId;

    private Integer areaId;

    private String channle;

    private Long userId;

    private String cpOrderId;

    private int payType;

    private String payExtraData;

    private String goodsId;

    private Integer currency;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public SiteCode getSite() {
        return SiteCode.getById(site);
    }

    public void setSite(int site) {
        this.site = site;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getChannle() {
        return channle;
    }

    public void setChannle(String channle) {
        this.channle = channle;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCpOrderId() {
        return cpOrderId;
    }

    public void setCpOrderId(String cpOrderId) {
        this.cpOrderId = cpOrderId;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getPayExtraData() {
        return payExtraData;
    }

    public void setPayExtraData(String payExtraData) {
        this.payExtraData = payExtraData;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getCurrency() {
        return currency;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }
}
