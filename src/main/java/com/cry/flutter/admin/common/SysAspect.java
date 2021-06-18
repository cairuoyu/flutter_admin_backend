package com.cry.flutter.admin.common;


import cn.hutool.extra.servlet.ServletUtil;
import com.cry.flutter.admin.entity.SysLog;
import com.cry.flutter.admin.service.ISysLogService;
import com.cry.flutter.admin.utils.RequestUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


@Aspect
@Component
public class SysAspect {

    @Resource
    private ISysLogService sysLogService;

    @Pointcut("@annotation(com.cry.flutter.admin.common.Operation)")
    public void logPointCut() {
    }

    @AfterReturning("logPointCut()")
//    @Before("logPointCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        SysLog sysLog = new SysLog();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Operation operation = method.getAnnotation(Operation.class);
        if (operation != null) {
            String value = operation.value();
            sysLog.setRequestDesc(value);
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String clientIP = ServletUtil.getClientIP(request,new String[]{"cryip"});
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = method.getName();


        sysLog.setUserId(RequestUtil.getCurrentUserId());
        sysLog.setUserIp(clientIP);
        sysLog.setRequestClass(className);
        sysLog.setRequestMethod(methodName);

        sysLogService.save(sysLog);
    }
}

