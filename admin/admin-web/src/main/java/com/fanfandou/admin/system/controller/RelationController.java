package com.fanfandou.admin.system.controller;

import com.fanfandou.admin.system.entity.Menu;
import com.fanfandou.admin.system.entity.MenuPermission;
import com.fanfandou.admin.system.entity.Relation;
import com.fanfandou.admin.system.service.MenuService;
import com.fanfandou.admin.system.service.PermissionService;
import com.fanfandou.admin.system.service.RelationService;
import com.fanfandou.admin.system.service.SysLogService;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王振伟 on 2016/3/15.
 * Description 关系controller.
 */
@RestController
@RequestMapping("/system/relation")
public class RelationController {

    @Autowired
    private RelationService relationService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private SysLogService sysLogService;

    /**
     * 显示关联列表显示页面.
     */
    @RequestMapping(value = "/relationInit")
    public ModelAndView relationList() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/relationList");
        return mv;
    }

    /**
     * 查询所有关系
     *
     * @return 关系集合
     */
    @ResponseBody
    @RequestMapping("/list")
    public List<Relation> getList() {
        return this.relationService.selectAll();
    }

    /**
     * 调转添加
     *
     * @return ModelAndView
     */
    @RequestMapping(value = "/toAdd")
    public ModelAndView toAdd() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/relation/addRelation");
        return mv;
    }

    /**
     * 用户添加关系
     *
     * @param userId 用户id 或者 角色id
     * @param type   类型
     * @param menuId 菜单id 或者资源id
     * @param opId   操作id
     * @param judge  判断码
     */
    @RequestMapping("/appendRelation")
    public void appendRelation(int userId, int type, int menuId, int opId, boolean judge) {
        this.relationService.optPermission(userId, type, opId, menuId, judge);
    }

    /**
     * 角色添加关系
     *
     * @param userRoleId   角色id或者用户id
     * @param menuParentId 父类菜单id
     * @param typeId       类型id
     * @param flag         判断码
     */
    @RequestMapping("/appendAllRelation")
    public void appendAllRelation(int userRoleId, int menuParentId, int typeId, boolean flag) {
        this.relationService.optMenuParentId(userRoleId, menuParentId, typeId, flag);
    }

    /**
     * 根据用户id，类型，菜单查询关系
     *
     * @param userId 用户id或者角色id
     * @param type   类型
     * @param menuId 菜单id 或者资源id
     * @return 关系list
     */
    @ResponseBody
    @RequestMapping("/theRelationById")
    public List<Integer> theRelationById(int userId, int type, int menuId) {
        return this.permissionService.selActionId(userId, type, menuId);
    }

    /**
     * 菜单权限分页查询
     *
     * @param page page对象
     * @return 分页对象集合
     */
    @ResponseBody
    @RequestMapping("/menuPermission")
    public PageResult<MenuPermission> menuPermission(int parentId, Page page, int userId, int type) {
        PageResult<Menu> menuPageResult = this.menuService.getPageListById(parentId, page);
        PageResult<MenuPermission> mp = new PageResult<>();
        List<MenuPermission> menuPermissions = new ArrayList<>();
        for (int i = 0; i < menuPageResult.getRows().size(); i++) {
            MenuPermission menuPermission = new MenuPermission();
            menuPermission.setMenu(menuPageResult.getRows().get(i));
            menuPermission.setRelationList(this.permissionService.selActionId(userId, type, menuPageResult.getRows().get(i).getId()));
            menuPermissions.add(menuPermission);
        }
        mp.setPage(menuPageResult.getPage());
        mp.setRows(menuPermissions);
        mp.setTotal(menuPageResult.getTotal());

        return mp;
    }

    /**
     * 根据用户id，类型，
     *
     * @param userId    用户id或者角色id
     * @param typeId    类型id
     * @param resTypeId 资源或者菜单id
     * @return 关系list
     */
    @ResponseBody
    @RequestMapping("/getPermission")
    public List<Integer> getPermission(int userId, int typeId, int resTypeId) {
        return this.relationService.selResPermission(userId, typeId, resTypeId);
    }

    /**
     * 设置关系
     *
     * @param userId    用户id或者角色id
     * @param typeId    类型id
     * @param resTypeId 资源id或者菜单id
     * @param siteIds   渠道id集合
     */
    @RequestMapping("/setPermission")
    public void setPermission(int userId, int typeId, int resTypeId, String siteIds) {
        this.relationService.optResPermission(userId, typeId, siteIds, resTypeId);
    }

    /**
     * 删除关系
     *
     * @param id id
     * @return ModelAndView
     */
    @RequestMapping("/delRelation")
    public ModelAndView delRelation(@RequestParam(value = "ID") int id) {
        this.relationService.delRelation(id);
        return relationList();
    }

    /**
     * 修改关系
     *
     * @param relation 关系对象
     * @return ModelAndView
     */
    @RequestMapping(value = "/updateRelation", method = RequestMethod.POST)
    public ModelAndView editRelation(Relation relation) {
        this.relationService.updRelation(relation);
        return relationList();
    }

    /**
     * 根据id查.
     *
     * @param id relationId
     */
    @RequestMapping(value = "/editRelation")
    public ModelAndView edit(@RequestParam(value = "ID") int id) {
        ModelAndView mav = new ModelAndView("system/relation/editRelation");
        Relation relation = relationService.selRelationById(id);
        mav.addObject("ro", relation);
        return mav;
    }

    /**
     * 用户添加角色
     *
     * @param userId  用户id
     * @param roleIds 角色id集合
     */
    @RequestMapping("/setRole")
    public void setRole(int userId, String roleIds) {
        this.relationService.optRole(userId, roleIds);
    }


    /**
     * 查询用户的绑定角色
     *
     * @param userId 用户id
     * @return 角色关系集合
     */
    @ResponseBody
    @RequestMapping("/getRole")
    public List<Integer> getRole(int userId) {
        return this.relationService.selRoleId(userId);
    }

    /**
     * 判断是否权限全选
     *
     * @param userRoleId   用户id或者角色id
     * @param typeId       类型id
     * @param menuParentId 父类菜单id
     * @return 状态码
     */
    @ResponseBody
    @RequestMapping("/parentMenuIsAll")
    public boolean parentMenuIsAll(int userRoleId, int typeId, int menuParentId) {
        return this.relationService.parentMenuIsAll(userRoleId, typeId, menuParentId);
    }

    /**
     * 验证是否有申请，审核的权限
     *
     * @return list页面
     */
    @RequestMapping(value = "/checkAuthority")
    public boolean checkAuthorityList(String menuName, int type) {
        if (!sysLogService.getUserName().equals("admin")) {
            Menu menu = this.menuService.getMenuByName(menuName);
            List<Integer> actionIds = this.permissionService.selActionId(sysLogService.getUserId(), 3, menu.getId());
            for (int i = 0; i < actionIds.size(); i++) {
                if (actionIds.get(i) == type) {
                    return true;
                }
            }
        } else {
            return true;
        }
        return false;
    }
}
