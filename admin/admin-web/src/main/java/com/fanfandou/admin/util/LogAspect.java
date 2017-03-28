package com.fanfandou.admin.util;

import com.fanfandou.admin.system.entity.SysLog;
import com.fanfandou.admin.system.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private SysLogService logService;//日志记录Service

    /**
     * 添加业务逻辑方法切入点.
     */
    @Pointcut("execution(* com.fanfandou.admin..*.service.*.add*(..))")
    public void insertServiceCall() {
        System.out.println("-----------------save--------------------");
    }

    /**
     * 修改业务逻辑方法切入点.
     */
    @Pointcut("execution(* com.fanfandou.admin..*.service.*.upd*(..))")
    public void updateServiceCall() {
        System.out.println("-----------------update--------------------");
    }


//    @Pointcut("@annotation(com.fanfandou.admin.util.LogUpd)")
//    public void updateServiceCall() {
//        System.out.println("-----------------update--------------------");
//    }

    /**
     * 刪除业务逻辑方法切入点.
     */
    @Pointcut("execution(* com.fanfandou.admin..*.service.*.del*(..))")
    public void deleteServiceCall() {
        System.out.println("-----------------delete--------------------");
    }

//    @Pointcut("@annotation(com.fanfandou.admin.util.LogAdd)")
//    public  void controllerAspect() {
//
//
//    }
//    @Around("@annotation(com.fanfandou.admin.util.LogUpd)")//@annotation：用于匹配当前执行方法持有指定注解的方法；
//    public void doCut(ProceedingJoinPoint joinPoint) throws Throwable {
//        Integer userId = logService.getUserId();
//
//        // 获取方法名
//        String methodName = joinPoint.getSignature().getName();
//        // 获取操作内容
//        String opContent = adminOptionContent(joinPoint.getArgs(), methodName);
//        // 创建日志对象
//        SysLog log = new SysLog();
//        log.setUserId(userId);// 设置用戶id
//        log.setCreateTime(new Date());// 操作时间
//        log.setContent(opContent);// 操作内容
//        log.setOperation("添加");// 操作
//        logService.log(log);// 添加日志
//    }


    /**
     * 用戶添加操作日志(后置通知).
     */
    @AfterReturning(value = "insertServiceCall()", argNames = "joinPoint,rtv", returning = "rtv")
    public void insertServiceCallCalls(JoinPoint joinPoint, Object rtv) {
        try {
            Integer userId = logService.getUserId();

            // 判断参数
            if (joinPoint.getArgs() == null) {
                // 没有参数
                return;
            }

            // 获取方法名
            String methodName = joinPoint.getSignature().getName();
            // 获取操作内容
            String opContent = adminOptionContent(joinPoint.getArgs(), methodName);
            // 创建日志对象
            SysLog log = new SysLog();
            log.setUserId(userId);// 设置用戶id
            log.setCreateTime(new Date());// 操作时间
            log.setContent(opContent);// 操作内容
            log.setOperation("添加");// 操作
            log.setLoginName(logService.getUserName());
            System.out.println("++++++++++++:" + rtv);
            //int ret = (Integer) rtv;
            //if (ret >= 1) {
            //     log.setOperationResult("成功");
            //} else {
            //      log.setOperationResult("失败");
            //  }
            logService.log(log);// 添加日志
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 用戶修改操作日志(后置通知).
     */
    @AfterReturning(value = "updateServiceCall()", argNames = "joinPoint,rtv", returning = "rtv")
    public void updateServiceCallCalls(JoinPoint joinPoint, Object rtv) {
        try {
            Integer userId = logService.getUserId();

            // 判断参数
            if (joinPoint.getArgs() == null) {
                // 没有参数
                return;
            }
            // 获取方法名
            String methodName = joinPoint.getSignature().getName();
            // 获取操作内容
            String opContent = adminOptionContent(joinPoint.getArgs(), methodName);
            // 创建日志对象
            SysLog log = new SysLog();
            log.setUserId(userId);// 设置用戶id
            log.setCreateTime(new Date());// 操作时间
            log.setContent(opContent);// 操作内容
            log.setOperation("修改");// 操作
            log.setLoginName(logService.getUserName());
            System.out.println("++++++++++++:" + rtv);
        /*int ret = (Integer) rtv;
        if (ret >= 1) {
            log.setOperationResult("成功");
        } else {
            log.setOperationResult("失败");
        }  */
            logService.log(log);// 添加日志
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 用戶刪除操作日志(后置通知).
     */
    @AfterReturning(value = "deleteServiceCall()", argNames = "joinPoint,rtv", returning = "rtv")
    public void deleteServiceCallCalls(JoinPoint joinPoint, Object rtv) {
        try {
            Integer userId = logService.getUserId();
            // 判断参数
            if (joinPoint.getArgs() == null) {
                // 没有参数
                return;
            }
            // 获取方法名
            String methodName = joinPoint.getSignature().getName();
            // 获取操作内容
            String opContent = adminOptionContent(joinPoint.getArgs(), methodName);
            // 创建日志对象
            SysLog log = new SysLog();
            log.setUserId(userId);// 设置用戶id
            log.setCreateTime(new Date());// 操作时间
            log.setContent(opContent);// 操作内容
            log.setOperation("删除");// 操作
            log.setLoginName(logService.getUserName());
            System.out.println("++++++++++++:" + rtv);
            //int ret = (Integer) rtv;
            //if (ret >= 1) {
            //    log.setOperationResult("成功");
            //} else {
            //    log.setOperationResult("失败");
            //}
            logService.log(log);// 添加日志
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 使用Java反射来获取被拦截方法(insert、update)的参数值， 将参数值拼接为操作内容.
     */
    public String adminOptionContent(Object[] args, String mName) {
        try {
            if (args == null) {
                return null;
            }
            StringBuffer rs = new StringBuffer();
            rs.append(mName);
            String className;
            int index = 1;
            // 遍历参数对象
            for (Object info : args) {
                // 获取对象类型
                className = info.getClass().getName();
                className = className.substring(className.lastIndexOf(".") + 1);
                rs.append("[参数" + index + "，类型：" + className + "，值：");
                // 获取对象的所有方法
                Method[] methods = info.getClass().getDeclaredMethods();
                // 遍历方法，判断get方法
                for (Method method : methods) {
                    String methodName = method.getName();
                    // 判断是不是get方法
                    if (!methodName.contains("get")) {
                        // 不是get方法
                        continue;// 不处理
                    }
                    Object rsValue;
                    try {
                        // 调用get方法，获取返回值
                        rsValue = method.invoke(info);
                        if (rsValue == null) {
                            // 没有返回值
                            continue;
                        }
                    } catch (Exception e) {
                        continue;
                    }
                    // 将值加入内容中
                    rs.append("(" + methodName + " : " + rsValue + ")");
                }
                rs.append("]");
                index++;
            }
            return rs.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

}