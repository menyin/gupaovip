package com.gupaoedu.vip.zookeeper.prestudy.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test3 {
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        final Test3 test3 = new Test3();
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                try {
                    test3.insert(Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                try {
                    test3.insert(Thread.currentThread());
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    System.out.println(Thread.currentThread().getName()+"中断等待，放弃抢锁");
                }
            }
        };
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //如果不加这个代码，则thread2还是等待，直到thread1释放锁，然后抢锁
        //如果加这个代码，则thread2中断等待，直接放弃。
        thread2.interrupt();
    }

    private void insert(Thread thread) throws InterruptedException {
        lock.lockInterruptibly();
        
        long startTime = System.currentTimeMillis();
        try {
            System.out.println(thread.getName()+"获得了锁");
            while (System.currentTimeMillis() - startTime <= 10000) {
            }

        } catch (Exception e) {
            lock.unlock();
            System.out.println(thread.getName()+"释放了锁");
        }finally {
            lock.unlock();
            System.out.println(thread.getName()+"释放了锁");
        }


    }
}
