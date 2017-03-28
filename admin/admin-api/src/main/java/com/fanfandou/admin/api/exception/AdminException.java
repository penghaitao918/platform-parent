package com.fanfandou.admin.api.exception;

import com.fanfandou.common.exception.ServiceException;

/**
 * Created by wangzhenwei on 2016/8/18.
 * Description adminException.
 */
public class AdminException extends ServiceException{

    //该游戏中该类型的物品code已存在
    public static final int ADMIN_WRONG_ITEMCODE = BASE_ADMIN + 1;

    //该登录名已被注册
    public static final int ADMIN_WRONG_LOGINNAME = BASE_ADMIN + 2;

    //只能删除状态1和4的--未提交和打回的
    public static final int ADMIN_MAIL_APPROVALSTATUS = BASE_ADMIN + 3;

    //excel导入异常，请重试
    public static final int ADMIN_INPUT_EXCEL = BASE_ADMIN + 4;

    //数据字典key重复
    public static final int ADMIN_WRONG_DATADICKEY = BASE_ADMIN + 5;

    //自定义表单code重复
    public static final int ADMIN_WRONG_DIYFORMCODE = BASE_ADMIN + 6;


    public AdminException(ServiceException e, String msg) {
        super(e, msg);
    }

    public AdminException(int val, String name) {
        super(val, name);
    }

    public AdminException(ServiceException e) {
        super(e);
    }

    public AdminException(int val) {
        super(val);
    }
}
