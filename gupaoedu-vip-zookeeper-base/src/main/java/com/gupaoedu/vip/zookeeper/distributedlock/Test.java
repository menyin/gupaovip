package com.gupaoedu.vip.zookeeper.distributedlock;

import java.util.concurrent.CountDownLatch;

public class Test {
    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                        DistributedLock distributedLock = new DistributedLock();
                        distributedLock.lock();
                        System.out.println(Thread.currentThread().getName() + "执行了业务逻辑");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            countDownLatch.countDown();
        }
    }
}
