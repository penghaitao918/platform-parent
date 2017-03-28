package com.fanfandou.platform.serv.user;

import com.fanfandou.common.exception.ServiceException;
import com.fanfandou.platform.serv.third.SmsException;

/**
 * Description: 用于三方短信平台返回值状态的判断
 * Created by wudi on 2016/3/7.
 */
public class SmsResChecker {

    /**
     * 三方异常匹配.
     */
    public static ServiceException checkCode(int errorCode) {
        switch (errorCode) {
            case -1:
                return new SmsException(SmsException.SMS_LACK_ACCOUNT);
            case -2:
                return new SmsException(SmsException.SMS_WRONG_ACCOUNT);
            case -3:
                return new SmsException(SmsException.SMS_LACKED);
            case -4:
                return new SmsException(SmsException.SMS_FORMAT_ERROR);
            case -6:
                return new SmsException(SmsException.SMS_IP_LIMIT);
            case -11:
                return new SmsException(SmsException.SMS_USER_BAN);
            case -14:
                return new SmsException(SmsException.SMS_ILLEGAL_STR);
            case -21:
                return new SmsException(SmsException.SMS_SIGN_ERROR);
            case -41:
                return new SmsException(SmsException.SMS_NO_EMPTY);
            case -42:
                return new SmsException(SmsException.SMS_STR_EMPTY);
            case -51:
                return new SmsException(SmsException.SMS_SIGN_FORMAT_ERROR);
            default:
                return new SmsException(SmsException.SMS_SEND_SUCCESS);
        }
    }

}
