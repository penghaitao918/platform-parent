package com.fanfandou.admin.system.dao;

import com.fanfandou.admin.system.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wangzhenwei on 2016/3/15.
 * Description 角色dao
 */
@Repository(value = "roleMapper")
public interface RoleMapper {

    List<Role> selectAll();

    /**
     * 通过userId查找角色.
     */
    List<Role> selRoleByUId(int id1,int type);

    List<Role> pageList(Map paramMap);

    int totalCount();

    void insert(Role role);

    void delete(int id);

    void update(Role role);

    Role selectById(int id);


}
