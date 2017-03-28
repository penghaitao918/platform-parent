package com.fanfandou.platform.api.billing.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.core.annotation.Order;

import java.io.Serializable;
import java.util.Date;

public class BillingOrder implements Serializable {
    private Long id;

    private String orderId;

    private String reOrderId;

    private int siteId;

    private int gameId;

    private Integer areaId;

    private Long userId;

    private Long roleId;

    private PayType payType = PayType.THIRD;

    private String goodsCode;

    private Integer goodsCount;

    private Currency currency = Currency.CNY;

    private int amount;

    private int payAmount;

    private String orderDesc;

    private OrderStatus orderStatus = OrderStatus.UNPAID;

    private PayStatus payStatus = PayStatus.UNPAID;

    private Date createTime;

    private String createIp;

    private Date payTime;

    private int version;

    public static BillingOrder convertBillingOrder(OrderParam orderParam) {
        BillingOrder billingOrder = new BillingOrder();
        billingOrder.setUserId(orderParam.getUserId());
        billingOrder.setSiteId(orderParam.getSiteId());
        billingOrder.setGameId(orderParam.getGameId());
        billingOrder.setCurrency(orderParam.getCurrency());
        billingOrder.setGoodsCode(orderParam.getGoodsCode());
        billingOrder.setGoodsCount(orderParam.getGoodsCount());
        billingOrder.setGoodsCode(orderParam.getGoodsCode());
        billingOrder.setCreateIp(orderParam.getIpAddress());
        billingOrder.setPayType(orderParam.getPayType());
        billingOrder.setCreateTime(new Date());
        billingOrder.setOrderDesc(orderParam.getOrderDesc());
        if (orderParam.getReOrderId() != null) {
            billingOrder.setReOrderId(orderParam.getReOrderId());
        }
        return billingOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getReOrderId() {
        return reOrderId;
    }

    public void setReOrderId(String reOrderId) {
        this.reOrderId = reOrderId == null ? null : reOrderId.trim();
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(int payAmount) {
        this.payAmount = payAmount;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc == null ? null : orderDesc.trim();
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public PayStatus getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(PayStatus payStatus) {
        this.payStatus = payStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp == null ? null : createIp.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}