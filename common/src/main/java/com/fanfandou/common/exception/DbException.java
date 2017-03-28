package com.fanfandou.common.exception;

/**
 * Description: 数据库异常.
 * <p/>
 * Date: 2016-03-16 15:44.
 * author: Arvin.
 */
public class DbException extends ServiceException {
    //
    public static final int DB_GENERIC = ServiceException.BASE_DB + 1;
    public static final int SQL_GENERIC = ServiceException.BASE_DB + 2;
    public static final int TABLE_SEQUENCE_ERROR = ServiceException.BASE_DB + 3;
    public static final int TABLE_NO_COLUMN = ServiceException.BASE_DB + 4;
    public static final int DUPLICATED_PK = ServiceException.BASE_DB + 5;

    public static final int ENTITY_PARAM_IS_NOT_EXISTS = ServiceException.BASE_DB + 6;

    public DbException(int id) {
        super(id);
    }

    public DbException(int id, Throwable cause) {
        super(id, cause);
    }

    public DbException(int id, String detail) {
        super(id, detail);
    }

    public DbException(int id, String detail, Throwable cause) {
        super(id, detail, cause);
    }
}
