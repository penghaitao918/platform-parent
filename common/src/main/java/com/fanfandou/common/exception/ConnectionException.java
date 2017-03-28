package com.fanfandou.common.exception;

/**
 * Description: 连接/通讯异常.
 * <p/>
 * Date: 2016-03-18 14:35.
 * author: Arvin.
 */
public class ConnectionException extends ServiceException {

    public static final int TIMEOUT = ServiceException.BASE_CONNECTION + 1;
    public static final int UNKNOWN_HOST = ServiceException.BASE_CONNECTION +2;

    public ConnectionException(int id) {
        super(id);
    }

    public ConnectionException(int id, Throwable cause) {
        super(id, cause);
    }

    public ConnectionException(int id, String detail) {
        super(id, detail);
    }

    public ConnectionException(int id, String detail, Throwable cause) {
        super(id, detail, cause);
    }
}
