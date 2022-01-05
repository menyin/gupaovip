package gupaoedu.vip.concurrent.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo1 {
    private static ReadWriteLock rwlock=new ReentrantReadWriteLock();


    public static void main(String[] args) {
        //分别测试读锁和写锁
        Lock lock = rwlock.readLock();
//        Lock lock = rwlock.writeLock();
        new Thread(() -> {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"获得了锁");
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"释放了锁");
            lock.unlock();
        },"Thread-1").start();
        new Thread(() -> {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"获得了锁");
            lock.unlock();
        },"Thread-2").start();

    }
}
