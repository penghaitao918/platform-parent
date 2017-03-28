package com.fanfandou.platform.serv.billing.dao;

import java.util.List;

import com.fanfandou.platform.api.billing.entity.BillingWalletBalance;
import com.fanfandou.platform.api.billing.entity.BillingWalletBalanceExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingWalletBalanceMapper {
    int countByExample(BillingWalletBalanceExample example);

    int deleteByPrimaryKey(Long walletId);

    int insert(BillingWalletBalance record);

    int insertSelective(BillingWalletBalance record);

    List<BillingWalletBalance> selectByExample(BillingWalletBalanceExample example);

    BillingWalletBalance selectByPrimaryKey(Long walletId);

    BillingWalletBalance selectByUserId(Long userId);

    int updateByExampleSelective(@Param("record") BillingWalletBalance record, @Param("example") BillingWalletBalanceExample example);

    int updateByExample(@Param("record") BillingWalletBalance record, @Param("example") BillingWalletBalanceExample example);

    int updateByPrimaryKeySelective(BillingWalletBalance record);

    int updateByPrimaryKey(BillingWalletBalance record);
}