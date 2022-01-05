package gupaoedu.vip.concurrent.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo2 {

    public static void main(String[] args) throws InterruptedException {
        //测试了当获得写锁的情况下
        new Thread(() -> {
            ReadWriteLockClass.setObj("jack","jack is man");
        },"thread-1").start();
        new Thread(() -> {
            ReadWriteLockClass.getObj("jack");
        },"thread-2").start();
    }

}
