package com.sina.aopSysLog;

import com.sina.domain.SysLog;
import com.sina.service.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import sun.security.util.SecurityConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@Aspect
public class SysLogAOP {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    @Around("execution(* com.sina.controller.*.*(..))")
    public Object saveLog(ProceedingJoinPoint joinPoint){
        try {
            /*
                     * 主键 无意义uuid
                     * 访问时间
                     * 操作者用户名
                     * 访问ip
                     * 访问资源url
                     * 执行时长
                     * 访问方法
                     */
            //访问时间
            Date visitTime = new Date();
            //访问的用户名
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            long start = System.currentTimeMillis();
            //执行切入点方法
            Object proceed = joinPoint.proceed();
            //访问者的ip地址
            String ip = request.getRemoteAddr();
            //访问者的url
            String url = request.getRequestURI();
            long end = System.currentTimeMillis();

            //执行时长
            long executionTime = end-start;

            //访问方法
            //获取切入点方法所在类的字节码对象
            String name = joinPoint.getTarget().getClass().getName();
            //获取切入点方法名称
            String methodName = joinPoint.getSignature().getName();
            String method = name+"."+methodName;


            //将相关数据封装到SysLog对象中
            SysLog sysLog = new SysLog();
            sysLog.setVisitTime(visitTime);
            sysLog.setUsername(username);
            sysLog.setIp(ip);
            sysLog.setUrl(url);
            sysLog.setExecutionTime(executionTime);
            sysLog.setMethod(method);

            //调用方法保存日志信息
            System.out.println(sysLog);
            sysLogService.saveSysLog(sysLog);

            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return null;
    }
}
