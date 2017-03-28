package com.fanfandou.platform.api.user.entity;

import com.fanfandou.common.entity.ActStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

public class UserAccount implements Serializable {
    private Long accountId;

    private Long userId;

    @NotNull
    private Integer siteId;

    @NotNull
    private String channel;

    @NotNull
    private Integer gameId;

    private String accountName;

    private AccountType accountType = AccountType.DEVICE;

    @Length(min = 6,max = 10,message = "密码长度错误")
    private String password;

    private String encryptSeed;

    private AccountStatus accountStatus = AccountStatus.ACTIVE;

    private String statusExtdata;

    private ActStatus confirmStatus = ActStatus.UNACT;

    private Date confirmTime;

    private String confirmIp;

    private Date createTime;

    private String createIp;

    private String createDeviceSerial;

    private Date lastUpdateTime;

    private String lastUpdateIp;

    private Date lastLoginTime;

    private String lastLoginIp;

    private String lastLoginDeviceSerial;

    public UserAccount(){

    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public ActStatus getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(ActStatus confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEncryptSeed() {
        return encryptSeed;
    }

    public void setEncryptSeed(String encryptSeed) {
        this.encryptSeed = encryptSeed == null ? null : encryptSeed.trim();
    }

    public String getStatusExtdata() {
        return statusExtdata;
    }

    public void setStatusExtdata(String statusExtdata) {
        this.statusExtdata = statusExtdata == null ? null : statusExtdata.trim();
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getConfirmIp() {
        return confirmIp;
    }

    public void setConfirmIp(String confirmIp) {
        this.confirmIp = confirmIp == null ? null : confirmIp.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp == null ? null : createIp.trim();
    }

    public String getCreateDeviceSerial() {
        return createDeviceSerial;
    }

    public void setCreateDeviceSerial(String createDeviceSerial) {
        this.createDeviceSerial = createDeviceSerial == null ? null : createDeviceSerial.trim();
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getLastUpdateIp() {
        return lastUpdateIp;
    }

    public void setLastUpdateIp(String lastUpdateIp) {
        this.lastUpdateIp = lastUpdateIp == null ? null : lastUpdateIp.trim();
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    public String getLastLoginDeviceSerial() {
        return lastLoginDeviceSerial;
    }

    public void setLastLoginDeviceSerial(String lastLoginDeviceSerial) {
        this.lastLoginDeviceSerial = lastLoginDeviceSerial == null ? null : lastLoginDeviceSerial.trim();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}