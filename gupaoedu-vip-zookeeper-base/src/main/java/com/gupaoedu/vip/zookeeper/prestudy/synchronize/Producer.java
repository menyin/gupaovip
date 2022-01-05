package com.gupaoedu.vip.zookeeper.prestudy.synchronize;

public class Producer implements  Runnable{
    private Person p;
    public Producer(Person p) {
        this.p = p;
    }

    public void run() {
        int i=0;
        while (true) {
            synchronized (p) {
                if (i % 2 == 0) {
                    this.p.setName("jack");
                    this.p.setSex("man");
                } else {
                    this.p.setName("marry");
                    this.p.setSex("girl");
                }
                i++;

            }

        }
    }
}
