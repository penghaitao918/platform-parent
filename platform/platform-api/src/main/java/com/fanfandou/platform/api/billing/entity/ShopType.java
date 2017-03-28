package com.fanfandou.platform.api.billing.entity;

import com.fanfandou.common.entity.EnumStatus;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by wudi.
 * Descreption:商城类型枚举.
 * Date:2016/10/27
 */
public enum ShopType implements EnumStatus<ShopType> {
    RMBSHOP(1,"rmbshop"),//人民币商城
    GEMSHOP(0,"gemshop")//宝石商城
    ;

    private int id;
    private String code;


    ShopType(int id, String code) {
        this.id = id;
        this.code = code;
    }
    @JsonValue
    @Override
    public int getId() {
        return id;
    }

    @Override
    public ShopType getById(int id) {
        return valueOf(id);
    }

    public String getCode() {
        return code;
    }

    public ShopType getByCode(String code) {
        return valueOfCode(code);
    }

    /**
     * 根据id获取ShopType.
     *
     * @param id id.
     * @return ShopType.
     */
    public static ShopType valueOf(int id) {
        ShopType[] types = values();
        for (ShopType type : types) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

    /**
     * 通过code获取shopType.
     * @param code code
     * @return ShopType
     */
    public static ShopType valueOfCode(String code) {
        ShopType[] types = values();
        for (ShopType type : types) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
