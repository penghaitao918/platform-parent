package com.fanfandou.common.crypto;

import com.fanfandou.common.base.BaseLogger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wudi.
 * Descreption:加密工厂类
 * Date:2016/3/23
 */
public abstract class BaseCipher extends BaseLogger {

    /**
     * MD5加密.
     * @param data 需要加密数据
     */
    public String encrypt(String data) {
        return "";
    }

    /**
     * 对称加密.
     * @param data 需要加密的数据
     * @param password 秘钥
     */
    public String encrypt(String data, String password) {
        return "";
    }

    /**
     * rsa 非对称专业加密.
     * @param data 需要加密的数据
     * @param key  秘钥
     * @param securityType 秘钥类型（private/public）
     * @return 加密后的数据
     */
    public byte[] encrypt(byte[] data, String key, int securityType) throws Exception {
        return null;
    }

    /**
     * 对称解密.
     * @param data 需要解密的数据
     * @param password 秘钥
     */
    public String decrypt(String data, String password) throws Exception {
        return "";
    }

    /**
     * rsa 非对称专业解密.
     * @param data 需要解密的数据
     * @param key  秘钥
     * @param securityType 秘钥类型（private/public）
     * @return 解密后的数据
     */
    public byte[] decrypt(byte[] data, String key, int securityType) throws Exception {
        return null;
    }

    public String getPublicKey(Map<String, Object> keyMap) throws Exception {
        return "";
    }

    public String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        return "";
    }

    public Map<String,Object> getRsaKeyPair() throws Exception {
        return new HashMap<String, Object>();
    }

    /**
     * 获取公钥或者私钥.
     * @param key public and private key
     * @param securityType public or private
     */
    public String getSecurityKey(Map<String,Object> key, int securityType) throws Exception {
        return "";
    }

    public String createSign(byte[] data, String privateKey) throws Exception {
        return "";
    }

    public boolean verify(byte[] data, String publicKey, String sign) throws Exception {
        return false;
    }





}
