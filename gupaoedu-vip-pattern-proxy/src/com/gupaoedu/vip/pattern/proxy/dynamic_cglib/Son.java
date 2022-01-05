package com.gupaoedu.vip.pattern.proxy.dynamic_cglib;

import com.gupaoedu.vip.pattern.proxy.dynamic_jdk.Person;

/*public class Son {
    public void findLove() {
        System.out.println("我要找对象，找老婆");
    }


}*/

public class Son {
    private int age;

    public Son(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void findLove() {
        System.out.println("我要找对象，找老婆"+this.age);
    }


}