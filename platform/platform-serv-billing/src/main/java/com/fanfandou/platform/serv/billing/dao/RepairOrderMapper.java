package com.fanfandou.platform.serv.billing.dao;

import java.util.List;

import com.fanfandou.platform.api.billing.entity.RepairOrder;
import com.fanfandou.platform.api.billing.entity.RepairOrderExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairOrderMapper {
    int countByExample(RepairOrderExample example);

    int deleteByPrimaryKey(Integer repairOrderId);

    int insert(RepairOrder record);

    int insertSelective(RepairOrder record);

    List<RepairOrder> selectByExample(RepairOrderExample example);

    RepairOrder selectByPrimaryKey(Integer repairOrderId);

    int updateByExampleSelective(@Param("record") RepairOrder record, @Param("example") RepairOrderExample example);

    int updateByExample(@Param("record") RepairOrder record, @Param("example") RepairOrderExample example);

    int updateByPrimaryKeySelective(RepairOrder record);

    int updateByPrimaryKey(RepairOrder record);
}