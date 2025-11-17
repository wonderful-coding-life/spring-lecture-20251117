package com.example.demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PrintExecutionTimeAspect {
    @Around("@annotation(PrintExecutionTime)")
    public Object printExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        // @PrintExecutionTime -> method()
        Object result = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;
        System.out.println("executed " + executionTime);

        return result;
    }
}
