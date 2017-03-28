package com.fanfandou.admin.system.dao;

import com.fanfandou.admin.system.entity.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wangzhenwei on 2016/3/15.
 * Description 权限dao
 */
@Repository(value = "permissionMapper")
public interface PermissionMapper {

    List<Permission> selectAll();

    /**
     * 查询一个角色or用户对应的权限.
     */
    List<Permission> selByRoleUserId(int id1, int type);

    /**
     * 查询操作id.
     */
    List<Integer> selectActionId(Map map);

    /**
     * 增.
     */
    void insert(Permission permission);

    /**
     * 删.
     */
    void delete(int id);

    /**
     * 改.
     */
    void update(Permission permission);

    /**
     * selectById.
     */
    Permission selectById(int id);

    /**
     * 分页.
     */
    List<Permission> pageList(Map paramMap);

    /**
     * 分页数量.
     */
    int totalCount();

    /**
     * 通过资源id和操作id查找权限.
     */
    Permission selPerByResIdAndActionId(int resourceId, int actionId);
}
