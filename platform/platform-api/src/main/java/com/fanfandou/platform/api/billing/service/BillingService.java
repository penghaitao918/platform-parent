package com.fanfandou.platform.api.billing.service;

import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.billing.entity.Currency;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wudi.
 * Descreption:交易总接口定义.
 * Date:2016/4/28
 */
public interface BillingService {

    /**
     * 正常扣款支付.
     */
    @Transactional(rollbackFor = {ServiceException.class, RuntimeException.class})
    void charge(String orderId, String srcOrderId, Currency currency, int chargeAmount) throws ServiceException;

    /**
     * 钱包支付.
     */
    @Transactional(rollbackFor = {ServiceException.class, RuntimeException.class})
    void walletPay(String orderId) throws ServiceException;

    void purchaseByGem(int goodsId, int amount, GameCode gameCode, int areaId, long userId) throws ServiceException;

    /**
     * 发送玩具激活码.
     */
    void sendToycode(int gameId, int areaId, long userId, int goodsId, String code) throws ServiceException;
}
