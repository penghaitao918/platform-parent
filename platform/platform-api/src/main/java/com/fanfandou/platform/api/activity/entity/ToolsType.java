package com.fanfandou.platform.api.activity.entity;

import com.fanfandou.common.entity.EnumStatus;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by wudi.
 * Descreption:道具类型.
 * Date:2017/3/10
 */
public enum ToolsType implements EnumStatus<ToolsType> {

    GEM(1,"宝石"),
    TOOLS(2,"道具"),
    GOLD(3,"金币")
    ;

    private int id;
    private String code;

    ToolsType(int id, String code) {
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
    public ToolsType getById(int id) {
        return valueOf(id);
    }

    /**
     * 根据id获取ToolsType.
     *
     * @param id id.
     * @return ToolsType.
     */
    public static ToolsType valueOf(int id) {
        ToolsType[] types = values();
        for (ToolsType type : types) {
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
