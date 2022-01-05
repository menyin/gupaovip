package gupaoedu.vip.concurrent.demo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo1 {
    public static void main(String[] args) {
        //需求：停车场共有10个车位，现在有20辆车要停放
        Semaphore semaphore = new Semaphore(10);
        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() + "抢到车位..." );
                        TimeUnit.SECONDS.sleep(5);
                        System.out.println(Thread.currentThread().getName() + "离开停车场..." );
                        semaphore.release();
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + "出现异常" + e.getMessage());
                    }
                }
            },"Car-"+i).start();
        }
    }
}
