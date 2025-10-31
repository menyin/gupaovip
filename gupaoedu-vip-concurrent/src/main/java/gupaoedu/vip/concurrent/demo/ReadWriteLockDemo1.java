package gupaoedu.vip.concurrent.demo;

import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo1 {
    private static ReadWriteLock rwlock=new ReentrantReadWriteLock();

    /**
     * ReentrantReadWriteLock可以保证当有一个线程在写的时候，其它线程是不能读和写的。 但是当没有线程在写，则可以有多个线程进行读。   是这样吗？
     * @param args
     */
    public static void main(String[] args) {
        new Vector<String>().add("");
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
