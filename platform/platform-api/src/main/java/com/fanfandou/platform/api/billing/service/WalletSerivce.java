package com.fanfandou.platform.api.billing.service;

import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.api.billing.entity.BillingWalletBalance;
import com.fanfandou.platform.api.billing.entity.BillingWalletDetail;
import com.fanfandou.platform.api.billing.entity.Currency;
import com.fanfandou.platform.api.billing.entity.WalletDetailType;

import java.util.Date;
import java.util.List;

/**
 * Created by wudi.
 * Descreption:钱包接口定义.
 * Date:2016/4/28
 */
public interface WalletSerivce {

    /**
     * 存钱.
     * @param userId 用户ID
     * @param amount 要存入的金额
     */
    BillingWalletBalance depositWallet(long userId, int amount) throws ServiceException;

    /**
     * 取钱.
     * @param userId 用户ID
     * @param amount 要取的金额
     */
    BillingWalletBalance withdrawWallet(long userId, int amount) throws ServiceException;

    /**
     * 钱包新增.
     */
    BillingWalletBalance addWallet(BillingWalletBalance billingWalletBalance, long userId, int amount) throws
            ServiceException;

    /**
     * 查询余额.
     * @param userId 用户ID
     */
    BillingWalletBalance queryBalance(long userId) throws ServiceException;


    /**
     * 流水查询.
     * @param walletId 钱包ID
     */
    List<BillingWalletDetail> queryWalletDetail(long walletId) throws ServiceException;
}
