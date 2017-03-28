package com.fanfandou.platform.serv.third;

import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.common.exception.ValidateException;

/**
 * Created by wudi.
 * Descreption:三方异常信息类.
 * Date:2016/3/11
 */
public class SmsException extends ValidateException {

    //三方短信接口异常 负数
    //没有该用户账户
    public static final int SMS_LACK_ACCOUNT = ValidateException.SMS_INVALID + 1;
    //不是账户登陆密码
    public static final int SMS_WRONG_ACCOUNT = ValidateException.SMS_INVALID + 2;
    //"短信数量不足
    public static final int SMS_LACKED = ValidateException.SMS_INVALID + 3;
    //"手机号格式不正确
    public static final int SMS_FORMAT_ERROR = ValidateException.SMS_INVALID + 4;
    //"IP限制
    public static final int SMS_IP_LIMIT = ValidateException.SMS_INVALID + 2;
    //"该用户被禁用
    public static final int SMS_USER_BAN = ValidateException.SMS_INVALID + 6;
    //"短信内容出现非法字符
    public static final int SMS_ILLEGAL_STR = ValidateException.SMS_INVALID + 7 ;
    //"MD5接口密钥加密不正确
    public static final int SMS_SIGN_ERROR = ValidateException.SMS_INVALID + 8;
    //"手机号码为空
    public static final int SMS_NO_EMPTY = ValidateException.SMS_INVALID + 9;
    //"短信内容为空
    public static final int SMS_STR_EMPTY = ValidateException.SMS_INVALID + 10;
    //"短信签名格式不正确
    public static final int SMS_SIGN_FORMAT_ERROR = ValidateException.SMS_INVALID + 11;
    //"短信发送成功
    public static final int SMS_SEND_SUCCESS = ValidateException.SMS_INVALID + 12;

    public SmsException(int id, Throwable cause) {
        super(id, cause);
    }

    public SmsException(int id, String detail) {
        super(id, detail);
    }

    public SmsException(int id, String detail, Throwable cause) {
        super(id, detail, cause);
    }

    public SmsException(int id) {
        super(id);
    }
}
