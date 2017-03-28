package com.fanfandou.platform.api.game.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wudi.
 * Descreption:TODO..
 * Date:2016/8/26
 */
public class ToyTest implements Serializable {

    private List<Integer> listTest = new ArrayList<>();

    public List<Integer> getListTest() {
        return listTest;
    }

    public void setListTest(List<Integer> listTest) {
        this.listTest = listTest;
    }
}
