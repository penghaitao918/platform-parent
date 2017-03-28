package com.fanfandou.admin.system.service.impl;

import com.fanfandou.admin.system.dao.MenuMapper;
import com.fanfandou.admin.system.entity.Menu;
import com.fanfandou.admin.system.service.MenuService;
import com.fanfandou.admin.system.service.PermissionService;
import com.fanfandou.common.entity.result.Page;
import com.fanfandou.common.entity.result.PageResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Description:菜单Service实现.
 */
@Service("MenuService")
public class MenuServiceImpl implements MenuService {

    @Autowired(required = true)
    private MenuMapper menuMapper;

    @Autowired(required = true)
    private PermissionService permissionService;

    /**
     * 获取分页数据
     * 模糊查询，分页，排序.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public PageResult<Menu> getPageList(String name, Page page) {
        if (page.getOrder() == null || page.getOrder().equals("")) {
            page.setOrder("show_order");
        }
        if (page.getSort() == null || page.getSort().equals("")) {
            page.setSort("asc");
        }
        String str = '%' + name + '%';
        int num1 = (page.getPageIndex() - 1) * page.getPageSize();
        Map paramMap = new HashMap();
        paramMap.put("menuName", str);
        paramMap.put("str1", page.getOrder());
        paramMap.put("str2", page.getSort());
        paramMap.put("num1", num1);
        paramMap.put("num2", page.getPageSize());

        Map map = new HashMap();
        map.put("menuName", str);
        int totalCount = this.menuMapper.totalCount(map);
        page.setTotalCount(totalCount);
        PageResult<Menu> pageResult = new PageResult<>();
        List<Menu> list = this.menuMapper.pageList(paramMap);
        pageResult.setPage(page);
        pageResult.setRows(list);
        return pageResult;
    }

    /**
     * 获取分页数据
     * 模糊查询，分页，排序.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public PageResult<Menu> getPageListById(int parentId, Page page) {
        if (page.getOrder() == null || page.getOrder().equals("")) {
            page.setOrder("show_order");
        }
        if (page.getSort() == null || page.getSort().equals("")) {
            page.setSort("asc");
        }
        int num1 = (page.getPageIndex() - 1) * page.getPageSize();
        Map paramMap = new HashMap();
        paramMap.put("parentId", parentId);
        paramMap.put("str1", page.getOrder());
        paramMap.put("str2", page.getSort());
        paramMap.put("num1", num1);
        paramMap.put("num2", page.getPageSize());

        Map map = new HashMap();
        map.put("parentId", parentId);
        int totalCount = this.menuMapper.totalCountById(map);
        page.setTotalCount(totalCount);
        PageResult<Menu> pageResult = new PageResult<>();
        List<Menu> list = this.menuMapper.pageListBypId(paramMap);
        pageResult.setPage(page);
        pageResult.setRows(list);
        return pageResult;
    }



    /*  @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public Integer getMax() {
        return this.menuMapper.getMax();
    }*/

    /**
     * 根据权限得到所有.
     *
     * @return menuList
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<Menu> getAll() {
        List<Menu> allList = this.menuMapper.getAll();
        List<Menu> list = new ArrayList<>();
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal().toString().equals("admin")) {
            list = allList;
        } else {
            for (Menu menu : allList) {
                if (menu.getAvailable() == 1) {
                    int perId = permissionService.getPerIdByResIdAndActionId(menu.getId(), 4);
                    if (subject.isPermitted(Integer.toString(perId))) {
                        list.add(menu);
                    }
                }
            }
        }
        return list;
    }

    /**
     * 根据Id查询.
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public Menu selById(int id) {
        return this.menuMapper.selById(id);
    }

    /**
     * 修改.
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void updMenu(Menu menu) {
        this.menuMapper.update(menu);
    }

    /**
     * 添加.
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void addMenu(Menu menu) {
        menu.setCreateTime(new Date());
        menu.setIsParent(0);
        menu.setAvailable(1);
        this.menuMapper.insert(menu);
    }

    /**
     * 删除.
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void delMenu(List<Integer> idList) {
        for (Integer id : idList) {
            this.menuMapper.deleteById(id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<Integer> getChildrenMenu(int parentId) {
        return this.menuMapper.childrenIdList(parentId);
    }

    /**
     * 一键失效和开启.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public void invalid(String ids, int state) {
        String[] noticeIds = ids.split(",");
        for (String id : noticeIds) {
            Menu menu = this.selById(Integer.parseInt(id));
            if (state == 0 || state == 1) {
                menu.setAvailable(state);
            }
            this.updMenu(menu);
        }
    }

    /**
     * 获取树结构.
     */
    public Menu MenuRoot(List<Menu> menuList) {
        // 定义根节点，初始id为0
        Menu root = new Menu();
        root.setId(0);
        childrenList(root, menuList);
        return root;
    }

    /**
     * 子菜单集合.
     */
    public void childrenList(Menu menu, List<Menu> menuList) {
        //循环menulist，把在子节点上存入children集合
        for (int j = menuList.size() - 1; j >= 0; j--) {
            if (menuList.get(j).getParentId() == menu.getId()) {
                menu.getChildrenList().add(menuList.get(j));
                menuList.remove(j);
            }
        }
        //递归循环，遍历所有child
        for (Menu child : menu.getChildrenList()) {
            childrenList(child, menuList);
        }
    }

    @Override
    public Menu menuRoot(List<Menu> menuList) {
        return MenuRoot(menuList);
    }

    /**
     * 查询所有的父菜单.
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<Menu> getParentMenu() {
        return this.menuMapper.getParentMenu();
    }

    @Override
    public Menu getMenuByName(String name) {
        return this.menuMapper.selByName(name);
    }
}