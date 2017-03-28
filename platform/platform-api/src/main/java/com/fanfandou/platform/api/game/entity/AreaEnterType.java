package com.fanfandou.platform.api.game.entity;

import com.fanfandou.common.entity.EnumStatus;

/**
 * Created by wudi.
 * Descreption:是客户端连接地址还是平台连接地址.
 * Date:2016/8/2
 */
public enum AreaEnterType implements EnumStatus {
    //客户端连接入口
    CLIENTENTER(0,"client"),
    //平台连接入口
    SERVERENTER(1,"server")
    ;

    private int id;
    private String code;

    AreaEnterType(int id, String code) {
        this.id = id;
        this.code = code;
    }


    /**
     * 根据id获取AreaEnterType.
     * @param id id.
     * @return AreaEnterType.
     */
    public static AreaEnterType valueOf(int id) {
        AreaEnterType[] types = values();
        for (AreaEnterType type : types) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Enum getById(int id) {
        return valueOf(id);
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
