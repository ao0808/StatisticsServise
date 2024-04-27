package ru.sbrf.statisticsServise.configurationSpring;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import ru.sbrf.statisticsServise.annotation.Metrics;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;

public class MyBeanProxy implements BeanPostProcessor {
    private Set<String> beans = new HashSet<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(isAnnotationCheck(bean)){
            beans.add(beanName);
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    @SneakyThrows
    public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException{
        if(beans.contains(beanName)){
            ClassLoader classLoader = bean.getClass().getClassLoader();
            Class[] interfaces = bean.getClass().getInterfaces();
            return Proxy.newProxyInstance(classLoader, interfaces, new MetricsInvocationHandler(bean));
        }
        return bean;
    }

    private Boolean isAnnotationCheck(@NotNull Object bean) {
        Method[] methods = bean.getClass().getMethods();
        Boolean flag = false;
        for (Method method : methods) {
            if (method.isAnnotationPresent(Metrics.class)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
