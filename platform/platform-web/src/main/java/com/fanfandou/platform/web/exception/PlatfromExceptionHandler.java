package com.fanfandou.platform.web.exception;

import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.entity.result.ErrorData;
import com.fanfandou.common.entity.result.JsonResult;
import com.fanfandou.common.exception.ServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Description: 异常统一处理.
 * <p/>
 * Date: 2016-04-01 11:30.
 * author: Arvin.
 */
@ControllerAdvice
public class PlatfromExceptionHandler extends BaseLogger {

    /**
     * 异常统一处理.
     * @param ex 所有抛出的异常
     * @return 统一格式的异常信息
     */
    @ExceptionHandler
    @ResponseBody
    public JsonResult resolveException(Exception ex) {
        logger.error("---------------->Exception msg:", ex);
        if (ex instanceof ServiceException) {
            ServiceException serviceException = (ServiceException)ex;
            ErrorData errData = new ErrorData(serviceException.getId(),serviceException.getDetail());
            return new JsonResult(JsonResult.FAIL, JsonResult.FAIL_MSG, errData);
        }
        ErrorData errData = new ErrorData(ServiceException.UNKNOWN, "系统异常，请联系管理员！");
        return new JsonResult(JsonResult.FAIL, JsonResult.FAIL_MSG, errData);
    }
}
