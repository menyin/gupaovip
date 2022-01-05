package gupaoedu.vip.spring.customerinterface;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

        System.out.println("公共拦截：进入BeanFactoryPostProcessor接口postProcessBeanFactory()方法  在student等其它bean实例化前发生，可通过接收到configurableListableBeanFactory的实例对student实例化前的元数据进行变更....");
    }
}
