package com.fanfandou.platform.serv.billing.dao;

import java.util.List;

import com.fanfandou.platform.api.billing.entity.BillingWalletDetail;
import com.fanfandou.platform.api.billing.entity.BillingWalletDetailExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingWalletDetailMapper {
    int countByExample(BillingWalletDetailExample example);

    int deleteByPrimaryKey(Long walletDetailid);

    int insert(BillingWalletDetail record);

    int insertSelective(BillingWalletDetail record);

    List<BillingWalletDetail> selectByExample(BillingWalletDetailExample example);

    BillingWalletDetail selectByPrimaryKey(Long walletDetailid);

    int updateByExampleSelective(@Param("record") BillingWalletDetail record, @Param("example") BillingWalletDetailExample example);

    int updateByExample(@Param("record") BillingWalletDetail record, @Param("example") BillingWalletDetailExample example);

    int updateByPrimaryKeySelective(BillingWalletDetail record);

    int updateByPrimaryKey(BillingWalletDetail record);
}