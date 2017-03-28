package com.fanfandou.platform.web.user.servcie;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Description: 登录类型.
 * <p/>
 * Date: 2016-03-21 17:07.
 * author: Arvin.
 */
public enum LoginType {
    ACCOUNT(1,"login.by.account"),
    TOKEN(2,"login.by.refreshToken"),
    TICKET(3, "login.by.ticket");


    private int id;
    private String code;

    LoginType(int id, String code) {
        this.id = id;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    /**
     * 根据id获取AuthTokenType.
     *
     * @param id id.
     * @return AuthTokenType.
     */
    public static LoginType valueOf(int id) {
        LoginType[] types = values();
        for (LoginType type : types) {
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
