package com.fanfandou.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * Description: 常用数据验证.
 * <p/>
 * Date: 2016-03-23 15:21.
 * author: Arvin.
 */
public class ValidateUtils {
    /**
     * 正则表达式匹配.
     *
     * @param value 待匹配的文本
     * @param reg 正则表达式
     * @return boolean
     */
    private static boolean match(String value, String reg) {
        return !(StringUtils.isBlank(value) || StringUtils.isBlank(reg)) && Pattern.compile(reg).matcher(value).matches();
    }


    /**
     * 是否是整数.
     *
     * @param value 待匹配的文本
     */
    public static boolean isIntege(String value) {
        return match(value,"^-?[1-9]\\d*$");
    }


    /**
     * 是否是数字.
     *
     * @param value 待匹配的文本
     */
    public static boolean isNum(String value) {
        return match(value,"^([+-]?)\\d*\\.?\\d+$");
    }


    /**
     * 是否是邮件.
     *
     * @param value 待匹配的文本
     */
    public static boolean isEmail(String value) {
        return match(value,"^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
    }

    /**
     * 是否是url.
     *
     * @param value 待匹配的文本
     */
    public static boolean isUrl(String value) {
        return match(value,"^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$");
    }

    /**
     * 是否是中文.
     *
     * @param value 待匹配的文本
     */
    public static boolean isChinese(String value) {
        return match(value,"^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$");
    }

    /**
     * 是否是ACSII字符.
     *
     * @param value 待匹配的文本
     */
    public static boolean isAscii(String value) {
        return match(value, "^[\\x00-\\xFF]+$");
    }

    /**
     * 是否是邮编.
     *
     * @param value 待匹配的文本
     */
    public static boolean isZipcode(String value) {
        return match(value, "^\\d{6}$");
    }

    /**
     * 是否是手机.
     *
     * @param value 待匹配的文本
     */
    public static boolean isMobile(String value) {

        return match(value,"^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$");
    }

    /**
     * 是否是ip地址.
     *
     * @param value 待匹配的文本
     */
    public static boolean isIp(String value) {
        return match(value,"^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$");
    }

}
