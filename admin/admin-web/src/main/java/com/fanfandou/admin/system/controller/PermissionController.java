package com.fanfandou.admin.system.controller;

import com.fanfandou.admin.system.entity.Permission;
import com.fanfandou.admin.system.service.PermissionService;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wangzhenwei on 2016/3/15.
 * Description 权限controller.
 */
@RestController
@RequestMapping("/system/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 显示权限列表.
     */
    @RequestMapping(value = "permissionInit")
    public ModelAndView permissionList() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("system/PermissionList");
        return mv;
    }

    /**
     * 查询所有权限
     * @return
     */
    @ResponseBody
    @RequestMapping("/permissionList")
    public List<Permission> getList() {
        return this.permissionService.selectAll();
    }

    /**
     * 分页查询.
     */
    @ResponseBody
    @RequestMapping("/pageList")
    public PageResult<Permission> getPageList(String permissionValue, Page page) {
        return this.permissionService.getPageList(page);
    }

    /**
     * 查询一个角色或者用户的所有权限信息.
     *
     * @param id1  roleid or userid
     * @param type 区分是什么id
     */
    @RequestMapping("/RUPerList")
    public ModelAndView selectByRoleUserId(@RequestParam(value = "ID") int id1,
                                           @RequestParam(value = "type") int type) {
        ModelAndView mav = new ModelAndView("system/relation/RUPerList");
        List<Permission> allPer = this.getList();
        List<Permission> hasList = permissionService.selByRoleUserId(id1, type);
        Iterator<Permission> iterator = allPer.iterator();
        while (iterator.hasNext()) {
            Permission all = iterator.next();
            for (Permission has : hasList) {
                if (has.getId() == all.getId()) {
                    iterator.remove();
                }
            }
        }
        mav.addObject("unHasList", allPer);
        mav.addObject("perList", hasList);
        mav.addObject("id1", id1);
        mav.addObject("perType", type);
        return mav;
    }



    @RequestMapping("/addPermission")
    public ModelAndView addPermission(@RequestParam(value = "resourceId") int resourceId,
                                      @RequestParam(value = "actionId") int actionId,
                                      @RequestParam(value = "type") String resourceType,
                                      @RequestParam(value = "permissionValue") String permissionValue,
                                      @RequestParam(value = "available") int available) {
       /* this.permissionService.addPermission(resourceId, actionId, resourceType, permissionValue, available);*/
        return permissionList();
    }

    //删
    @RequestMapping("/delPermission/{permissionId}")
    @ResponseBody
    public List<Permission> delPermission(@PathVariable(value = "permissionId") String permissionId) {
        String[] ids = permissionId.split(",");
        List<Integer> idList = new ArrayList<Integer>();
        for (int i = 0; i < ids.length; i++) {
            int id = Integer.parseInt(ids[i]);
            idList.add(id);
        }
        this.permissionService.delPermissionList(idList);
        return this.permissionService.selectAll();
    }

    //改
    @RequestMapping(value = "/updatePermission", method = RequestMethod.POST)
    public ModelAndView editPermission(Permission permission) {
        this.permissionService.updPermission(permission);
        return permissionList();
    }

    /**
     * 根据id查.
     *
     * @param id permissionId
     */
    @ResponseBody
    @RequestMapping(value = "/editPermission/{id}")
    public Permission edit(@PathVariable(value = "id") int id) {
        Permission permission = permissionService.selPermissionById(id);
        return permission;
    }

}
