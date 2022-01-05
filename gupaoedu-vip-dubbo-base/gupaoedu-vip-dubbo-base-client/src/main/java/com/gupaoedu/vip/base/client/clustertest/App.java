package com.gupaoedu.vip.base.client.clustertest;

import com.gupaoedu.vip.base.server.basetest.Hello;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext= new ClassPathXmlApplicationContext("applicationContext-client.xml");
        applicationContext.start();

        Hello helloCluster= (Hello)applicationContext.getBean("helloCluster");
        String result = helloCluster.sayHello("我是客户端,我测试下cluster属性" );
        System.out.println(result+"客户端时间："+System.currentTimeMillis());
    }
}
