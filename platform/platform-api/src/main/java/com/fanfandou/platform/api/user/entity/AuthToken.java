package com.fanfandou.platform.api.user.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: token.
 * <p/>
 * Date: 2016-02-20 15:13.
 * author: Arvin.
 */
public class AuthToken implements Serializable {
    /**
     * token string.
     */
    private String token;

    /**
     * token type.
     */
    private AuthTokenType tokenType;

    /**
     * the application id.
     */
    private int appId;

    /**
     * the related user id.
     */
    private long userId;

    /**
     * the expire seconds of token.
     */
    private long expireSec;

    /**
     * the create time.
     */
    private Date createTime;

    /**
     * the refresh time.
     */
    private Date refreshTime;

    public AuthToken() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AuthTokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(AuthTokenType tokenType) {
        this.tokenType = tokenType;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getExpireSec() {
        return expireSec;
    }

    public void setExpireSec(long expireSec) {
        this.expireSec = expireSec;
    }

    public Date getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(Date refreshTime) {
        this.refreshTime = refreshTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public int hashCode() {
        return token.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return !((obj == null) || !(obj instanceof AuthToken)) && token.equalsIgnoreCase(((AuthToken) obj).getToken());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
