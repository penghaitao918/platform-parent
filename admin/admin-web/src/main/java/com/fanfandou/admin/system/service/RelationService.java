package com.fanfandou.admin.system.service;

import com.fanfandou.admin.system.entity.Relation;
import com.fanfandou.common.entity.resource.GameCode;

import java.util.List;
import java.util.Map;

/**
 * Created by wangzhenwei on 2016/3/15.
 * Description 角色service接口
 */
public interface RelationService {

    /**
     * 根据内容查找关系id.
     */
    int selectId(int id1,int id2,int typeId);

    /**
     * 通过id1和typeId查找id2.
     * @param id1 user或role ID
     * @param typeId 类型Id
     */
    List<Integer> selectIdBy(int id1, int typeId);

    /**
     *  添加关联.
     *  type为1时，id1 = userId，id2 = roleId
     *  type为2时，id1 = roleId，id2 = promissionId
     *  type为3时，id1 = userId，id2 = promissionId
     */
    void addRelation(int id1, int id2, int idType);

    /**
     * 删除某个角色的某个权限or删除某个用户的某个角色or删除某个用户的某个权限.
     */
    void delRelation(int id);

    /**
     * ...更新关联关系.
     */
    void updRelation(Relation relation);

    /**
     * 查询所有关联关系.
     */
    List<Relation> selectAll();

    /**
     * 通过id查找关联关系.
     */
    Relation selRelationById(int id);

    /**
     * 为用户或者角色添加权限.
     */
    void addPermission(int userRoleId, int typeId, int actionId, int menuId);

    /**
     *  删除用户权限.
     */
    void delPermission(int userRoleId, int typeId, int actionId, int menuId);

    /**
     * 操作菜单权限.
     */
    void optPermission(int userRoleId, int typeId, int actionId, int menuId, boolean flag);

    /**
     * 查询资源权限.
     * @param userRoleId 用户角色id
     * @param typeId     类型id
     * @param resType    资源类型
     * @return 资源id集合
     */
    List<Integer> selResPermission(int userRoleId, int typeId, int resType);

    /**
     * 操作资源权限.
     */
    void optResPermission(int userRoleId, int typeId, String resIds, int resType);

    /**
     * 为用户添加角色.
     * @param id1  用户id
     * @param ids2 角色id集合
     */
    void addRole(int id1, String ids2);

    /**
     * 根据用户id查角色id集合.
     */
    List<Integer> selRoleId(int userId);

    /**
     * 操作用户角色，添加或者删除.
     * @param userId 用户id
     * @param roleIds 角色id集合
     */
    void optRole(int userId,String roleIds);

    /**
     * 操作父菜单id 为所有子菜单授权或移除权限.
     * @param userRoleId 角色或用户id
     * @param menuParentId 父菜单id
     * @param typeId 类型id
     * @param flag 判断是授权还是移除权限的标示
     */
    void optMenuParentId(int userRoleId,int menuParentId,int typeId,boolean flag);

    /**
     * 判断父菜单id里是否拥有全部权限.
     * @param userRoleId 用户角色id
     * @param typeId 类型id
     * @param menuParentId 父菜单id
     * @return true是 false否 所有权限
     */
    boolean parentMenuIsAll(int userRoleId,int typeId,int menuParentId);



}
