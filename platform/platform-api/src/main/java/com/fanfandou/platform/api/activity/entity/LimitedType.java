package com.fanfandou.platform.api.activity.entity;

import com.fanfandou.common.entity.EnumStatus;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by wudi.
 * Descreption:礼包领取限制.
 * Date:2017/3/8
 */
public enum LimitedType implements EnumStatus<LimitedType> {

    NOLIMIT(0,"无限制"),
    SERVER(1,"限制服务器"),
    SITE(2,"限制渠道"),
    TOTLE(3,"总共"),
    MONTHLY(4,"每月"),
    WEEKLY(5,"每周"),
    DAILY(6,"每天")
    ;

    private int id;
    private String code;

    LimitedType(int id, String code) {
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
    public LimitedType getById(int id) {
        return valueOf(id);
    }

    /**
     * 根据id获取LimitedTypeId.
     *
     * @param id id.
     * @return LimitedTypeId.
     */
    public static LimitedType valueOf(int id) {
        LimitedType[] types = values();
        for (LimitedType type : types) {
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
