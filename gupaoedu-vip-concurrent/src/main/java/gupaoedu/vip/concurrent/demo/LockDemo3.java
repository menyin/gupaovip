package gupaoedu.vip.concurrent.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo3 {
    /*基本使用*/

    static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new DemoThread(), "thread-1");
        Thread thread2 = new Thread(new DemoThread(), "thread-2");
        Thread thread3 = new Thread(new DemoThread(), "thread-3");
        Thread thread4 = new Thread(new DemoThread(), "thread-4");
        thread1.start();

        TimeUnit.SECONDS.sleep(1);
        thread2.start();
        thread3.start();
        thread4.start();
        TimeUnit.SECONDS.sleep(4);
        thread2.interrupt();
        thread3.interrupt();
        thread4.interrupt();

    }

    public static class DemoThread implements Runnable {

        @Override
        public void run() {
            try {
                reentrantLock.lockInterruptibly();
                System.out.println(Thread.currentThread().getName() + "抢占了锁");
                TimeUnit.SECONDS.sleep(10);
                reentrantLock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放了锁");
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "被interrupt了：" + e.getMessage());
            }
        }


    }

}
