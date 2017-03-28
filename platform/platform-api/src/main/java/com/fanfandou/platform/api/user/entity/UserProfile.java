package com.fanfandou.platform.api.user.entity;

import com.fanfandou.common.entity.ActStatus;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class UserProfile implements Serializable {
    private Long userId;

    @NotNull
    private Integer siteId;

    @NotNull
    private String channel;

    @NotNull
    private Integer gameId;

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

    private String statusExtdata;

    private String profileDesc;

    private ActStatus auditStatus;

    private Long auditUserId;

    private Date auditTime;

    private String auditIp;

    private Date createTime;

    private String createIp;

    private String createDeviceSerial;

    private Date lastUpdateTime;

    private String lastUpdateIp;

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

    public ProfileDomain getProfileDomain() {
        return profileDomain;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon == null ? null : headIcon.trim();
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
        this.country = country == null ? null : country.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getStatusExtdata() {
        return statusExtdata;
    }

    public void setStatusExtdata(String statusExtdata) {
        this.statusExtdata = statusExtdata == null ? null : statusExtdata.trim();
    }

    public String getProfileDesc() {
        return profileDesc;
    }

    public void setProfileDesc(String profileDesc) {
        this.profileDesc = profileDesc == null ? null : profileDesc.trim();
    }

    public void setProfileDomain(ProfileDomain profileDomain) {
        this.profileDomain = profileDomain;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public AccountStatus getProfileStatus() {
        return profileStatus;
    }

    public void setProfileStatus(AccountStatus profileStatus) {
        this.profileStatus = profileStatus;
    }

    public ActStatus getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(ActStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Long getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(Long auditUserId) {
        this.auditUserId = auditUserId;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditIp() {
        return auditIp;
    }

    public void setAuditIp(String auditIp) {
        this.auditIp = auditIp == null ? null : auditIp.trim();
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}