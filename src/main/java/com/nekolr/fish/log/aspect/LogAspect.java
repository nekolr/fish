package com.nekolr.fish.log.aspect;

import com.nekolr.fish.constant.LogType;
import com.nekolr.fish.entity.Log;
import com.nekolr.fish.exception.BadRequestException;
import com.nekolr.fish.security.AuthenticationUser;
import com.nekolr.fish.service.LogService;
import com.nekolr.fish.util.HttpServletRequestHolder;
import com.nekolr.fish.util.IpUtils;
import com.nekolr.fish.util.SecurityContextHolder;
import com.nekolr.fish.util.ThrowableUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 日志切面
 *
 * @author nekolr
 */
@Component
@Aspect
public class LogAspect {

    @Autowired
    private LogService logService;

    /**
     * 登录方法名
     */
    private static final String LOGIN_METHOD_NAME = "login";

    /**
     * 当前时间戳
     */
    private Long currentTime = 0L;

    /**
     * 切点
     */
    @Pointcut("@annotation(com.nekolr.fish.log.annotation.Log)")
    public void logPointcut() {
    }

    /**
     * 环绕通知
     *
     * @param joinPoint
     */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) {
        Object result;

        currentTime = System.currentTimeMillis();

        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            throw new BadRequestException(e.getMessage());
        }

        Log log = new Log(LogType.INFO.getValue(), System.currentTimeMillis() - currentTime);
        this.fillLog(joinPoint, log);
        logService.saveLog(log);

        return result;
    }

    /**
     * 填充日志
     *
     * @param joinPoint
     * @param log
     */
    private void fillLog(ProceedingJoinPoint joinPoint, Log log) {
        // 获取请求
        HttpServletRequest request = HttpServletRequestHolder.getRequest();
        // 获取方法签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        com.nekolr.fish.log.annotation.Log logAnnotation = method.getAnnotation(com.nekolr.fish.log.annotation.Log.class);
        // 获取注解值
        String annotationValue = logAnnotation.value();
        if (StringUtils.isNotBlank(annotationValue)) {
            log.setDescription(annotationValue);
        }
        // 组装方法名称
        String methodName = joinPoint.getTarget().getClass().getName() + "." + methodSignature.getName() + "()";
        log.setMethod(methodName);
        // 获取参数值集合
        Object[] parameterValues = joinPoint.getArgs();
        // 获取参数名称集合
        String[] parameterNames = methodSignature.getParameterNames();
        // 组装参数
        StringBuilder params = new StringBuilder("{");
        if (ArrayUtils.isNotEmpty(parameterValues)) {
            for (int i = 0; i < parameterNames.length; i++) {
                params.append(" " + parameterNames[i] + ":" + parameterValues[i]);
            }
        }
        log.setParams(params.toString() + "}");

        String username;
        if (!LOGIN_METHOD_NAME.equals(methodSignature.getName())) {
            // 如果不是登录方法，则可以直接获取用户信息
            UserDetails userDetails = SecurityContextHolder.getUserDetails();
            username = userDetails.getUsername();
        } else {
            // 如果是登录方法，则需要通过方法参数来获取用户信息
            username = ((AuthenticationUser) parameterValues[0]).getUsername();
        }
        log.setUsername(username);
        log.setIp(IpUtils.getRemoteAddress(request));
    }

    /**
     * 异常通知
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "logPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        Log log = new Log(LogType.ERROR.getValue(), System.currentTimeMillis() - currentTime);
        log.setErrorMessage(ThrowableUtils.getStackTrace(e));
        this.fillLog((ProceedingJoinPoint) joinPoint, log);
        logService.saveLog(log);
    }
}
