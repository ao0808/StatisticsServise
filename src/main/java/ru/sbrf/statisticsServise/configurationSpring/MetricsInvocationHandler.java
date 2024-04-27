package ru.sbrf.statisticsServise.configurationSpring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.sbrf.statisticsServise.annotation.Metrics;
import ru.sbrf.statisticsServise.implementation.report.ReportHolder;
import ru.sbrf.statisticsServise.model.MetricModel;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MetricsInvocationHandler implements InvocationHandler {
    private static final Logger logger = LoggerFactory.getLogger(MetricsInvocationHandler.class);
    private final Object target;
    private static int id = 1;

    public MetricsInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.nanoTime();
        Object result = method.invoke(target, args);
        long elapsed = System.nanoTime() - start;
        if (method.isAnnotationPresent(Metrics.class)) {
            logger.info("ID: " + id + " Class: " + target.getClass().getSimpleName() + " Method: " + method.getName()
                    + " ExecutionTime: " + (elapsed / 100000));
            ReportHolder.get().add(MetricModel.builder().build());
            id++;
        }
        return result;
    }
}
