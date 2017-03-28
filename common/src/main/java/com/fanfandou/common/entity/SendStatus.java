package com.fanfandou.common.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by wudi.
 * Descreption:发送状态
 * Date:2016/3/18
 */
public enum SendStatus implements EnumStatus<SendStatus> {

    REGISTER(1, "register"),
    MODIFY(2, "modify"),
    FIND(3,"find");

    private int id;
    private String code;



    SendStatus(int id, String code) {
        this.id = id;
        this.code = code;
    }

    public static SendStatus valueOf(int id) {
        SendStatus[] types = values();
        for (SendStatus type : types) {
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

    public String getCode() {
        return code;
    }

    @Override
    public SendStatus getById(int id) {
        return valueOf(id);
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
