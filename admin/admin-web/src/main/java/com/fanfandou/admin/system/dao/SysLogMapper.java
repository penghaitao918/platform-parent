package com.fanfandou.admin.system.dao;

import com.fanfandou.admin.system.entity.SysLog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wangzhenwei on 2016/3/15.
 * Description 角色dao
 */
@Repository(value = "sysLogMapper")
public interface SysLogMapper {

    List<SysLog> selectAll();

    void insert(SysLog role);

    void delete(int id);

    void update(SysLog role);

    SysLog selectById(int id);

    /**
     * 分页.
     */
    List<SysLog> pageList(Map paramMap);

    /**
     * 分页数量.
     */
    int totalCount(Map map);
}
