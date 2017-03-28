package com.fanfandou.common.crypto;

import org.apache.commons.codec.binary.Base64;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;




/**
 * Created by wudi.
 * Descreption:Aes对称加密
 * Date:2016/3/23
 */
public class AesCipher extends BaseCipher {

    /*AES算法基于排列和置换运算。排列是对数据重新进行安排，置换是将一个数据单元替换为另一个。
    AES使用几种不同的方法来执行排列和置换运算。AES是一个迭代的、对称密钥分组的密码，它可以使用128、192和256位密钥，
    并且用128位（16字节）分组加密和解密数据。*/
    //密钥算法
    public static final String AES_KEY_ALGORITHM = "AES";

    //加解密算法/工作模式/填充方式,Java6.0支持PKCS5Padding填充方式,BouncyCastle支持PKCS7Padding填充方式
    public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";


    /**
     * 转换密钥.
     */
    public Key toKey(byte[] key) throws Exception {
        return new SecretKeySpec(key, AES_KEY_ALGORITHM);
    }

    /**
     * 加密数据.
     * @param data 待加密数据
     * @param password  密钥
     * @return 加密后的数据
     * */
    @Override
    public String encrypt(String data, String password)  {
        String returnValue = null;
        try {
            KeyGenerator kg = KeyGenerator.getInstance(AES_KEY_ALGORITHM); //实例化密钥生成器
            kg.init(128, new SecureRandom(password.getBytes())); //初始化密钥生成器:AES要求密钥长度为128,192,256位
            SecretKey secretKey = kg.generateKey();
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);              //实例化Cipher对象，它用于完成实际的加密操作
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);                               //初始化Cipher对象，设置为加密模式
            returnValue =  Base64.encodeBase64String(cipher.doFinal(data.getBytes())); //执行加密操作。加密后的结果通常都会用Base64编码进行传输
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException p) {
            p.printStackTrace();
        } catch (InvalidKeyException i) {
            i.printStackTrace();
        } catch (BadPaddingException b) {
            b.printStackTrace();
        } catch (IllegalBlockSizeException s) {
            s.printStackTrace();
        }
        return returnValue;
    }

    /**
     * 解密数据.
     * @param data 待解密数据
     * @param password  密钥
     * @return 解密后的数据
     * */
    @Override
    public String decrypt(String data, String password) throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(AES_KEY_ALGORITHM); //实例化密钥生成器
        kg.init(128,new SecureRandom(password.getBytes())); //初始化密钥生成器:AES要求密钥长度为128,192,256位
        SecretKey secretKey = kg.generateKey();
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);                          //初始化Cipher对象，设置为解密模式
        return new String(cipher.doFinal(Base64.decodeBase64(data))); //执行解密操作
    }

}
