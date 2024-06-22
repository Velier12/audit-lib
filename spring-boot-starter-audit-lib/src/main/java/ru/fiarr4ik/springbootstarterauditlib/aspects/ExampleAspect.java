package ru.fiarr4ik.springbootstarterauditlib.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExampleAspect {

    @Around("@annotation(ru.fiarr4ik.springbootstarterauditlib.annotations.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();
        String methodName = joinPoint.getSignature().getName();


        long executionTime = System.currentTimeMillis() - start;

        System.out.println(joinPoint.getSignature() + " Метод " + methodName + " выполнился за " + executionTime + " ms" );
        return proceed;
    }

}
