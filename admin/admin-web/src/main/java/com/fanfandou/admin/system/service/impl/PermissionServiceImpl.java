package com.fanfandou.admin.system.service.impl;

import com.fanfandou.admin.api.system.service.ResService;
import com.fanfandou.admin.system.dao.PermissionMapper;
import com.fanfandou.admin.system.entity.Permission;
import com.fanfandou.admin.system.service.PermissionService;
import com.fanfandou.common.entity.resource.GameCode;
import com.fanfandou.common.entity.resource.SiteCode;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.service.cache.CacheService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangzhenwei on 2016/3/15.
 * Description service实现类
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private ResService resService;

    /**
     * 获取分页数据
     * 模糊查询，分页，排序.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public PageResult<Permission> getPageList(Page page) {
        if (page.getOrder() == null) {
            page.setOrder("id");
        }
        if (page.getSort() == null) {
            page.setSort("asc");
        }
        int num1 = (page.getPageIndex() - 1) * page.getPageSize();
        Map paramMap = new HashMap();
        paramMap.put("str1", page.getOrder());
        paramMap.put("str2", page.getSort());
        paramMap.put("num1", num1);
        paramMap.put("num2", page.getPageSize());

        List<Permission> list = this.permissionMapper.pageList(paramMap);
        int totalCount = this.permissionMapper.totalCount();
        page.setTotalCount(totalCount);
        PageResult<Permission> pageResult = new PageResult<>();
        pageResult.setPage(page);
        pageResult.setRows(list);
        return pageResult;
    }

    /**
     * 查询所有权限列表.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<Permission> selectAll() {
        return this.permissionMapper.selectAll();
    }

    /**
     * 查询某个角色的所有权限.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<Permission> selByRoleUserId(int id1, int type) {
        return this.permissionMapper.selByRoleUserId(id1, type);
    }

    /**
     * 批量删除权限.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void delPermissionList(List<Integer> idList) {
        for (int i = 0; i < idList.size(); i++) {
            int id = idList.get(i);
            this.permissionMapper.delete(id);
        }
    }

    /**
     * 新增一项权限.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void addPermission(Permission permission) {
        permission.setCreateTime(new Date());
        permission.setAvailable(1);
        this.permissionMapper.insert(permission);
    }

    /**
     * 从权限列表中删除一项权限.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void delPermission(int id) {
        this.permissionMapper.delete(id);
    }

    /**
     * 修改一项权限.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void updPermission(Permission permission) {
        Permission roleOld = this.selPermissionById(permission.getId());
        permission.setCreateTime(roleOld.getCreateTime());
        this.permissionMapper.update(permission);
    }

    /**
     * 通过id查找权限.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public Permission selPermissionById(int id) {
        return this.permissionMapper.selectById(id);
    }

    /**
     * 根据资源id和操作id查询权限.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public Permission selPerByResIdAndActionId(int resourceId, int actionId) {
        return this.permissionMapper.selPerByResIdAndActionId(resourceId, actionId);
    }

    /**
     * 根据资源id和操作id查询权限id.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public int getPerIdByResIdAndActionId(int resourceId, int actionId) {
        try {
            Permission permission = this.selPerByResIdAndActionId(resourceId, actionId);
            if (permission != null) {
                return permission.getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取权限id.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public int getPerId(int resourceId, int actionId) {
        int id = this.getPerIdByResIdAndActionId(resourceId, actionId);
        if (id == 0) {
            Permission permission = new Permission();
            permission.setResourceId(resourceId);
            permission.setActionId(actionId);
            this.addPermission(permission);
            return this.getPerIdByResIdAndActionId(resourceId, actionId);
        }
        return id;
    }

    /**
     * 查询actionId.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<Integer> selActionId(int id1, int typeId, int resourceId) {
        Map map = new HashMap();
        map.put("id1", id1);
        map.put("typeId", typeId);
        map.put("resourceId", resourceId);
        List<Integer> list = this.permissionMapper.selectActionId(map);
        return list;
    }

    @Override
    public Map<Integer, GameCode> getGameMap() {
        Map<Integer, GameCode> gameCodeMaps = resService.getGameMap();
        Map<Integer, GameCode> gameCodeMap = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        String loginName = subject.getPrincipal().toString();
        if (loginName.equals("admin")) {
            gameCodeMap = gameCodeMaps;
        } else {
            for (Integer gameId : gameCodeMaps.keySet()) {
                int perId = getPerIdByResIdAndActionId(gameId, 5);
                if (subject.isPermitted(Integer.toString(perId))) {
                    gameCodeMap.put(gameId, gameCodeMaps.get(gameId));
                }
            }
        }
        return gameCodeMap;
    }

    @Override
    public Map<Integer, SiteCode> getSiteMap() {
        Map<Integer, SiteCode> siteCodeMaps = resService.getSiteMap();
        Map<Integer, SiteCode> siteCodeMap = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        String loginName = subject.getPrincipal().toString();
        if (loginName.equals("admin")) {
            siteCodeMap = siteCodeMaps;
        } else {
            for (Integer siteId : siteCodeMaps.keySet()) {
                //actionId=5为可用
                // int actionId=5;
                int perId = getPerIdByResIdAndActionId(siteId, 5);
                if (subject.isPermitted(Integer.toString(perId))) {
                    siteCodeMap.put(siteId, siteCodeMaps.get(siteId));
                }
            }
        }
        return siteCodeMap;
    }


}
