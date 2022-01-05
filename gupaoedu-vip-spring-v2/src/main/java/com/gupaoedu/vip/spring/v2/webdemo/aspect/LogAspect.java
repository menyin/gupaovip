package com.gupaoedu.vip.spring.v2.webdemo.aspect;

public class LogAspect {
    public void before(){
        System.out.println("日志.....前");
    }
    public void after(){
        System.out.println("日志.....后");
    }
}
