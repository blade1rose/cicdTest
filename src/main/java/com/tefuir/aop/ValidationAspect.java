package com.tefuir.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAspect {
    private static final Logger log = LoggerFactory.getLogger(ValidationAspect.class);

    @Around(value = "execution(* com.tefuir.service.impl.AuthServiceImpl.validateCredentials(..)) && args(account, password)", argNames = "joinPoint,account,password")
    public Object validateInput(ProceedingJoinPoint joinPoint, String account, String password) throws Throwable {
        log.info("切面生效，拦截参数: account={}, password={}", account, password);

        if (account == null || account.trim().isEmpty()) {
            throw new IllegalArgumentException("账户不能为空");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("密码不能为空");
        }

        return joinPoint.proceed();
    }
}