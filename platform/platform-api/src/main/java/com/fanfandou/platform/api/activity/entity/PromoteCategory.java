package com.fanfandou.platform.api.activity.entity;

import com.fanfandou.common.entity.EnumStatus;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by wudi.
 * Descreption:兑换码类型.
 * Date:2017/3/10
 */
public enum PromoteCategory implements EnumStatus<PromoteCategory> {
    ACTIVE(1,"激活码"),
    GIFT(2,"礼包码"),
    INVITE(3,"邀请码")
    ;

    private int id;
    private String code;

    PromoteCategory(int id, String code) {
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
    public PromoteCategory getById(int id) {
        return valueOf(id);
    }

    /**
     * 根据id获取PromoteCategory.
     *
     * @param id id.
     * @return PromoteCategory.
     */
    public static PromoteCategory valueOf(int id) {
        PromoteCategory[] types = values();
        for (PromoteCategory type : types) {
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
