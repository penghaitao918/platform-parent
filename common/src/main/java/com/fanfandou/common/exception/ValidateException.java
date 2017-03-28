package com.fanfandou.common.exception;

/**
 * Description: 验证异常.
 * <p/>
 * Date: 2016-03-16 15:46.
 * author: Arvin.
 */
public class ValidateException extends ServiceException {

    public static final int INVALID_ARGUMENT = ServiceException.BASE_VALIDATE + 1;

    public static final int SMS_INVALID = ServiceException.BASE_VALIDATE + 200;

    public static final int JSR_ERROR = ServiceException.BASE_VALIDATE + 3;

    public ValidateException(int id) {
        super(id);
    }

    public ValidateException(int id, Throwable cause) {
        super(id, cause);
    }

    public ValidateException(int id, String detail) {
        super(id, detail);
    }

    public ValidateException(int id, String detail, Throwable cause) {
        super(id, detail, cause);
    }
}
