package com.fanfandou.admin.system.service;

import com.fanfandou.admin.system.entity.Menu;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;

import java.util.List;

/**
 * Description:菜单Service.
 * author:shengbo.
 */
public interface MenuService {

    /**
     * 所有菜单.
     */
    List<Menu> getAll();

    /**
     * 通过id查找菜单.
     */
    Menu selById(int id);

    /**
     * 更新菜单.
     */
    void updMenu(Menu menu);

    /**
     * 添加.
     */
    void addMenu(Menu menu);

    /**
     * 批量删除.
     * @param idList id集合
     */
    void delMenu(List<Integer> idList);

    /**
     * 从数据库模糊查询出本页数据并排序.
     */
    PageResult<Menu> getPageList(String name, Page page);

    /**
     * 从数据库模糊查询出本页数据并排序.根据父id
     */
    PageResult<Menu> getPageListById(int parentId, Page page);

    /**
     * 菜单工具.
     */
    Menu menuRoot(List<Menu> menuList);

    /**
     * 一件开始，失效.
     */
    void invalid(String ids,int state);

    /**
     * 通过父菜单获取子菜单.
     * @param parentId 父菜单id
     * @return 子菜单id集合
     */
    List<Integer> getChildrenMenu(int parentId);

    /**
     * 查询出所有基础父菜单.
     */
    List<Menu> getParentMenu();

    /**
     * 根据菜单名称查menu
     * @return
     */
    Menu getMenuByName(String name);
}
