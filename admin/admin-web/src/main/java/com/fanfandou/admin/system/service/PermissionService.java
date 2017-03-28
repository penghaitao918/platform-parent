package com.fanfandou.admin.system.service;

import com.fanfandou.admin.system.entity.Permission;
import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.entity.resource.SiteCode;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;

import java.util.List;
import java.util.Map;

/**
 * Created by wangzhenwei on 2016/3/15.
 * Description 角色service接口
 */
public interface PermissionService {

    /**
     * 向权限列表新增一项权限.
     */
    void addPermission(Permission permission);

    /**
     * 从权限列表中删除一项权限.
     */
    void delPermission(int id);

    /**
     * 修改一项权限.
     */
    void updPermission(Permission stu);

    /**
     * 批量删除权限.
     */
    void delPermissionList(List<Integer> idList);

    /**
     * 查询所有权限列表.
     */
    List<Permission> selectAll();

    /**
     * 通过id查找权限.
     */
    Permission selPermissionById(int id);

    /**
     * 查询某个角色的所有权限.
     */
    List<Permission> selByRoleUserId(int id1, int type);

    /**
     * 从数据库模糊查询出本页数据并排序.
     */
    PageResult<Permission> getPageList(Page page);

    /**
     * 根据资源id和操作id查询权限.
     */
    Permission selPerByResIdAndActionId(int resourceId, int actionId);

    /**
     * 根据资源id和操作id查询权限id.
     */
    int getPerIdByResIdAndActionId(int resourceId, int actionId);

    /**
     * 获取权限id.
     */
    int getPerId(int resourceId, int actionId);

    /**
     * 查询actionId.
     *
     * @param id1        用户或者角色Id
     * @param typeId     类型Id
     * @param resourceId 资源Id或者菜单Id
     * @return actionId集合
     */
    List<Integer> selActionId(int id1, int typeId, int resourceId);

    /**
     * 获取游戏资源.
     */
    Map<Integer, GameCode> getGameMap();

    /**
     * 获取渠道资源.
     */
    Map<Integer, SiteCode> getSiteMap();


}
