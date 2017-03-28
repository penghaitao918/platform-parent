package com.fanfandou.platform.api.game.entity;

import com.fanfandou.common.entity.EnumStatus;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by wudi.
 * Descreption:维护状态枚举.
 * Date:2016/5/27
 */
public enum MaintenanceStatus implements EnumStatus {
    //正常
    NORMAL(1,"normal"),
    //维护中
    MAINTAINING(2,"maintaining"),
    ;
    private int id;
    private String code;

    MaintenanceStatus(int id, String code) {
        this.id = id;
        this.code = code;
    }


    /**
     * 根据id获取MaintenanceStatus.
     * @param id id.
     * @return MaintenanceStatus.
     */
    public static MaintenanceStatus valueOf(int id) {
        MaintenanceStatus[] types = values();
        for (MaintenanceStatus type : types) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

    @JsonValue
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
