package com.gupaoedu.vip.zookeeper.prestudy.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 测试lock.lock() 当并发场景下，两个线程同时争抢锁，
 * 则抢到即抢到，抢不到就等待抢到的人释放再抢。
 */
public class Test1 {
    private List<Integer> list = new ArrayList<Integer>();
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        final Test1 test1 = new Test1();
        new Thread(){
            @Override
            public void run() {
                test1.insert(Thread.currentThread());
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                test1.insert(Thread.currentThread());
            }
        }.start();

    }

    private void insert(Thread thread) {
        lock.lock();
        try {
            System.out.println(thread.getName()+"获得了锁");
            for (int i = 0; i < 5; i++) {
                list.add(i);
            }
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
            System.out.println(thread.getName()+"释放了锁");
        }


    }
}
