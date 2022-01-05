package gupaoedu.vip.spring.customerinterface;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

import java.beans.PropertyDescriptor;

public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("公共拦截：进入InstantiationAwareBeanPostProcessor接口postProcessBeforeInitialization()方法 在student实例化前发生，beanName="+beanName+"....");
//        return bean;
//    }
//
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("公共拦截：进入InstantiationAwareBeanPostProcessor接口postProcessAfterInitialization()方法 在student实例化后发生，beanName="+beanName+"....");
//        return bean;
//    }

    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if(beanClass == InstantiationAwareBeanPostProcessor.class)return null;//因为当前类实例也是ioc里bean，所以排除它消除干扰
        System.out.println("公共拦截：进入InstantiationAwareBeanPostProcessor接口postProcessBeforeInstantiation()方法 在student实例化前发生，beanName="+beanName+"....");
        return null;
    }

    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if(bean instanceof InstantiationAwareBeanPostProcessor)return true;//因为当前类实例也是ioc里bean，所以排除它消除干扰
        System.out.println("公共拦截：进入InstantiationAwareBeanPostProcessor接口postProcessAfterInstantiation()方法 在student实例化后发生，beanName="+beanName+"....");
        return true;
    }

    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        if(bean instanceof InstantiationAwareBeanPostProcessor)return pvs;//因为当前类实例也是ioc里bean，所以排除它消除干扰
        System.out.println("公共拦截：进入InstantiationAwareBeanPostProcessor接口postProcessPropertyValues()方法 在student实例化后发生，beanName="+beanName+"....");
        return pvs;
    }
}
