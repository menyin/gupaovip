package com.gupaoedu.vip.pattern.singleton.prestudy;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {
    public static void main(String[] args) {
        int count = 2;
        final CountDownLatch countDownLatch = new CountDownLatch(count);
        new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("当前" + Thread.currentThread().getName() + "线程正在执行");
                    Thread.sleep(3000);
                    countDownLatch.countDown();
                    System.out.println("当前" + Thread.currentThread().getName() + "线程执行完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("当前" + Thread.currentThread().getName() + "线程正在执行");
                    Thread.sleep(3000);
                    countDownLatch.countDown();
                    System.out.println("当前" + Thread.currentThread().getName() + "线程执行完毕");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        try {
            Thread.sleep(1000);
            System.out.println("主线程在等待子线程执行完毕...");
            countDownLatch.await();
            System.out.println("2个子线程全部执行完，主线程继续执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
