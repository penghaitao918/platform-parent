package com.fanfandou.platform.api.billing.exception;

import com.fanfandou.common.exception.ServiceException;

/**
 * Created by wudi.
 * Descreption:支付相关异常处理.
 * Date:2016/4/11
 */
public class BillingException extends ServiceException {

    /*   钱包 1 - 20    */
    //钱包余额不足
    public static final int WALLET_BALANCE_NOT_ENOUGH = BASE_BILLING + 1;


    /*   order 21 - 50    */
    //pay 订单无效
    public static final int PAY_ORDER_INVALID = BASE_BILLING + 21;
    //pay 订单扣除失败
    public static final int PAY_ORDER_DEDUCTE_FAIL = BASE_BILLING + 22;
    //pay 订单已扣除
    public static final int PAY_ORDER_DEDUCTED = BASE_BILLING + 23;
    //pay 交付失败
    public static final int PAY_ORDER_DELIVERE_FAIL = BASE_BILLING + 24;
    //pay 订单已交付
    public static final int PAY_ORDER_DELIVERED = BASE_BILLING + 25;
    //pay 订单不存在
    public static final int PAY_ORDER_NOT_EXISTS = BASE_BILLING + 26;
    //订单为未付款
    public static final int PAY_ORDER_UNPAID = BASE_BILLING + 27;
    //支付金额错误
    public static final int PAY_AMOUNT_AERRO = BASE_BILLING + 28;
    //充值发货失败
    public static final int PAY_DILEVER_FAIL = BASE_BILLING + 29;


    //商品配方不存在
    public static final int GOODS_NOT_EXIST = BASE_BILLING + 51;
    //商品参数创建缺失
    public static final int GOODS_LACK_PARAMS = BASE_BILLING + 52;




    public BillingException(ServiceException e, String msg) {
        super(e, msg);
    }

    public BillingException(int val, String name) {
        super(val, name);
    }

    public BillingException(ServiceException e) {
        super(e);
    }

    public BillingException(int val) {
        super(val);
    }
}
