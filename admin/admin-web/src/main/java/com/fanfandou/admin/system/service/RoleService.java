package com.fanfandou.admin.system.service;

import com.fanfandou.admin.system.entity.Role;
import com.fanfandou.admin.util.Log;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;

import java.util.List;

/**
 * Created by wangzhenwei on 2016/3/15.
 * Description 角色service接口
 */
public interface RoleService {


    /**
     * 添加角色.
     */
    void addRole(Role role);

    /**
     * 删除角色.
     */
    void delRole(List<Integer> idList);

    /**
     * 更新角色.
     */
    void updRole(Role stu);

    /**
     * 查询所有角色.
     */
    List<Role> selectAll();

    /**
     * 从数据库模糊查询出本页数据并排序.
     */
    PageResult<Role> getPageList(String roleName,Page page);

    /**
     * 查询一个用户的角色信息.
     */
    List<Role> selRoleByUId(int id1,int type);

    /**
     * 通过id查找角色.
     */
    Role selRoleById(int id);

    /**
     * 一键解锁，锁定.
     * @param ids id集合
     * @param state 状态id
     */
    @Log
    void invalid(String ids,int state);
}
