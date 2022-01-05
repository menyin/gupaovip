package gupaoedu.vip.concurrent.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureDemo3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

       /*
       //CompletableFuture#join是阻塞主线程以让子任务执行完毕
       CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                System.out.println("子任务进入...");
                Thread.sleep(3000);
                System.out.println("子任务退出...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        future.join();//会阻塞等待子任务线程执行完
        System.out.println("主线程....");*/

        /*//CompletableFuture#get是阻塞主线程以让子任务执行完毕并拿到结果，而且可以设置超时时间
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                System.out.println("子任务进入...");
                Thread.sleep(3000);
                System.out.println("子任务退出...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        try {
            future.get(1000, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            System.out.println("抛异常了："+e);
        }
        System.out.println("主线程....");*/

    }
}
