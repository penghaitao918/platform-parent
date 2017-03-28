package com.fanfandou.admin.api.operation.entity;

import com.fanfandou.common.entity.EnumStatus;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by wudi.
 * Descreption:设备版本.
 * Date:2017/2/23
 */
public enum DeviceType implements EnumStatus<DeviceType> {
    ANDROID(1,"android"),
    IOS(2,"ios")
    ;

    private int id;
    private String code;

    DeviceType(int id, String code) {
        this.id = id;
        this.code = code;
    }
    @JsonValue
    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    @Override
    public DeviceType getById(int id) {
        return valueOf(id);
    }

    /**
     * 根据id获取DeviceType.
     *
     * @param id id.
     * @return DeviceType.
     */
    public static DeviceType valueOf(int id) {
        DeviceType[] types = values();
        for (DeviceType type : types) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
