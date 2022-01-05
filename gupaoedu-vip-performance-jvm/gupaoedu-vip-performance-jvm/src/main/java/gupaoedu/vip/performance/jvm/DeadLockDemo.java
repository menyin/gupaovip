package gupaoedu.vip.performance.jvm;

import java.util.Objects;

/**
 * 模拟死锁，运行后用jvisualvm查看该进程里的线程情况，发现有两个线程进入了监视状态，即死锁中相互等待状态
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        final Object left=new Object();
        final Object right=new Object();
        new Thread(new Runnable() {
            public void run() {
                synchronized (left) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("leftRight start!");
                    synchronized (right) {
                        System.out.println("leftRight end!");
                    }
                }
            }
        },"thread-left").start();
        new Thread(new Runnable() {
            public void run() {
                synchronized (right) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("rightLeft start!");
                    synchronized (left) {
                        System.out.println("rightLeft end!");
                    }
                }
            }
        },"thread-right").start();
    }
}
