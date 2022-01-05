package gupaoedu.vip.concurrent.demo;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureDemo2 {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        //其实是通过线程池来实现异步化，以达到任务的串行化和promise的链式编程的目的
        /*CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1的线程："+Thread.currentThread().getName());
            return "结果值1";
        }).thenApplyAsync((s)->{
            System.out.println("任务2的线程："+Thread.currentThread().getName());
            return s+"结果值2";
        }).thenApplyAsync((s)->{
            System.out.println("任务3的线程："+Thread.currentThread().getName());
            int i=1/0;
            return s+"结果值3";
        }).whenCompleteAsync((s,e)->{
            System.out.println(s);
            System.out.println(e);
            System.out.println("最终任务的线程："+Thread.currentThread().getName());
        });
        Thread.sleep(1000);*/

        /*//也可以用默认线程池以外的线程池去执行任务
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1的线程："+Thread.currentThread().getName());
            return "结果值1";
        }).thenApplyAsync((s)->{
            System.out.println("任务2的线程："+Thread.currentThread().getName());
            return s+"结果值2";
        },executorService).thenApplyAsync((s)->{
            System.out.println("任务3的线程："+Thread.currentThread().getName());
            return s+"结果值3";
        }).whenCompleteAsync((s,e)->{
            System.out.println(s);
            System.out.println("最终任务的线程："+Thread.currentThread().getName());
        });
        Thread.sleep(1000);*/

       /* CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1的线程："+Thread.currentThread().getName());
            return "结果值1";
        }).handleAsync((s,e)->{
            System.out.println("任务2的异常："+e);
            System.out.println("任务2的线程："+Thread.currentThread().getName());
            int i=1/0;
            return s+"结果值2";
        }).handleAsync((s,e)->{
            System.out.println("任务3的异常："+e);
            System.out.println("任务3的线程："+Thread.currentThread().getName());
            return s+"结果值3";
        }).whenCompleteAsync((s,e)->{
            System.out.println(s);
            System.out.println("最终任务的线程："+Thread.currentThread().getName());
        });
        Thread.sleep(1000);*/

            System.out.println(null instanceof String);

    }
}
