package com.fanfandou.common.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Description: valid status.
 * <p/>
 * Date: 2016-02-20 15:13.
 * author: Arvin.
 */
public enum ValidStatus implements EnumStatus<ValidStatus> {

    VALID(1, "valid"),
    INVALID(2, "invalid"),
    REMOVED(3, "removed");

    private int id;
    private String code;

    ValidStatus(int id, String code) {
        this.id = id;
        this.code = code;
    }
    @JsonValue
    @Override
    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    @Override
    public ValidStatus getById(int id) {
        return valueOf(id);
    }

    public ValidStatus getByCode(String code) {
        return valueOfCode(code);
    }

    /**
     * 根据id获取AccountType.
     * @param id id.
     * @return AccountType.
     */
    public static ValidStatus valueOf(int id) {
        ValidStatus[] types = values();
        for (ValidStatus type : types) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

    /**
     * 通过code获取ValidStatus.
     * @param code code
     * @return ValidStatus
     */
    public static ValidStatus valueOfCode(String code) {
        ValidStatus[] types = values();
        for (ValidStatus type : types) {
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
