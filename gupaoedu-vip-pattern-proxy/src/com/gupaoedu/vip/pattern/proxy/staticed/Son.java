package com.gupaoedu.vip.pattern.proxy.staticed;

public class Son implements Person {
    @Override
    public void findLove() {
        System.out.println("我要找对象，找老婆");
    }

    @Override
    public void findJob() {
        System.out.println("我要找工作，找好工作");
    }

    @Override
    public void eat() {
        System.out.println("我要吃饭，吃大餐");
    }
}
