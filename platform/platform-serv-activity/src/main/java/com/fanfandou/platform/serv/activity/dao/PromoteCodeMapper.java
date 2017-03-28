package com.fanfandou.platform.serv.activity.dao;

import com.fanfandou.platform.api.activity.entity.PromoteCode;
import com.fanfandou.platform.api.activity.entity.PromoteCodeExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PromoteCodeMapper {
    int countByExample(PromoteCodeExample example);

    int deleteByPrimaryKey(Long codeId);

    int insert(PromoteCode record);

    int insertSelective(PromoteCode record);

    List<PromoteCode> selectByExample(PromoteCodeExample example);

    PromoteCode selectByPrimaryKey(Long codeId);

    int updateByExampleSelective(@Param("record") PromoteCode record, @Param("example") PromoteCodeExample example);

    int updateByExample(@Param("record") PromoteCode record, @Param("example") PromoteCodeExample example);

    int updateByPrimaryKeySelective(PromoteCode record);

    int updateByPrimaryKey(PromoteCode record);

    List<PromoteCode> pageList(Map paramMap);

    int totalCount(Map map);
}