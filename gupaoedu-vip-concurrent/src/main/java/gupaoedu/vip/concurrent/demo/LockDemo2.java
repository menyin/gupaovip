package gupaoedu.vip.concurrent.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo2 {
    /*测试公平锁和非公平锁*/
    //默认无参为非公平锁
    //static ReentrantLock reentrantLock = new ReentrantLock();
    //带true参数为公平锁
    static ReentrantLock reentrantLock = new ReentrantLock(true);

    public static void main(String[] args) {
        for (int i = 0; i <30; i++) {
            new Thread(new DemoThread(),"thread-"+i).start();
        }

    }
    public static class DemoThread implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"等待锁");
            reentrantLock.lock();
                System.out.println(Thread.currentThread().getName()+"抢到锁");
            reentrantLock.unlock();
        }
    }

}
