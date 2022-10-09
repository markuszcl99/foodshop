package com.markus.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author: markus
 * @date: 2022/10/9 11:42 PM
 * @Description: service层方法日志记录切面
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@Aspect
@Component
public class ServiceLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);

    /**
     * AOP通知类型包括：
     * 1. 前置通知-方法调用前执行
     * 2. 后置通知-方法正常执行后执行
     * 3. 异常通知-方法异常执行后执行
     * 4. 最终通知-方法执行后执行
     * 5. 环绕通知-方法执行前置、后置等通知执行
     * 采用环绕通知,记录service层方法执行的耗时情况
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around(value = "execution(* com.markus.service.impl..*.*(..))")
    public Object recordTimeServiceMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        logger.info("========开始执行{}.{}=============",
                proceedingJoinPoint.getTarget().getClass(),
                proceedingJoinPoint.getSignature().getName());

        long startTime = System.currentTimeMillis();
        Object o = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;
        if (duration > 1000) {
            logger.error("====== 执行结束，耗时：{} 毫秒 ======", duration);
        } else if (duration > 500) {
            logger.warn("====== 执行结束，耗时：{} 毫秒 ======", duration);
        } else {
            logger.info("====== 执行结束，耗时：{} 毫秒 ======", duration);
        }
        return o;
    }
}
