package com.fanfandou.common.entity.result;

/**
 * Description: 错误信息.
 * <p/>
 * Date: 2016-04-01 15:35.
 * author: Arvin.
 */
public class ErrorData {
    private int errorCode;
    private String errorMessage;

    /**
     * 构造方法.
     * @param errorCode exception id
     * @param errorMessage msg
     */
    public ErrorData(int errorCode, String errorMessage) {

        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
