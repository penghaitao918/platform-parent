package com.fanfandou.admin.system.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wangzhenwei on 2016/3/19.
 * Description 用户表
 */
public class User implements Serializable {
    private int id;
    //登录名
    private String loginName;
    //密码
    private String password;
    //昵称
    private String nikeName;
    //真实姓名
    private String realName;
    //手机号
    private String mobile;
    //邮箱
    private String email;
    //创建时间
    private Date createTime;
    //登录时间
    private Date loginTime;
    //登录ip
    private String LoginIp;
    //登陆次数
    private int count;
    //加密盐
    private String salt;
    //是否锁定
    private int locked;
    //锁定原因
    private String lockReason;
    //语言
    private String language;
    //备注
    private String memo;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLoginIp() {
        return LoginIp;
    }

    public void setLoginIp(String loginIp) {
        LoginIp = loginIp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getLockReason() {
        return lockReason;
    }

    public void setLockReason(String lockReason) {
        this.lockReason = lockReason;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String userName) {
        this.realName = userName;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("loginName", loginName)
                .append("password", password)
                .append("nikeName", nikeName)
                .append("realName", realName)
                .append("mobile", mobile)
                .append("email", email)
                .append("createTime", createTime)
                .append("loginTime", loginTime)
                .append("LoginIp", LoginIp)
                .append("count", count)
                .append("salt", salt)
                .append("locked", locked)
                .append("lockReason", lockReason)
                .append("language", language)
                .append("memo", memo)
                .toString();
    }
}
