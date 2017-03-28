package com.fanfandou.admin.system.controller;

import com.fanfandou.admin.system.entity.Menu;
import com.fanfandou.admin.system.service.MenuService;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;

import com.fanfandou.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author shengbo.
 * Description:菜单操作.
 */
@RestController
@RequestMapping(value = "system/menu")
public class MenuController {
    @Autowired
    @Resource(name = "MenuService")
    private MenuService menuService;


    /**
     * 跳转到分类页面
     *
     * @return 分类页面
     */
    @RequestMapping(value = "/menuInit")
    public ModelAndView toListMenu() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/system/MenuList");
        return mav;
    }

    /**
     * 分页查询
     *
     * @param name 菜单名称
     * @param page page对象
     * @return 分页对象集合
     */
    @ResponseBody
    @RequestMapping("/pageList")
    public PageResult<Menu> getPageList(String name, Page page) {
        return this.menuService.getPageList(name, page);
    }


    /**
     * 查询所有分类信息
     *
     * @return 获取的menu
     */
    @RequestMapping(value = "/getAll")
    public List<Menu> getAll() {
        return this.menuService.getAll();
    }

    /**
     * 根据查询menu
     *
     * @param id id
     * @return 菜单对象
     */
    @RequestMapping(value = "/getById")
    public Menu getById(int id) {
        return this.menuService.selById(id);
    }

    /**
     * 修改
     *
     * @param menu 菜单对象
     */
    @RequestMapping(value = "/update")
    public void update(Menu menu) {
        this.menuService.updMenu(menu);
    }

    /**
     * 添加
     *
     * @param menu 菜单对象
     */
    @RequestMapping(value = "/insert")
    public void insert(Menu menu) {
        this.menuService.addMenu(menu);
    }

    /**
     * 获取所有的父类菜单
     *
     * @return 菜单集合
     */
    @RequestMapping(value = "/getParentMenu")
    public List<Menu> getParentMenu() {
        return this.menuService.getParentMenu();
    }

    /**
     * 根据id集合删除
     *
     * @param menuIds 菜单集合
     */
    @RequestMapping(value = "/delete")
    public void delete(String menuIds) {
        String[] menus = menuIds.split(",");
        List<Integer> menuList = new ArrayList<Integer>();
        for (int i = 0; i < menus.length; i++) {
            menuList.add(Integer.parseInt(menus[i]));
        }
        this.menuService.delMenu(menuList);
    }

    /**
     * 根据id修改状态
     *
     * @param ids   菜单id
     * @param state 状态码
     * @throws ServiceException
     */
    @ResponseBody
    @RequestMapping(value = "/updateState")
    public void menuState(String ids, int state) throws ServiceException {
        this.menuService.invalid(ids, state);
    }

    /**
     * 菜单查询.
     */
    @ResponseBody
    @RequestMapping("/getMenu")
    public Menu getMenu() {
        Menu menu = menuService.menuRoot(menuService.getAll());
        System.out.print(menu);
        return menu;
    }

    /**
     * 根据菜单名称查询菜单
     */
    @ResponseBody
    @RequestMapping("/getMenuByName")
    public Menu getMenuByName(String name) {
        Menu menu = menuService.getMenuByName(name);
        return menu;
    }


}
