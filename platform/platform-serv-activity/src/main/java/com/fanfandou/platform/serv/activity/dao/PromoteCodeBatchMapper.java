package com.fanfandou.platform.serv.activity.dao;


import com.fanfandou.platform.api.activity.entity.PromoteCodeBatch;
import com.fanfandou.platform.api.activity.entity.PromoteCodeBatchExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PromoteCodeBatchMapper {
    int countByExample(PromoteCodeBatchExample example);

    int deleteByPrimaryKey(Integer batchId);

    int insert(PromoteCodeBatch record);

    int insertSelective(PromoteCodeBatch record);

    List<PromoteCodeBatch> selectByExample(PromoteCodeBatchExample example);

    PromoteCodeBatch selectByPrimaryKey(Integer batchId);

    int updateByExampleSelective(@Param("record") PromoteCodeBatch record, @Param("example") PromoteCodeBatchExample example);

    int updateByExample(@Param("record") PromoteCodeBatch record, @Param("example") PromoteCodeBatchExample example);

    int updateByPrimaryKeySelective(PromoteCodeBatch record);

    int updateByPrimaryKey(PromoteCodeBatch record);

    List<PromoteCodeBatch> pageList(Map paramMap);

    int totalCount(Map map);

}