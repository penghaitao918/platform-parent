package com.fanfandou.platform.api.user.entity;

import com.fanfandou.common.entity.EnumStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Description: token type.
 * <p/>
 * Date: 2016-02-20 15:15.
 * author: Arvin.
 */
public enum AuthTokenType implements EnumStatus<AuthTokenType> {
    REFRESH(1, "refresh", 30 * 24 * 60 * 60L),
    ACCESS(2, "access", 24 * 60 * 60L),
    VERIFY(3, "verify", 10 * 60L),
    TMP(4, "tmp", 60L),
    TICKET(5, "ticket", 30L);

    private int id;
    private String code;
    /**
     * expire seconds.
     */
    private long expireSec;

    AuthTokenType(int id, String code, long expireSec) {
        this.id = id;
        this.code = code.toLowerCase();
        this.expireSec = expireSec;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public long getExpireSec() {
        return expireSec;
    }

    @Override
    public AuthTokenType getById(int id) {
        return valueOf(id);
    }

    /**
     * 根据id获取AuthTokenType.
     *
     * @param id id.
     * @return AuthTokenType.
     */
    public static AuthTokenType valueOf(int id) {
        AuthTokenType[] types = values();
        for (AuthTokenType type : types) {
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
