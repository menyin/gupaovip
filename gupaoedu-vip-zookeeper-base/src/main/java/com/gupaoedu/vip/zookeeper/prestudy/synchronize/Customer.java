package com.gupaoedu.vip.zookeeper.prestudy.synchronize;

public class Customer implements Runnable{
    private Person p;

    public Customer(Person p) {
        this.p = p;
    }

    public void run() {
        while (true) {
            synchronized (p) {
                System.out.println(p.getName()+"---"+p.getSex());
            }
        }
    }
}
