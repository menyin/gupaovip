package gupaoedu.vip.concurrent.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo2 {
    private static Lock reentrantLock = new ReentrantLock();
    private static Condition fullCondition = reentrantLock.newCondition();
    private static Condition nullCondition = reentrantLock.newCondition();
    private static Object[] buff = new Object[5];
    private static int count = 0;

    public static void main(String[] args) {
        //condition的高级使用
        //需求：各开读线程和写线程各10个，同时进行读写缓冲区内容，当缓冲区为空时所有读线程等待，当缓冲区已满所有写线程等待。
        //注意：1.这里每个读写线程都只读或写一次; 2.reentrantLock.newCondition()得到的并非单例
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    reentrantLock.lock();
                    try {
                        if (count == 0) {

                            nullCondition.await();

                        }
                        System.out.println(Thread.currentThread().getName() + "读取了" + buff[count - 1]);
                        count--;
                        fullCondition.signal();
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + "出现异常" + e.getMessage());
                    } finally {
                        reentrantLock.unlock();
                    }
                }
            }, "Thread-read-" + i).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    reentrantLock.lock();
                    try {
                        if (count == buff.length) {
                            fullCondition.await();
                        }
                        buff[count]=count;
                        System.out.println(Thread.currentThread().getName() + "写入了buff[" + count+"]="+count);
                        count++;
                        nullCondition.signal();
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + "出现异常" + e.getMessage());
                    }finally {
                        reentrantLock.unlock();
                    }
                }
            }, "Thread-write-" + i).start();
        }


    }
}
