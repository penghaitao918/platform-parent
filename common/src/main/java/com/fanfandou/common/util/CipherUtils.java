package com.fanfandou.common.util;


import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.crypto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wudi.
 * Descreption:各种加密方法工具类.
 * Date:2016/3/22
 */
public class CipherUtils {

    private static Logger logger = LoggerFactory.getLogger(CipherUtils.class);

    public static final int RSA = 100;
    public static final int AES = 200;
    public static final int DES = 300;
    public static final int MD5 = 400;

    public static final int RSA_PRIVATE = 101;
    public static final int RSA_PUBLIC = 102;


    public static BaseCipher initMd5() {
        return new Md5Cipher();
    }

    public static BaseCipher initDes() {
        return new DesCipher();
    }

    public static BaseCipher initAes() {
        return new AesCipher();
    }

    public static BaseCipher initRsa() {
        return new RsaCipher();
    }

    public static BaseCipher initRc4() {
        return new RC4Cipher();
    }

    /**
     * 密码加密.
     *
     * @param passwd      原密码
     * @param encryptSeed 加密种子
     * @return 加密后密码
     */
    public static String getPasswd(String passwd, String encryptSeed) {
        try {
            return CipherUtils.initMd5().encrypt(encryptSeed + CipherUtils.initMd5().encrypt(passwd + encryptSeed));
        } catch (Exception e) {
            logger.error("加密失败", e);
        }
        return "";
    }

}
