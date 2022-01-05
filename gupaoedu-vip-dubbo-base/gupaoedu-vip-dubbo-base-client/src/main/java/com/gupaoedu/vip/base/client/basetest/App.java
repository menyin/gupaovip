package com.gupaoedu.vip.base.client.basetest;

import com.gupaoedu.vip.base.server.basetest.Hello;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext= new ClassPathXmlApplicationContext("applicationContext-client.xml");
        applicationContext.start();
        Hello hello = (Hello)applicationContext.getBean("hello");
        hello.sayHello("我是客户端，hello");
        hello.sayHi("我是客户端，hi");
    }
}
