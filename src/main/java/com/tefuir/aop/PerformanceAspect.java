package com.tefuir.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class PerformanceAspect {
    /**execution(访问修饰符? 返回值 包名.类名.?方法名(方法参数) throws 异常?)*/
    @Around("execution(* com.tefuir.service.impl.AuthServiceImpl.validateCredentials(..))")
    public Object measureExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = pjp.proceed();
        long elapsedTime = System.currentTimeMillis() - startTime;

        if(elapsedTime > 100) {
            log.warn("密码验证方法 {} 耗时: {}ms",pjp.getSignature(), elapsedTime);
        }
        return result;
    }
}