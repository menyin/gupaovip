package gupaoedu.vip.concurrent.demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultThreadTimes {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //单线程下的任务执行效率
        /*long start = System.currentTimeMillis();
        task("task1");
        task("task2");
        task("task3");
        System.out.println(System.currentTimeMillis()-start);*/

        //多线程下的任务执行效率
        /*long start = System.currentTimeMillis();
        Thread task1 = new Thread(() -> {
            try {
                task("task1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread task2 = new Thread(() -> {
            try {
                task("task2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread task3 = new Thread(() -> {
            try {
                task("task3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        task1.start();
        task2.start();
        task3.start();
        task1.join();
        task2.join();
        task3.join();
        System.out.println(System.currentTimeMillis()-start);*/

        //多线程下无关联的多任务执行效率一般取决于执行时间最长的一个
        /*ExecutorService executorService = Executors.newCachedThreadPool();
        long start = System.currentTimeMillis();
        Future<?> task1= executorService.submit(() -> {
            try {
                task1("task1",1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Future<?> task2= executorService.submit(() -> {
            try {
                task1("task2",2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Future<?> task3 = executorService.submit(() -> {
            try {
                task1("task3",3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Object o3 = task3.get();
        Object o1 = task1.get();
        Object o2 = task2.get();
        System.out.println(System.currentTimeMillis()-start);*/

    }
    public static void task(String name) throws InterruptedException {
        Thread.sleep(2000);
        System.out.println(name+"任务执行完毕");
    }
    public static void task1(String name,long times) throws InterruptedException {
        Thread.sleep(times);
        System.out.println(name+"任务执行完毕");
    }
}
