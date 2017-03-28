package com.fanfandou.admin.system.service.impl;

import com.fanfandou.admin.system.dao.RelationMapper;
import com.fanfandou.admin.system.entity.Menu;
import com.fanfandou.admin.system.entity.Permission;
import com.fanfandou.admin.system.entity.Relation;
import com.fanfandou.admin.api.system.entity.ResEnum;
import com.fanfandou.admin.api.system.entity.Resource;
import com.fanfandou.admin.system.service.MenuService;
import com.fanfandou.admin.system.service.PermissionService;
import com.fanfandou.admin.system.service.RelationService;
import com.fanfandou.admin.api.system.service.ResService;
import com.fanfandou.common.constant.PublicCachedKeyConstant;
import com.fanfandou.common.entity.resource.GameCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangzhenwei on 2016/3/15.
 * Description service实现类
 */
@Service("relationService")
public class RelationServiceImpl implements RelationService {
    @Autowired
    private RelationMapper relationMapper;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private ResService resService;

    @Autowired
    private MenuService menuService;


    //查询所有关联关系
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<Relation> selectAll() {
        return this.relationMapper.selectAll();
    }

    //根据内容查找关系id
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public int selectId(int id1, int id2, int typeId) {
        try {
            Relation relation = new Relation();
            relation.setId1(id1);
            relation.setId2(id2);
            relation.setType(typeId);
            int relationId = this.relationMapper.selectId(relation);
            return relationId;
        } catch (Exception e) {
            return 0;
        }
    }

    //根据id1和typeId查找id2
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<Integer> selectIdBy(int id1, int typeId) {
        Map map = new HashMap();
        map.put("id1", id1);
        map.put("typeId", typeId);
        return this.relationMapper.selectIdBy(map);
    }
    /*  type为1时，id1 = userId，id2 = roleId
        type为2时，id1 = roleId，id2 = promissionId
        type为3时，id1 = userId，id2 = promissionId*/

    /**
     * 授予一个角色或者用户权限就是在角色权限关联表中加入一个角色id和权限id的对应关系.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void addRelation(int id1, int id2, int idType) {
        Relation relation = new Relation();
        relation.setId1(id1);
        relation.setId2(id2);
        relation.setType(idType);
        this.relationMapper.insert(relation);
    }

    //del By Id
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void delRelation(int id) {
        this.relationMapper.delete(id);
    }

    //upd
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void updRelation(Relation user) {
        this.relationMapper.update(user);
    }

    //sel By Id
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public Relation selRelationById(int id) {
        return this.relationMapper.selectById(id);
    }

    //清空用户所有权限
   /* @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void emptyPermission(int userRoleId,int typeId) {
        List<Integer> list = this.selectIdBy(userRoleId,typeId);
        for(Integer relationId:list){
            this.delRelation(relationId);
        }
    }*/

    //判断添加菜单权限还是删除
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void optPermission(int userRoleId, int typeId, int actionId, int menuId, boolean flag) {
        if (flag) {
            this.addPermission(userRoleId, typeId, actionId, menuId);
        } else {
            this.delPermission(userRoleId, typeId, actionId, menuId);
        }
    }

    //添加菜单权限
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void addPermission(int userRoleId, int typeId, int actionId, int menuId) {
        //添加父菜单查看权限
        Menu menu = menuService.selById(menuId);
        int parentMenuId = menu.getParentId();
        if (parentMenuId != 0) {
            int perId = permissionService.getPerId(parentMenuId, 4);
            int relationId1 = this.selectId(userRoleId, perId, typeId);
            if (relationId1 == 0) {
                this.addRelation(userRoleId, perId, typeId);
            }
        }
        //如果添加了操作权限，就会自动添加查看权限
        if (actionId < 4) {
            int permId = this.permissionService.getPerId(menuId, 4);
            int relationId2 = this.selectId(userRoleId, permId, typeId);
            if (relationId2 == 0) {
                this.addRelation(userRoleId, permId, typeId);
            }
        }
        //添加子菜单权限
        int permissionId = this.permissionService.getPerId(menuId, actionId);
        int relationId = this.selectId(userRoleId, permissionId, typeId);
        if (relationId == 0) {
            this.addRelation(userRoleId, permissionId, typeId);
        }
    }

    //删除菜单权限
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void delPermission(int userRoleId, int typeId, int actionId, int menuId) {
        int permissionId = this.permissionService.getPerIdByResIdAndActionId(menuId, actionId);
        int relationId = this.selectId(userRoleId, permissionId, typeId);
        this.delRelation(relationId);
    }

    //查询ResId 包括game和site
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<Integer> selResPermission(int userRoleId, int typeId, int resType) {
        List<Integer> list = this.selectIdBy(userRoleId, typeId);
        List<Integer> gameIdList = new ArrayList<>();
        List<Integer> siteIdList = new ArrayList<>();
        List<Integer> resIdList = new ArrayList<>();
        for (Integer perId : list) {
            Permission permission = this.permissionService.selPermissionById(perId);
            if (permission != null) {
                int perActionId = permission.getActionId();
                if (perActionId == 5) {
                    int resId = permission.getResourceId();

                    Resource resource = resService.selectById(resId);
                    if (resource != null) {
                        if (resource.getResType().equals(ResEnum.game)) {
                            gameIdList.add(resId);
                        }
                        if (resource.getResType().equals(ResEnum.site)) {
                            siteIdList.add(resId);
                        }
                    }
                }
            }
        }
        if (resType == 1) {
            return gameIdList;
        }
        if (resType == 2) {
            return siteIdList;
        }
        return resIdList;
    }

