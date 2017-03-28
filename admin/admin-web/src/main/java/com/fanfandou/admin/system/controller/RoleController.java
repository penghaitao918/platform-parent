package com.fanfandou.admin.system.controller;

import com.fanfandou.admin.system.entity.Role;
import com.fanfandou.admin.system.service.RoleService;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import com.fanfandou.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wangzhenwei on 2016/3/15.
 * Description 角色管理.
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 显示角色列表.
     */
    @RequestMapping(value = "/roleInit")/*crud/partials/table*/
    public ModelAndView roleList() {
        return new ModelAndView("system/RoleLists");
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<Role> getList() {
        return this.roleService.selectAll();
    }

    /**
     * 分页查询
     *
     * @param roleName 角色名称
     * @param page     page对象
     * @return 分页对象集合
     */
    @ResponseBody
    @RequestMapping("/pageList")
    public PageResult<Role> getPageList(String roleName, Page page) {
        return this.roleService.getPageList(roleName, page);
    }


    /**
     * 添加角色跳转
     */
    @RequestMapping(value = "/toAdd")
    public ModelAndView toAdd() {
        ModelAndView manv = new ModelAndView();
        manv.setViewName("system/role/addRole");
        return manv;
    }

    /**
     * 添加角色跳转
     *
     * @param role 角色对象
     * @return
     */
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public String addRole(Role role) {
        this.roleService.addRole(role);
        return "redirect:roleInit";
    }

    /**
     * 根据id修改状态
     *
     * @param ids   id集合
     * @param state 状态码
     * @throws ServiceException
     */
    @ResponseBody
    @RequestMapping(value = "/updateState")
    public void menuState(String ids, int state) throws ServiceException {
        this.roleService.invalid(ids, state);
    }


    /**
     * 修改角色
     *
     * @param role 角色对象
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    public List<Role> editRole(Role role) {
        this.roleService.updRole(role);
        return this.roleService.selectAll();
    }

    /**
     * 根据id查.
     *
     * @param id id
     */
    @ResponseBody
    @RequestMapping(value = "/editRole/{id}")
    public Role edit(@PathVariable(value = "id") int id) {
        Role role = roleService.selRoleById(id);
        return role;
    }

    /**
     * 删除
     *
     * @param roleId 角色id
     */
    @RequestMapping("/delRole/{roleId}")
    @ResponseBody
    public List<Role> delUser(@PathVariable(value = "roleId") String roleId) {
        String[] ids = roleId.split(",");
        List<Integer> idList = new ArrayList<Integer>();
        for (int i = 0; i < ids.length; i++) {
            int id = Integer.parseInt(ids[i]);
            idList.add(id);
        }
        this.roleService.delRole(idList);
        return this.roleService.selectAll();
    }
}
