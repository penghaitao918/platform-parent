package com.fanfandou.platform.api.activity.exception;

import com.fanfandou.common.exception.ServiceException;

/**
 * Created by wudi.
 * Descreption:活动异常.
 * Date:2016/11/22
 */
public class ActivityException extends ServiceException {

    //游戏活动 1 - 50

    //活动crud参数缺失
    public static final int GAME_ACITIVITY_LACK_PARAMS = BASE_ACTIVITY + 1;
    //没有该活动
    public static final int GAME_ACITIVITY_ISEMPTY = BASE_ACTIVITY + 2;
    //客户端传递参数错误.
    public static final int GAME_ACITIVITY_PARAMS_ERRRO = BASE_ACTIVITY + 3;
    //活动ID重复..
    public static final int GAME_ACITIVITY_DUPLECATE_ID = BASE_ACTIVITY + 4;
    //活动已经过期
    public static final int GAME_ACITIVITY_PAST = BASE_ACTIVITY + 5;
    //活动未开始
    public static final int GAMEA_ACTIVITY_UNSTART = BASE_ACTIVITY + 6;


    //游戏礼包码 51 - 100
    public static final int GAME_ACITIVITT_PROMOTE_CODE_UNEXIST = BASE_ACTIVITY + 51;
    //渠道不匹配
    public static final int GAME_ACITIVITT_PROMOTE_CODE_NOTMATCH = BASE_ACTIVITY + 52;
    //激活码已被使用
    public static final int GAME_ACTIVITY_PROMOTE_CODE_DULPLICATE = BASE_ACTIVITY + 53;
    //激活码已过期
    public static final int GAME_ACTIVITY_PROMOTE_CODE_OUTOFDATE = BASE_ACTIVITY + 54;




    public ActivityException(ServiceException e, String msg) {
        super(e, msg);
    }

    public ActivityException(int val, String name) {
        super(val, name);
    }

    public ActivityException(ServiceException e) {
        super(e);
    }

    public ActivityException(int val) {
        super(val);
    }

}
