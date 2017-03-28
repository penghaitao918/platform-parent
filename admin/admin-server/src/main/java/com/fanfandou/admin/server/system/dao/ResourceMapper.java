package com.fanfandou.admin.server.system.dao;

import com.fanfandou.admin.api.system.entity.ResEnum;
import com.fanfandou.admin.api.system.entity.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * author shengbo.
 * Description:菜单mapper.
 */
@Repository(value = "resourceMapper")
public interface ResourceMapper {

    List<Resource> selectAll();

    void isInsert(@Param("resCode")String resCode, @Param("url")String url, @Param("resType")ResEnum resType, @Param("createTime")Date createTime, @Param("available")Integer available);

    Resource selectById(int id);

    void delGame(int id);

    void updateGame(Resource res);

    List<Resource> getResByType(int typeId);

    Resource selByIdAndType(Map map);

    List<Resource> pageList(Map paramMap);

    int totalCount();
}
