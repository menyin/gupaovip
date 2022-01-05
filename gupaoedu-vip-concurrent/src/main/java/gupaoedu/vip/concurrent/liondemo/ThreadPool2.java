package gupaoedu.vip.concurrent.liondemo;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool2 {
    /**
     * ScheduledThreadPoolExecutor继承了ThreadPoolExecutor，并另外提供一些调度方法以支持定时和周期任务。
     * @param args
     */
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
        for (int i = 0; i < 20; i++) {
            if(i%2==0){
                System.out.println(executor.getQueue().size()); //打印出现在线程池里接收到的任务个数
            }
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                        //此输出可以看出只有 corePoolSize=2 个线程在跑这些任务
                        System.out.println("执行任务"+System.currentTimeMillis()+":"+Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        }

    }
}
