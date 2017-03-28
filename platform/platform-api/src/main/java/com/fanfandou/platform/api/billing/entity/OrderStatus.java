package com.fanfandou.platform.api.billing.entity;

import com.fanfandou.common.entity.EnumStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by wudi.
 * Descreption:订单状态.
 * Date:2016/4/28
 */
public enum OrderStatus implements EnumStatus<OrderStatus> {
    UNPAID(1,"unpaid"),//待付款
    PAID(2,"paid"),//已付款
    DELIVERING(3,"delivering"),//发货中
    DELIVERED(4,"delivered"),//已发货/发货成功
    REPAIR(5,"repair")//补单
    ;

    private int id;
    private String code;


    OrderStatus(int id, String code) {
        this.id = id;
        this.code = code;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public OrderStatus getById(int id) {
        return valueOf(id);
    }

    public String getCode() {
        return code;
    }

    /**
     * 根据id获取OrderStatus.
     *
     * @param id id.
     * @return OrderStatus.
     */
    public static OrderStatus valueOf(int id) {
        OrderStatus[] types = values();
        for (OrderStatus type : types) {
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
