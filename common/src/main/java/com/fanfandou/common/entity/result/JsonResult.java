package com.fanfandou.common.entity.result;

import com.fanfandou.common.base.BaseLogger;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by wudi.
 * Descreption:返回值工具类
 * Date:2016/3/14
 */
public class JsonResult extends BaseLogger {
    public static final int SUCCESS = 0;
    public static final int FAIL = -1;
    public static final String SUCCESS_MSG = "SUCCESS";
    public static final String FAIL_MSG = "FAIL";
    private int status;
    private String message;
    private Object data;

    public JsonResult(){
    }

    /**
     * 构造方法.
     * @param status status
     * @param message msg
     * @param data data
     */
    public JsonResult(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public JsonResult(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static final JsonResult RESULT_SUCCESS = new JsonResult(SUCCESS,SUCCESS_MSG);
    public static final JsonResult RESULT_FAIL = new JsonResult(FAIL,FAIL_MSG);

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
