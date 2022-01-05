package com.gupaoedu.vip.zookeeper.prestudy.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试lock.tryLock() 当并发场景下，两个线程同时争抢锁，
 * 则抢到即抢到，抢不到就放弃不会等待。
 */
public class Test2 {
    private List<Integer> list = new ArrayList<Integer>();
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        final Test2 test2 = new Test2();
        final CountDownLatch countDownLatch = new CountDownLatch(2);//用它构造并发场景
        new Thread(){
            @Override
            public void run() {
                try {
                    countDownLatch.await();
                    test2.insert(Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        countDownLatch.countDown();
        new Thread(){
            @Override
            public void run() {
                try {
                    countDownLatch.await();
                    test2.insert(Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        countDownLatch.countDown();
    }

    private void insert(Thread thread) {
        if (lock.tryLock()) {
            try {
                System.out.println(thread.getName()+"获得了锁");
                for (int i = 0; i < 5; i++) {
                    list.add(i);
                }
//                System.out.println(list);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
                System.out.println(thread.getName()+"释放了锁");
            }
        } else {
            System.out.println(thread.getName()+"获取锁失败");
        }
    }
}
