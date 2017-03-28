package com.fanfandou.admin.system.dao;

import com.fanfandou.admin.system.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * author shengbo.
 * Description:菜单mapper.
 */

@Repository
public interface MenuMapper {

    /**
     * 查询所有.
     */
    List<Menu> getAll();

    /**
     * 得到父菜单.
     */
    List<Menu> getParentMenu();

    /**
     * selById.
     */
    Menu selById(int id);

    /**
     * 改.
     */
    void update(Menu menu);

    /**
     * 增.
     */
    void insert(Menu menu);

    /**
     * deleteById.
     */
    void deleteById(int id);

    /**
     * 分页.
     */
    List<Menu> pageList(Map paramMap);

    /**
     * 分页.根据父id查询
     */
    List<Menu> pageListBypId(Map paramMap);


    /**
     * 分页数量.
     */
    int totalCount(Map map);

    /**
     * 分页数量 根据父id.
     */
    int totalCountById(Map map);

    /**
     * childrenIdList.
     */
    List<Integer> childrenIdList(int parentId);

    /**
     * 根据名称查menu
     *
     * @param menuName 菜单名称
     * @return 菜单
     */
    Menu selByName(String menuName);
}
