package gupaoedu.vip.concurrent.liondemo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class FlowControl2 {
    /**
     * JUC的Semaphore交通灯 实现限流（qps）
     * 超过限流值的请求则会被阻塞在semaphore.acquire();
     * ？？有空可以在springmvc的拦截器前-后里做下交通灯流量整形实践
     * @param args
     */
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
                        TimeUnit.SECONDS.sleep(30);
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
