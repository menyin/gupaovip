package gupaoedu.vip.concurrent.demo;

import java.rmi.StubNotFoundException;
import java.util.concurrent.TimeUnit;

public class StatusDemo {
    public static void main(String[] args) {
        /*new Thread(()->{
            System.out.println("TimeWaiting begin");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("TimeWaiting end");
        },"TimeWaiting").start();*/

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                synchronized(StatusDemo.class){
                    try {
                        System.out.println("wait begin");
                        StatusDemo.class.wait();
                        System.out.println("wait end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "wait").start();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
            synchronized (StatusDemo.class){//要释放上面的等待就必须找到它，所以要找到这个锁对象
//                StatusDemo.class.notify();
                StatusDemo.class.notifyAll();
                //notifyAll是唤醒所有锁所在的线程，而notify只是唤醒第一个线程
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

       /* new Thread(new ThreadDemo(),"blocking-1").start();
        new Thread(new ThreadDemo(),"blocking-2").start();*/


    }
    public static class ThreadDemo implements Runnable{
        @Override
        public void run() {
            synchronized (ThreadDemo.class) {
                System.out.println("当前占用锁的线程是"+Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
