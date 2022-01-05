package com.gupaoedu.vip.base.server.versiontest;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext-service.xml");
        applicationContext.start();
        System.in.read();
    }
}
