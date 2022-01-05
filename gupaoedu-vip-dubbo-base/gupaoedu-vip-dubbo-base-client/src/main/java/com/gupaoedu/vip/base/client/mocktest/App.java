package com.gupaoedu.vip.base.client.mocktest;

import com.gupaoedu.vip.base.server.basetest.Hello;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext= new ClassPathXmlApplicationContext("applicationContext-client.xml");
        applicationContext.start();

        Hello helloMock = (Hello)applicationContext.getBean("helloMock");
        String result = helloMock.sayHello("我是客户端，hello 时间：" + System.currentTimeMillis());
        System.out.println("我是客户端，调用sayHello 结果：" + result + " 时间：" + System.currentTimeMillis());
    }
}
