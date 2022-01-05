package gupaoedu.vip.concurrent.demo;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CompletableFuture是jdk1.8之后线程池的API，它让线程池执行任务更加方便
 * Executors是jdk1.5就有的API
 */

public class CompletableFutureDemo1 {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
       //采用默认的线程池ForkJoinPool.commonPool()
        /*
        //无返回值
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                System.out.println("任务线程执行开始...");
                Thread.sleep(3000);
                System.out.println("任务线程执行完毕...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Void aVoid = future.get();
        System.out.println(aVoid);
        System.out.println("主线程...");
        System.in.read();*/

        //有返回值
        /*CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("任务线程执行开始...");
                Thread.sleep(3000);
                System.out.println("任务线程执行完毕...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "社会主义好";
        });
        String s = future.get();
        System.out.println(s);
        System.out.println("主线程...");
        System.in.read();*/

        /*//发现两个任务使用的线程居然是同一个线程
        CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务1线程名："+Thread.currentThread().getName());
            int i = 1 / 0;
            return "任务执行结果";
        }).whenCompleteAsync((s,e)->{
            System.out.println("任务2线程名："+Thread.currentThread().getName());
            System.out.println(e);
            System.out.println(s);//如果异常了，这个s为null
        });
        System.in.read();
*/
        //使用了不同的线程池，结果两个任务使用了不同的线程
       /* ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务1线程名："+Thread.currentThread().getName());
            return "任务执行结果";
        },executorService).whenCompleteAsync((s,e)->{
            System.out.println("任务2线程名："+Thread.currentThread().getName());
            System.out.println(e);
            System.out.println(s);//如果异常了，这个s为null
        });
        System.in.read();*/

        //使用了同一个线程池，结果两个任务使用了相同的线程
        /*ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务1线程名："+Thread.currentThread().getName());
            return "任务执行结果";
        },executorService).whenCompleteAsync((s,e)->{
            System.out.println("任务2线程名："+Thread.currentThread().getName());
            System.out.println(e);
            System.out.println(s);//如果异常了，这个s为null
        },executorService);
        System.in.read();*/

        //whenComplete和whenCompleteAsync区别在于whenComplete用的线程是上一个任务的线程，而whenCompleteAsync不一定
        /*ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务1线程名："+Thread.currentThread().getName());
            return "任务执行结果";
        },executorService).whenComplete((s,e)->{
            System.out.println("任务2线程名："+Thread.currentThread().getName());
            System.out.println(e);
            System.out.println(s);//如果异常了，这个s为null
        });
*/
        System.in.read();


    }
}
