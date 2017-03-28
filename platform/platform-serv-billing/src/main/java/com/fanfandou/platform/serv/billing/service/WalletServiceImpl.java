package com.fanfandou.platform.serv.billing.service;

import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.sequence.TableSequenceGenerator;
import com.fanfandou.platform.api.billing.entity.BillingWalletBalance;
import com.fanfandou.platform.api.billing.entity.BillingWalletDetail;
import com.fanfandou.platform.api.billing.entity.BillingWalletDetailExample;
import com.fanfandou.platform.api.billing.entity.WalletDetailType;
import com.fanfandou.platform.api.billing.exception.BillingException;
import com.fanfandou.platform.api.billing.service.WalletSerivce;
import com.fanfandou.platform.api.constant.TableSequenceConstant;
import com.fanfandou.platform.serv.billing.dao.BillingWalletBalanceMapper;
import com.fanfandou.platform.serv.billing.dao.BillingWalletDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by wudi.
 * Descreption:钱包实现.
 * Date:2016/5/4
 */
@Service("walletService")
public class WalletServiceImpl extends BaseLogger implements WalletSerivce {

    @Autowired
    private BillingWalletBalanceMapper billingWalletBalanceMapper;

    @Autowired
    private BillingWalletDetailMapper billingWalletDetailMapper;

    @Autowired
    private TableSequenceGenerator tableSequenceGenerator;

    @Override
    public BillingWalletBalance depositWallet(long userId, int amount) throws ServiceException {
        BillingWalletBalance billingWalletBalance = billingWalletBalanceMapper.selectByUserId(userId);

        if (billingWalletBalance == null) {
            billingWalletBalance = new BillingWalletBalance();
            billingWalletBalance = addWallet(billingWalletBalance,userId, amount);
        }

        billingWalletBalance.setAmount(billingWalletBalance.getAmount() + amount);
        billingWalletBalance.setDepositDate(new Date());
        billingWalletBalanceMapper.updateByPrimaryKeySelective(billingWalletBalance);
        logger.debug("depositWallet complete");
        return billingWalletBalance;
    }

    @Override
    public BillingWalletBalance withdrawWallet(long userId, int amount) throws ServiceException {
        BillingWalletBalance billingWalletBalance = billingWalletBalanceMapper.selectByUserId(userId);
        if (billingWalletBalance == null) {
            billingWalletBalance = new BillingWalletBalance();
            billingWalletBalance = addWallet(billingWalletBalance,userId, amount);
        }

        if (billingWalletBalance.getAmount() < amount) {
            throw new BillingException(BillingException.WALLET_BALANCE_NOT_ENOUGH, "钱包余额不足");
        }

        billingWalletBalance.setAmount(billingWalletBalance.getAmount() - amount);
        billingWalletBalance.setLastWithdrawDate(new Date());
        billingWalletBalanceMapper.updateByPrimaryKeySelective(billingWalletBalance);
        logger.debug("withdrawWallet complete");
        return billingWalletBalance;
    }

    @Override
    public BillingWalletBalance addWallet(BillingWalletBalance billingWalletBalance, long userId,int amount) throws
            ServiceException {
        long seqValue = tableSequenceGenerator.generate(TableSequenceConstant
                .PLATFORM_BILLING_WALLET);
        billingWalletBalance.setWalletId(seqValue);
        billingWalletBalance.setUserId(userId);
        billingWalletBalance.setAmount(amount);
        billingWalletBalanceMapper.insertSelective(billingWalletBalance);
        logger.debug("addWallet complete");
        return billingWalletBalance;
    }

    @Override
    public BillingWalletBalance queryBalance(long userId) throws ServiceException {

        return billingWalletBalanceMapper.selectByUserId(userId);
    }



    @Override
    public List<BillingWalletDetail> queryWalletDetail(long walletId) throws ServiceException {

        BillingWalletDetailExample billingWalletDetailExample = new BillingWalletDetailExample();
        BillingWalletDetailExample.Criteria criteria = billingWalletDetailExample.createCriteria();
        criteria.andWalletIdEqualTo(walletId);
        logger.debug("queryWalletDetail complete");
        return billingWalletDetailMapper.selectByExample(billingWalletDetailExample);
    }
}
