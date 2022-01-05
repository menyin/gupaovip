package gupaoedu.vip.spring.customerinterface;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 所有的接口所代表的事件都发生在IOC容器启动之前，当启动之后这些事件不会再被触发。
 * 比如：InitializingBean接口在bean首次初始化完成后，再对bean设置属性值是不会触发InitializingBean的事件的
 * ？？MyBeanPostProcessor、MyInstantiationAwareBeanPostProcessor两个接口没有触发事件
 */
public class UnitTest {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("customerinterface.xml");
        Student person = (Student)applicationContext.getBean("student");
        System.out.println("====================");
        person.setCar(new Car("卡宴"));


    }
}
