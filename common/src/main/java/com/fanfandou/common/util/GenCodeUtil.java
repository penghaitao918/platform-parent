package com.fanfandou.common.util;

import java.util.UUID;

/**
 * Created by wudi.
 * Descreption:礼包码生成器.
 * Date:2017/3/16
 */
public class GenCodeUtil {

    /**
     * 生成唯一的字符码(数字+英文).
     */
    public static String generateCode(String data) {
        //首先通过RC4生成唯一
        return CipherUtils.initRc4().encrypt(data, UUID.randomUUID().toString()).toUpperCase();
    }

}
