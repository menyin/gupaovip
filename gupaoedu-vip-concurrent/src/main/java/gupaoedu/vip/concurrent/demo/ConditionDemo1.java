package gupaoedu.vip.concurrent.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo1 {
    private static Lock reentrantLock = new ReentrantLock();
    private static Condition condition = reentrantLock.newCondition();

    public static void main(String[] args) {
        //condition的基本使用
        //实现和wait()、notify()同样的阻塞和唤醒功能
        new Thread(new Runnable() {

            @Override
            public void run() {
                reentrantLock.lock();
                try {
                        condition.await();
                        System.out.println(Thread.currentThread().getName() + "进行中...");
                        TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + "出现异常..." + e.getMessage());
                } finally {
                    reentrantLock.unlock();
                }

            }
        }, "thread-1").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    reentrantLock.lock();
                    System.out.println(Thread.currentThread().getName() + "唤醒rentrantLock...");
                    condition.signal();
                } catch (Exception e) {
                    System.out.println(Thread.currentThread().getName() + "出现异常..." + e.getMessage());
                }finally {
                    reentrantLock.unlock();
                }

            }
        }, "thread-2").start();

    }
}
