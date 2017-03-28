package com.fanfandou.platform.api.game.entity;

import com.fanfandou.common.entity.EnumStatus;

/**
 * Created by wudi.
 * Descreption:玩具绑定状态枚举.
 * Date:2016/5/25
 */
public enum ToyBindStatus implements EnumStatus {
    UNBIND(1,"unbind"),
    BINDING(2,"binding"),
    BOUND(3,"bound"),
    INVALID(4,"invalid")
    ;

    private int id;
    private String code;

    ToyBindStatus(int id, String code) {
        this.id = id;
        this.code = code;
    }


    /**
     * 根据id获取ToyBindStatus.
     * @param id id.
     * @return ToyBindStatus.
     */
    public static ToyBindStatus valueOf(int id) {
        ToyBindStatus[] types = values();
        for (ToyBindStatus type : types) {
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


}
