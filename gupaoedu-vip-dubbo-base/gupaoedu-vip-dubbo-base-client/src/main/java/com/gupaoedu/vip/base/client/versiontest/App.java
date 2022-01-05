package com.gupaoedu.vip.base.client.versiontest;

import com.gupaoedu.vip.base.server.basetest.Hello;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext= new ClassPathXmlApplicationContext("applicationContext-client.xml");
        applicationContext.start();

        Hello hello1 = (Hello)applicationContext.getBean("hello1");
        hello1.sayHello("我是客户端，hello");
        hello1.sayHi("我是客户端，hi");

        Hello hello2 = (Hello)applicationContext.getBean("hello2");
        hello2.sayHello("我是客户端，hello");
        hello2.sayHi("我是客户端，hi");

    }
}
