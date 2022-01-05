package gupaoedu.vip.spring.base;

import gupaoedu.vip.spring.base.pojo.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UnitTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person = (Person)applicationContext.getBean("person");
        person.setName("zhangsan");
        System.out.println(person.getName());
        int[] dd=new int[]{1};

    }
}
