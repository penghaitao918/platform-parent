package com.fanfandou.platform.web.user.vo;

import com.fanfandou.common.entity.ActStatus;
import com.fanfandou.common.entity.SendStatus;
import com.fanfandou.common.entity.resource.SiteCode;
import com.fanfandou.platform.api.user.entity.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by wudi.
 * Descreption:用户信息VO类.
 * Date:2016/3/16
 */
public class UserVo implements Serializable {

    private Long accountId;

    private Long userId;

    @NotNull
    private int siteId;

    @NotNull
    private String channel;

    @NotNull
    private Integer gameId;

    private String accountName;

    private AccountType accountType = AccountType.DEVICE;

    @Length(min = 6,max = 10,message = "密码长度错误")
    private String password;

    private String newPassword;

    private String encryptSeed;

    private AccountStatus accountStatus = AccountStatus.ACTIVE;

    private String accessToken;

    private String refreshToken;

    private String statusExtdata;

    private ActStatus confirmStatus = ActStatus.UNACT;

    private SendStatus sendStatus = SendStatus.REGISTER;

    private String token;

    private AuthTokenType tokenType;

    private ProfileDomain profileDomain;

    private String nickName;

    private String realName;

    private String idcard;

    private String headIcon;

    private Gender gender;

    private Date birthday;

    private String country;

    private String province;

    private String city;

    private AccountStatus profileStatus;

    private String createIp;

    private String createDeviceSerial;

    private Date createTime;

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

    public SiteCode getSite() {
        return SiteCode.getById(siteId);
    }

    public void setSite(int site) {
        this.siteId = site;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
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
        this.accountName = accountName;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getEncryptSeed() {
        return encryptSeed;
    }

    public void setEncryptSeed(String encryptSeed) {
        this.encryptSeed = encryptSeed;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getStatusExtdata() {
        return statusExtdata;
    }

    public void setStatusExtdata(String statusExtdata) {
        this.statusExtdata = statusExtdata;
    }

    public ActStatus getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(ActStatus confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public SendStatus getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(SendStatus sendStatus) {
        this.sendStatus = sendStatus;
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

    public ProfileDomain getProfileDomain() {
        return profileDomain;
    }

    public void setProfileDomain(ProfileDomain profileDomain) {
        this.profileDomain = profileDomain;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public AccountStatus getProfileStatus() {
        return profileStatus;
    }

    public void setProfileStatus(AccountStatus profileStatus) {
        this.profileStatus = profileStatus;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp;
    }

    public String getCreateDeviceSerial() {
        return createDeviceSerial;
    }

    public void setCreateDeviceSerial(String createDeviceSerial) {
        this.createDeviceSerial = createDeviceSerial;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
