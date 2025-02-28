package ch.jubnl.vsecureflow.backend.helper;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext context;

    public static <T> T getBean(Class<T> requiredType) {
        return context.getBean(requiredType);
    }

    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> requiredType) {
        return context.getBean(beanName, requiredType);
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        SpringContextHolder.context = ctx;
    }
}

