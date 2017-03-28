package com.fanfandou.admin.system.test;

import com.fanfandou.common.util.CipherUtils;

/**
 * Created by wangzhenwei on 2016/6/30.
 * Description md5加密测试
 */
public class test {

    public static void main(String[] args){
        String pwd = "Admin@Funfind.c0m";
        String salt = "a123";
        getPWD(pwd,salt);
    }
    public static void getPWD(String pwd,String salt){
        String miPwd = CipherUtils.getPasswd(pwd,salt);
        System.out.println(miPwd);
    }
}
