package com.fanfandou.common.entity;

import java.util.Map;

/**
 * Description: map容器，方便protostuff序列化.
 * <p/>
 * Date: 2016-10-14 14:37.
 * author: Arvin.
 */
public class MapWrapper {
    private Map<Object, Object> map;

    public MapWrapper() {
    }

    public MapWrapper(Map<Object, Object> map) {
        this.map = map;
    }

    public Map<Object, Object> getMap() {
        return map;
    }

    public void setMap(Map<Object, Object> map) {
        this.map = map;
    }
}
