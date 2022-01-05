package com.gupaoedu.vip.distributed.io.dabaiprojetstudy;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TimerDemo {
    public static void main(String[] args) throws IOException {
//        new HashedWheelTimer(new NamedThreadFactory(ThreadNames.T_CONN_TIMER),tickDuration, TimeUnit.MILLISECONDS, ticksPerWheel);
       /* HashedWheelTimer timer = new HashedWheelTimer(
                Executors.defaultThreadFactory(),
                1000,
                TimeUnit.MILLISECONDS,
                12);
        TimerTask task = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("执行任务1："+new Date().toLocaleString());
                timer.newTimeout(this, 3000, TimeUnit.MILLISECONDS);
            }
        };
        timer.newTimeout(task, 3000, TimeUnit.MILLISECONDS);*/

        final HashedWheelTimer timer = new HashedWheelTimer(
                new ThreadFactory() {
                    private AtomicInteger count = new AtomicInteger(1);

                    @Override
                    public Thread newThread(Runnable r) {
                        System.out.println("线程工厂被调用 " + count.getAndIncrement() + " 次");
                        return new Thread(r);
                    }
                },
                1000,
                TimeUnit.MILLISECONDS,
                12);
        TimerTask task1 = new TimerTask() {
            public void run(Timeout timeout) throws Exception {
                System.out.println("执行任务1：" + new Date().toLocaleString());
                System.out.println(Thread.currentThread().getName());
                timer.newTimeout(this, 3000, TimeUnit.MILLISECONDS);
            }
        };
        TimerTask task2 = new TimerTask() {
            public void run(Timeout timeout) throws Exception {
                System.out.println("执行任务2：" + new Date().toLocaleString());
                System.out.println(Thread.currentThread().getName());
                timer.newTimeout(this, 1000, TimeUnit.MILLISECONDS);
                Thread.sleep(4000);//！！！在任务中不能使用休眠，否则影响其他任务执行时机，因为所有任务都是用了同一个线程。

            }
        };
        timer.newTimeout(task1, 3000, TimeUnit.MILLISECONDS);
        timer.newTimeout(task2, 1000, TimeUnit.MILLISECONDS);
    }
}
