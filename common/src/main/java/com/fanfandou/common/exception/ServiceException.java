package com.fanfandou.common.exception;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * A class to encapsulate error codes. This class is intended for
 * derivation by other classes. ServiceExceptions are represented as static
 * objects. This class defines some set of errors, while derived
 * classes augment the set.
 */
public class ServiceException extends Exception {
    //
    private int id;
    private String detail = "";

    ////////////////////////////////////////////////////////////////////
    //the common services exception id setting.
    ////////////////////////////////////////////////////////////////////
    protected static final int BASE_SERVICE = 0;

    //a=1
    protected static final int BASE_ACCOUNT = 11000;
    protected static final int BASE_AUTH = 14000;
    protected static final int BASE_ADMIN = 16000;

    //b=2
    protected static final int BASE_BILLING = 20000;

    //c=3
    protected static final int BASE_CONNECTION = 30000;

    //d=4
    protected static final int BASE_DB = 50000;

    //e=5
    protected static final int BASE_EVENT = 55000;

    //g=7
    protected static final int BASE_GAME = 70000;

    //l=12
    protected static final int BASE_LINE = 120000;

    //m=13
    protected static final int BASE_MAIL = 130000;
    protected static final int BASE_MESSAGE = 132000;

    //p=16
    protected static final int BASE_PRIZE = 160000;
    protected static final int BASE_PROMOTE = 162000;
    protected static final int BASE_PROPERTIES = 164000;

    //s=19
    protected static final int BASE_STATS = 190000;

    //u=21
    protected static final int BASE_USER = 210000;

    //v=22
    protected static final int BASE_VALIDATE = 220000;

    //a=23
    protected static final int BASE_ACTIVITY = 230000;

    /////////////////////////////////////////////
    //the back end layer exception.
    public static final int UNKNOWN = BASE_SERVICE - 1;
    // A generic exception.
    public static final int GENERIC = BASE_SERVICE + 1;

    //
    protected ServiceException() {
        this(UNKNOWN, "unknown", null);
    }

    protected ServiceException(ServiceException e) {
        this(e.getId(), e.getDetail(), e.getCause());
    }

    public ServiceException(ServiceException e, String detail) {
        this(e.getId(), detail, e.getCause());
    }

    public ServiceException(int id) {
        this(id, "unknown", null);
    }

    public ServiceException(int id, Throwable cause) {
        this(id, "unknown", cause);
    }

    public ServiceException(int id, String detail) {
        this(id, detail, null);
    }

    public ServiceException(int id, String detail, Throwable cause) {
        //
        super(detail, cause);

        //
        this.id = id;
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public String getDetail() {
        return detail;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof ServiceException && id == ((ServiceException) obj).getId();
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}