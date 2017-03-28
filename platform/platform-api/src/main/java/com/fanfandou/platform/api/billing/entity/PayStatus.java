package com.fanfandou.platform.api.billing.entity;

import com.fanfandou.common.entity.EnumStatus;

/**
 * Created by wudi.
 * Descreption:支付状态枚举.
 * Date:2016/4/29
 */
public enum PayStatus implements EnumStatus<PayStatus> {
    UNPAID(1,"unpaid"),
    PAID(2,"paid")
    ;

    private int id;
    private String code;

    PayStatus(int id, String code) {
        this.id = id;
        this.code = code;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public PayStatus getById(int id) {
        return valueOf(id);
    }

    /**
     * 根据id获取PayType.
     * @param id id.
     * @return PayType.
     */
    public static PayStatus valueOf(int id) {
        PayStatus[] types = values();
        for (PayStatus type : types) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }
}
