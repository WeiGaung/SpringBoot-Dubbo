package com.wenba.rpc.util.db;

import com.wenba.common.annotation.Master;
import com.wenba.common.annotation.Slave;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect
@Component
public class DataSourceAspect {

    @Autowired
    DataSourceInterceptor dataSourceInterceptor;

//      @Pointcut("execution(* com.wenba.service.*impl.*(..))||execution(* com.wenba.rpc.service.*impl.*(..)) " +
//              "|| execution(* com.wenba.service.*.*.impl(..)) || execution(* com.wenba.rpc.service.*.*.Provider.*(..))")
      @Pointcut("execution(* com.wenba.rpc.service.*Provider.*(..)) || execution(* com.wenba.rpc.service.*.*.Provider.*(..))")
      public void controllerAspect() { }

    @Around("controllerAspect()")
    public Object beforeBrowse(ProceedingJoinPoint joinPoint) throws Throwable{
        Method method = ((MethodSignature) (joinPoint.getSignature())).getMethod();
        Annotation annotation = method.getAnnotation(Master.class);
        if(annotation == null) {
            annotation = method.getAnnotation(Slave.class);
        }
        if(annotation != null) {
            DynamicDataSource.DataSourceHolder.set(annotation);
        }
        try {
            return joinPoint.proceed();
        } finally {
            DynamicDataSource.DataSourceHolder.clear();
        }

    }

}
