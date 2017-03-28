package com.fanfandou.platform.api.game.entity;

import com.fanfandou.common.entity.EnumStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Description: 操作类型、也是协议类型.
 * <p/>
 * Date: 2016-05-18 14:27.
 * author: Arvin.
 */
public enum OperationType implements EnumStatus<OperationType> {

    //获取登录key
    GET_LOGIN_KEY(1, "get.login.key"),
    //发送gm指令
    SEND_GM_CMD(2, "send.gm.cmd"),
    //发送公告
    SEND_ANNOUNCE(3, "send.announce"),
    //发送物品
    SEND_ITEM(4, "send.item"),
    //充值发货
    DELIVER_OF_PAY(5, "deliver.pay"),
    //多人发送物品
    MULTI_SEND_ITEM(6,"multi.send.item");

    private int id;
    private String code;

    OperationType(int id, String code) {
        this.id = id;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    @Override
    public OperationType getById(int id) {
        return valueOf(id);
    }

    /**
     * 根据id获取Gender.
     *
     * @param id id.
     * @return Gender.
     */
    public static OperationType valueOf(int id) {
        OperationType[] types = values();
        for (OperationType type : types) {
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
