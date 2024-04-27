package ru.sbrf.statisticsServise.tmp;

import ru.sbrf.statisticsServise.model.MetricModel;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

//package ru.sbrf.statisticsService;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StopWatch;
//
//@Aspect
//@Component
//public class MetricsAspect {
//    @Pointcut("@annotation(ru.sbrf.statisticsService.annotation.Metrics)")
//    public void loggableMethod() {
//    }
//    @Around("loggableMethod()")
//    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
//
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        String className = methodSignature.getDeclaringType().getSimpleName();
//        String methodName = methodSignature.getName();
//        final StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        try {
//            return joinPoint.proceed();
//        } finally {
//            stopWatch.stop();
//            System.out.println("Execution time for " + className + "." + methodName + " :: " + stopWatch.getTotalTimeMillis() + " ms");
//        }
//    }
//}

