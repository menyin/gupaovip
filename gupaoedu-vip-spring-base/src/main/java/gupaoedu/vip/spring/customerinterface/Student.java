package gupaoedu.vip.spring.customerinterface;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * InitializingBean是bean在ioc容器实例化后并依赖注入后调用的一个方法，本质上是让bean在ioc一系列启动操作后可以做一些初始化工作
 * BeanPostProcessor是在InitializingBean接口所指的初始化工作前后做一些拦截
 * BeanNameAware,ApplicationContextAware,BeanFactoryAware这一系列接口都是在ioc将bean实例化并依赖注入之后，在BeanPostProcessor接口之前，将一些东西“感知到”bean里面
 */
public class Student implements InitializingBean,DisposableBean,BeanNameAware,ApplicationContextAware,BeanFactoryAware {
    private String name;
    @Autowired
    private Car car;

    public Student() {
        System.out.println("进入Student的构造方法");
    }

    public String getName() {
        System.out.println("进入Student的getName()方法....");
        return name;
    }

    public void setName(String name) {
        System.out.println("进入Student的setName()方法，name="+name+"  ....");
        this.name = name;
    }

    public Car getCar() {
        System.out.println("进入Student的getCar()方法....");
        return car;
    }

    public void setCar(Car car) {
        System.out.println("进入Student的setCar()方法....");
        this.car = car;
    }

    public void destroy() throws Exception {
        System.out.println("进入Student的DisposableBean接口destroy()方法  ....");

    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("进入Student的InitializingBean接口afterPropertiesSet()方法  ....");
    }

    public void setBeanName(String s) {
        System.out.println("进入Student的BeanNameAware接口setBeanName()方法  感知到bean的名称值为"+s+"....");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("进入Student的ApplicationContextAware接口setApplicationContext()方法 感知到ApplicationContext实例....");
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("进入Student的BeanFactoryAware接口setBeanFactory()方法 感知到BeanFactory实例....");
    }






}
