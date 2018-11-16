package com.gupaoedu.vip.pattern.prototype;

public class ShallowTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Shool s1 = new Shool("清华", 1);
        Person p1 = new Person("张三", 18, s1);
        Person p2 = (Person)p1.clone();
//        System.out.println(p1==p2);
        System.out.println(p1.getShool()==p2.getShool());
    }

}
