package com.fanfandou.common.service;

import com.fanfandou.common.exception.ValidateException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


/**
 * Created by wudi.
 * Descreption:用于过滤jsr异常.
 * Date:2016/3/15
 */
@Aspect
@Component
public class ValidAspect {
    /*@Before是在所拦截方法执行之前执行一段逻辑。
    @After 是在所拦截方法执行之后执行一段逻辑。
    @Around是可以同时在所拦截方法的前后执行一段逻辑。*/

    /**
     * 参数拦截器切面.
     */
    @Around("@annotation(com.fanfandou.common.util.ErrorValidate)")//@annotation：用于匹配当前执行方法持有指定注解的方法；
    public Object doCut(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();//当前正在执行的方法

        Object[] args = pjp.getArgs();//用于匹配当前执行的方法传入的参数为指定类型的执行方法；
        Annotation[][] annotations = method.getParameterAnnotations();//用于匹配当前执行方法持有指定注解的方法
        for (int i = 0; i < annotations.length; i++) {
            if (!hasValidAnnotation(annotations[i])) {
                continue;
            }
            if (!(i < annotations.length - 1 && args[i + 1 ] instanceof BindingResult)) {
                //验证对象后面没有跟RespResult,事实上如果没有应该到不了这一步
                continue;
            }
            BindingResult result = (BindingResult) args[i + 1 ];
            if (result.hasErrors()) {
                throw new ValidateException(ValidateException.JSR_ERROR,"请求参数缺失");
            }
        }
        return pjp.proceed();
    }

    /**
     * 是否包含valid注解.
     */
    private boolean hasValidAnnotation(Annotation[] annotations) {
        if (annotations == null) {
            return false;
        }
        for (Annotation annotation : annotations) {
            if (annotation instanceof Valid) {
                return true;
            }
        }
        return false;
    }

}
