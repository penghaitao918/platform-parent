package com.fanfandou.platform.api.billing.entity;

import com.fanfandou.common.entity.EnumStatus;

/**
 * Created by wudi.
 * Descreption:支付类型.
 * Date:2016/4/11
 */
public enum PayType implements EnumStatus {

    FANFANDOU(0,"fanfandou"),//平台币支付
    ALIPAY(1,"alipay"),//支付宝支付
    WECHAT(2,"wechat"),//微信支付
    THIRD(3,"third")//三方支付
    ;

    private int id;
    private String code;

    PayType(int id, String code) {
        this.id = id;
        this.code = code;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Enum getById(int id) {
        return valueOf(id);
    }

    /**
     * 根据id获取PayType.
     * @param id id.
     * @return PayType.
     */
    public static PayType valueOf(int id) {
        PayType[] types = values();
        for (PayType type : types) {
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
