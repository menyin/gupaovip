package gupaoedu.vip.spring.customerinterface;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 准确的说BeanPostProcessor是拦截了bean的init-method指定的方法前后
 * 而不是ioc对bean进行属性依赖注入的拦截。
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("公共拦截：进入BeanPostProcessor接口postProcessBeforeInitialization()方法 在student初始化前发生，beanName="+beanName+"....");
        return bean;
    }
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("公共拦截：进入BeanPostProcessor接口postProcessAfterInitialization()方法  在student初始化后发生，beanName="+beanName+"....");
        return bean;
    }
}
