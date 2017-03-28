package com.fanfandou.platform.api.billing.entity;

import com.fanfandou.common.entity.EnumStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by wudi.
 * Descreption:币种枚举.
 * Date:2016/4/28
 */
public enum Currency implements EnumStatus<Currency> {
    FANFANDOU(1,"FFD"),//平台币
    CNY(2,"CNY"),//人民币
    USD(3,"USD")//美元
    ;

    private int id;
    private String code;

    Currency(int id, String code) {
        this.id = id;
        this.code = code;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Currency getById(int id) {
        return valueOf(id);
    }

    public String getCode() {
        return code;
    }

    /**
     * 根据id获取ActStatus.
     * @param id id.
     * @return ActStatus.
     */
    public static Currency valueOf(int id) {
        Currency[] types = values();
        for (Currency type : types) {
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
