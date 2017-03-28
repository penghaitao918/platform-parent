package com.fanfandou.platform.serv.user.service;

import com.alibaba.fastjson.JSONObject;
import com.fanfandou.common.util.CipherUtils;
import com.fanfandou.common.util.HttpUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wudi.
 * Descreption:工具类测试
 * Date:2016/3/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/*.xml")
public class CommonUtilstest {

    @Test
    public void httpTest(){
        try {
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            list.add(new BasicNameValuePair("name","wudi"));
            list.add(new BasicNameValuePair("time","today"));
            System.out.println("以下为输出：");
            System.out.print("这是打印信息："+ HttpUtils.doGet("http://101.69.176.140/reqTest", list));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void createWebSecuTest(){
        try {
        String md5Sign = CipherUtils.initMd5().encrypt
                ("1"+"10025989"+"257"+"hilink"+"gundamw"+"beta"+"web_test"+"1456731403000"+"abm2las");
        System.out.println(md5Sign);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getTimeTest(){

    }

    @Test
    public void securityTest(){
        String str = "abcdefgh"; String pwd = "pokemon1";
        try {
            String md5Str = CipherUtils.initMd5().encrypt(str);
            String desStr = CipherUtils.initDes().encrypt(str,pwd) ;
            String aesStr = CipherUtils.initAes().encrypt(str,pwd);
            System.out.println("MD5:" + md5Str);
            System.out.println("DES 加密:" + desStr + ",解密:"+ CipherUtils.initDes().decrypt(desStr,pwd)+"," +
                    "秘钥为:"+pwd);
            System.out.println("AES 加密:" + aesStr +",解密:"+ CipherUtils.initAes().decrypt(aesStr,
                    pwd)+",秘钥为:"+pwd);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void RsaTest2() {
        Map<String,Object> keyMap = new HashMap<String, Object>();
        try {

            System.out.println("公钥——私钥");
            keyMap = CipherUtils.initRsa().getRsaKeyPair();
            String publicKey = CipherUtils.initRsa().getPublicKey(keyMap);
            String privateKey = CipherUtils.initRsa().getPrivateKey(keyMap);
            System.out.println("RSA 公钥:"+publicKey+"\n私钥:"+ privateKey);

            System.out.println("公钥加密——私钥解密");
            String inputStr = "abc";
            byte[] encodedData = CipherUtils.initRsa().encrypt(inputStr.getBytes(),publicKey,CipherUtils.RSA_PUBLIC);
            byte[] decodedData = CipherUtils.initRsa().decrypt(encodedData,privateKey,CipherUtils.RSA_PRIVATE);

            System.out.println("加密前: " + inputStr + "\n" + "解密后: " + new String(decodedData));

            System.out.println("私钥加密——公钥解密");
            String originStr = "sign";
            //byte[] originData = originStr.getBytes();

            byte[] encodedDatas = CipherUtils.initRsa().encrypt(originStr.getBytes(),privateKey,CipherUtils.RSA_PRIVATE);

            byte[] decodedDatas = CipherUtils.initRsa().decrypt(encodedDatas, publicKey,CipherUtils.RSA_PUBLIC);

            System.out.println("加密前: " + originStr + "\n" + "解密后: " + new String(decodedDatas));

            System.out.println("私钥签名——公钥验证签名");
            // 产生签名
            String sign = CipherUtils.initRsa().createSign(encodedDatas, privateKey);

            System.out.println("签名:" + sign);

            // 验证签名
            boolean status = CipherUtils.initRsa().verify(encodedDatas, publicKey, sign);
            System.out.println("状态:" + status);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
