package com.fanfandou.platform.serv.billing.dao;

import java.util.List;
import java.util.Map;

import com.fanfandou.platform.api.billing.entity.BillingOrder;
import com.fanfandou.platform.api.billing.entity.BillingOrderExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingOrderMapper {
    int countByExample(BillingOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BillingOrder record);

    int insertSelective(BillingOrder record);

    int totalCount(Map map);

    List<BillingOrder> selectByExample(BillingOrderExample example);

    List<BillingOrder> getOrdersbyselective(Map map);

    BillingOrder selectByPrimaryKey(Long id);

    BillingOrder selectByOrderId(String orderId);

    int updateByExampleSelective(@Param("record") BillingOrder record, @Param("example") BillingOrderExample example);

    int updateByExample(@Param("record") BillingOrder record, @Param("example") BillingOrderExample example);

    int updateByPrimaryKeySelective(BillingOrder record);

    int updateByPrimaryKey(BillingOrder record);

    int updateByOrderIdSelective(BillingOrder record);
}