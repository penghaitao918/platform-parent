package com.fanfandou.admin.system.service.impl;

import com.fanfandou.admin.system.dao.RoleMapper;
import com.fanfandou.admin.system.entity.Role;
import com.fanfandou.admin.system.service.RoleService;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangzhenwei on 2016/3/15.
 * Description service实现类
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询所有角色.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<Role> selectAll() {
        List<Role> list = this.roleMapper.selectAll();
        List<Role> roleList = new ArrayList<>();
        for (Role role : list) {
            if (role.getAvailable() == 1) {
                roleList.add(role);
            }
        }
        return roleList;
    }

    /**
     * 获取分页数据
     * 模糊查询，分页，排序.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public PageResult<Role> getPageList(String roleName, Page page) {
        if (page.getOrder() == null || page.getOrder().equals("")) {
            page.setOrder("id");
        }
        if (page.getSort() == null || page.getSort().equals("")) {
            page.setSort("desc");
        }
        String str = '%' + roleName + '%';
        int num1 = (page.getPageIndex() - 1) * page.getPageSize();
        Map paramMap = new HashMap();
        paramMap.put("role", str);
        paramMap.put("str1", page.getOrder());
        paramMap.put("str2", page.getSort());
        paramMap.put("num1", num1);
        paramMap.put("num2", page.getPageSize());

        List<Role> list = this.roleMapper.pageList(paramMap);
        int totalCount = this.roleMapper.totalCount();
        page.setTotalCount(totalCount);
        PageResult<Role> pageResult = new PageResult<>();
        pageResult.setPage(page);
        pageResult.setRows(list);
        return pageResult;
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<Role> selRoleByUId(int id1, int type) {
        return this.roleMapper.selRoleByUId(id1, type);
    }

    /**
     * 添加角色.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void addRole(Role role) {
        Date now = new Date();
        role.setCreateTime(now);
        role.setAvailable(1);
        this.roleMapper.insert(role);
    }

    /**
     * 删除角色.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void delRole(List<Integer> idList) {
        for (int i = 0; i < idList.size(); i++) {
            int id = idList.get(i);
            this.roleMapper.delete(id);
        }
    }

    /**
     * 更新角色.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void updRole(Role role) {
        Role roleOld = this.selRoleById(role.getId());
        role.setCreateTime(roleOld.getCreateTime());
        this.roleMapper.update(role);
    }

    /**
     * 通过id查找角色.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public Role selRoleById(int id) {
        return this.roleMapper.selectById(id);
    }

    /**
     * 一键失效和开启.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void invalid(String ids, int state) {
        String []userIds = ids.split(",");
        for (String id : userIds) {
            Role role = this.selRoleById(Integer.parseInt(id));
            if (state == 0 || state == 1) {
                role.setAvailable(state);
            }
            this.updRole(role);
        }
    }
}
