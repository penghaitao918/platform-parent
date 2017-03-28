package com.fanfandou.platform.serv.activity.dao;


import com.fanfandou.platform.api.activity.entity.PromoteAwardPackage;
import com.fanfandou.platform.api.activity.entity.PromoteAwardPackageExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PromoteAwardPackageMapper {
    int countByExample(PromoteAwardPackageExample example);

    int deleteByPrimaryKey(Integer packageId);

    int insert(PromoteAwardPackage record);

    int insertSelective(PromoteAwardPackage record);

    List<PromoteAwardPackage> selectByExample(PromoteAwardPackageExample example);

    PromoteAwardPackage selectByPrimaryKey(Integer packageId);

    int updateByExampleSelective(@Param("record") PromoteAwardPackage record, @Param("example") PromoteAwardPackageExample example);

    int updateByExample(@Param("record") PromoteAwardPackage record, @Param("example") PromoteAwardPackageExample example);

    int updateByPrimaryKeySelective(PromoteAwardPackage record);

    int updateByPrimaryKey(PromoteAwardPackage record);

    List<PromoteAwardPackage> pageList(Map paramMap);

    int totalCount(Map map);

    List<PromoteAwardPackage> getList(Map paramMap);
}