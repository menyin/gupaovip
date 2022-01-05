package com.gupaoedu.vip.pattern.singleton.lazy;

public class Person {
    public Person() {
        System.out.println("Person构造函数被调用...");
        Hanlder.test();
    }

    public static class Hanlder{
        public Hanlder() {
            System.out.println("Hanlder构造函数被调用...");
        }
        public static void  test(){}
        public static Person p = new Person();
    }
}
