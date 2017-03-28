package com.fanfandou.platform.api.user.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Set;

/**
 * Description: 平台用户：包含账号和用户信息.
 * <p/>
 * Date: 2016-02-20 15:37.
 * author: Arvin.
 */
public class PlatformUser implements Serializable {

    private UserAccount currentAccount;
    private UserProfile userProfile;
    private Set<UserAccount> accountAll;
    private long userId;
    private String refreshToken;

    /**
     * 构造方法.
     * @param userAccount account
     * @param userProfile profile
     */
    public PlatformUser(UserAccount userAccount, UserProfile userProfile) {
        this.currentAccount = userAccount;
        this.userProfile = userProfile;
        this.userId = userProfile.getUserId();
    }

    public PlatformUser() {
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Set<UserAccount> getAccountAll() {
        return accountAll;
    }

    public void setAccountAll(Set<UserAccount> accountAll) {
        this.accountAll = accountAll;
    }

    public UserAccount getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(UserAccount currentAccount) {
        this.currentAccount = currentAccount;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
