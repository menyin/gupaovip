package gupaoedu.vip.spring.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 提供了和guava的事件总线的功能
 * 如果ioc容器里的bean有实现ApplicationListener接口，通过ioc容器实例可以向这些bean传递事件
 */
public class EventTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        applicationContext.getBean("eventListener");
//        applicationContext.publishEvent(new EventBean("张三丰","太极创始人"));
        System.out.println("任务完成");
    }
}
