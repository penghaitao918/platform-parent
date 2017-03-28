package com.fanfandou.platform.api.game.entity;

import java.io.Serializable;

/**
 * Created by wudi.
 * Descreption:记录IP地址和端口的实体.
 * Date:2016/6/7
 */
public class EnterAddress implements Serializable {
    //服务连接IP地址
    private String ipAddress;
    //服务连接端口
    private int port;
    //服务器作用名称
    private String serverName;
    //1 为soket连接方式，2 为数据库连接方式
    private ConnectType connectType = ConnectType.DATABASE;
    //socket连接方式的秘钥
    private String socketKey;
    //数据库名称
    private String sqlName;
    //数据库账号
    private String sqlAccount;
    //数据库密码
    private String sqlPassword;


    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ConnectType getConnectType() {
        return connectType;
    }

    public void setConnectType(ConnectType connectType) {
        this.connectType = connectType;
    }

    public String getSocketKey() {
        return socketKey;
    }

    public void setSocketKey(String socketKey) {
        this.socketKey = socketKey;
    }

    public String getSqlName() {
        return sqlName;
    }

    public void setSqlName(String sqlName) {
        this.sqlName = sqlName;
    }

    public String getSqlAccount() {
        return sqlAccount;
    }

    public void setSqlAccount(String sqlAccount) {
        this.sqlAccount = sqlAccount;
    }

    public String getSqlPassword() {
        return sqlPassword;
    }

    public void setSqlPassword(String sqlPassword) {
        this.sqlPassword = sqlPassword;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    @Override
    public String toString() {
        return "ipAddress : " + ipAddress + ",port : " + port + ",serverName :" + serverName;
    }
}
