package com.fanfandou.platform.api.billing.entity;

import com.fanfandou.common.entity.EnumStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by wudi.
 * Descreption:流水.
 * Date:2016/4/28
 */
public enum WalletDetailType implements EnumStatus<WalletDetailType> {

    INFLOW(1,"inflow"),
    OUTFLOW(2,"outflow")
    ;

    private int id;
    private String code;

    WalletDetailType(int id, String code) {
        this.id = id;
        this.code = code;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public WalletDetailType getById(int id) {
        return null;
    }

    public String getCode() {
        return code;
    }

    /**
     * 根据id获取WalletDetailType.
     * @param id id.
     * @return WalletDetailType.
     */
    public static WalletDetailType valueOf(int id) {
        WalletDetailType[] types = values();
        for (WalletDetailType type : types) {
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
