package gupaoedu.vip.concurrent.demo;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo1 {
    /*基本使用*/

    static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        for (int i = 0; i <3; i++) {
            new Thread(new DemoThread(),"thread-"+i).start();
        }


        try {
            TimeUnit.SECONDS.sleep(1);

            System.out.println("hasQueuedThreads="+reentrantLock.hasQueuedThreads());
            if (reentrantLock.tryLock()) {
                System.out.println("主线程抢锁成功");
            } else {
                System.out.println("主线程抢锁失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static class DemoThread implements Runnable{

        @Override
        public void run() {
            reentrantLock.lock();
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantLock.unlock();
        }
    }

}
