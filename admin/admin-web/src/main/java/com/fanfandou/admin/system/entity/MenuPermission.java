package com.fanfandou.admin.system.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Description:菜单的实体类.
 */
public class MenuPermission implements Serializable {
    private Menu menu;
    private List<Integer> relationList;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Integer> getRelationList() {
        return relationList;
    }

    public void setRelationList(List<Integer> relationList) {
        this.relationList = relationList;
    }
}