    //先清空用户的资源权限，再添加权限
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void optResPermission(int userRoleId, int typeId, String resIds, int resType) {
        this.emptyResPermission(userRoleId, typeId, resType);
        this.addResPermission(userRoleId, typeId, 5, resIds);
    }

    //清空资源权限
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void emptyResPermission(int userRoleId, int typeId, int resType) {
        List<Integer> resIdList = this.selResPermission(userRoleId, typeId, resType);
        for (Integer resId : resIdList) {
            int permissionId = this.permissionService.getPerIdByResIdAndActionId(resId, 5);
            int relationId = this.selectId(userRoleId, permissionId, typeId);
            this.delRelation(relationId);
        }
    }

    //添加资源权限
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void addResPermission(int userRoleId, int typeId, int actionId, String resIds) {
        String resourceIds[] = resIds.split(",");
        for (String resIdStr : resourceIds) {
            int resId = Integer.parseInt(resIdStr);
            int permissionId = this.permissionService.getPerId(resId, actionId);
            this.addRelation(userRoleId, permissionId, typeId);
        }
    }

    //为用户添加角色
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void addRole(int id1, String ids2) {
        String roleIds[] = ids2.split(",");
        for (String roleIdStr : roleIds) {
            int roleId = Integer.parseInt(roleIdStr);
            this.addRelation(id1, roleId, 1);
        }
    }

    //查询角色id集合
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<Integer> selRoleId(int userId) {
        int typeId = 1;
        List<Integer> list = this.selectIdBy(userId, typeId);
        return list;
    }

    //清空角色信息
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void emptyRole(int userId) {
        int typeId = 1;
        List<Integer> list = this.selectIdBy(userId, typeId);
        for (int roleId : list) {
            int relationId = this.selectId(userId, roleId, 1);
            this.delRelation(relationId);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void optRole(int userId, String roleIds) {
        this.emptyRole(userId);
        this.addRole(userId, roleIds);
    }

    //清空菜单权限
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void emptyMenuPer(int userRoleId, int typeId) {
        List<Integer> perList = this.selectIdBy(userRoleId, typeId);
        for (int perId : perList) {
            Permission permission = permissionService.selPermissionById(perId);
            if (permission != null) {
                int actionId = permission.getActionId();
                if (actionId < 5) {
                    int permissionId = permission.getId();
                    int relationId = this.selectId(userRoleId, permissionId, typeId);
                    this.delRelation(relationId);
                }
            }
        }
    }

    //清空父菜单内权限
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void emptyParentMenuPer(int userRoleId, int typeId, int menuParentId) {
        List<Integer> perList = this.selectIdBy(userRoleId, typeId);
        List<Integer> childrenList = this.menuService.getChildrenMenu(menuParentId);
        for (int perId : perList) {
            Permission permission = permissionService.selPermissionById(perId);
            if (permission != null) {
                int actionId = permission.getActionId();
                if (actionId < 5) {
                    int resId = permission.getResourceId();
                    if (childrenList.contains(resId)) {
                        int permissionId = permission.getId();
                        int relationId = this.selectId(userRoleId, permissionId, typeId);
                        this.delRelation(relationId);
                    }
                }
            }
        }
    }

    //添加菜单权限
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void addMenuPer(int userRoleId, int menuParentId, int typeId) {
        List<Integer> childrenMenuList = this.menuService.getChildrenMenu(menuParentId);
        for (int childrenMenuId : childrenMenuList) {
            for (int actionId = 1; actionId < 5; actionId++) {
                this.addPermission(userRoleId, typeId, actionId, childrenMenuId);
            }
        }
    }

    //判断全选还是全不选
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void optMenuParentId(int userRoleId, int menuParentId, int typeId, boolean flag) {
        if (flag) {
            this.emptyParentMenuPer(userRoleId, typeId, menuParentId);
            this.addMenuPer(userRoleId, menuParentId, typeId);
        } else {
            this.emptyParentMenuPer(userRoleId, typeId, menuParentId);
        }
    }

    //判断这个父菜单里的权限是否全部都有
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public boolean parentMenuIsAll(int userRoleId, int typeId, int menuParentId) {
        boolean flag = true;
        List<Integer> childrenMenuList = this.menuService.getChildrenMenu(menuParentId);
        for (int childrenMenuId : childrenMenuList) {
            for (int actionId = 1; actionId < 5; actionId++) {
                int perId = this.permissionService.getPerIdByResIdAndActionId(childrenMenuId, actionId);
                if (perId == 0) {
                    flag = false;
                    break;
                }
                int relationId = this.selectId(userRoleId, perId, typeId);
                if (relationId == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag == false) {
                break;
            }
        }
        return flag;
    }



}
