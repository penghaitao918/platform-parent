package com.fanfandou.platform.api.user.exception;

import com.fanfandou.common.exception.ServiceException;

/**
 * Created by wudi.
 * Descreption:用户异常类
 * Date:2016/3/18
 */
public class UserException extends ServiceException {


    //BASE_ACCOUNT + 1 - 10 用于 手机号码验证
    //账号已注册
    public static final int ACCNOUNT_DUPLICATE = BASE_ACCOUNT + 1;
    //手机号码格式错误
    public static final int PHONENO_FORMAT_INVALID = BASE_ACCOUNT + 2;
    //验证码不匹配
    public static final int PHONENO_SIGN_CODE_ERROR = BASE_ACCOUNT + 3;
    //初始密码无效
    public static final int ORIGINAL_PWD_INVALID = BASE_ACCOUNT + 4;


    //验证码token为空
    public static final int SIGN_CODE_EMPTY = BASE_ACCOUNT + 5;
    //验证码token不匹配
    public static final int SIGN_CODE_INVALID = BASE_ACCOUNT + 6;


    //BASE_ACCOUNT + 11-20 用于 邮箱问题
    //邮箱格式不对
    public static final int EMAIL_FORMAT_INVALID = BASE_ACCOUNT + 11;

    //BASE_USER+1-100 用于用户业务异常
    //账号不存在
    public static final int USER_NOT_FOUND = BASE_USER + 1;
    //密码错误
    public static final int USER_PASSWD_INVALID = BASE_USER + 2;
    //token不存在
    public static final int USER_TOKEN_NOT_FOUND = BASE_USER + 3;
    //用户还未激活
    public static final int USER_NOT_ACTIVATE = BASE_USER + 4;
    //用户被封号
    public static final int USER_BANNED = BASE_USER + 5;
    //用户被停用
    public static final int USER_DEACTIVATED = BASE_USER + 6;
    //用户site无效
    public static final int USER_INVALID_SITE = BASE_USER + 7;
    //用户token无效
    public static final int USER_TOKEN_INVALID = BASE_USER + 8;

    //userId错误
    public static final int USER_ID_ERRRO = BASE_USER + 10;

    //登录类型错误
    public static final int USER_LOGIN_TYPE_INVALID = BASE_USER + 11;

    //登录验证无效
    public static final int USER_LOGIN_INVALIED = BASE_USER + 12;

    //用户不在白名单内
    public static final int USER_EXCEPT_WHITE_LIST = BASE_USER + 13;

    public UserException(ServiceException e, String msg) {
        super(e, msg);
    }

    public UserException(int val, String name) {
        super(val, name);
    }

    public UserException(ServiceException e) {
        super(e);
    }

    public UserException(int val) {
        super(val);
    }
}
