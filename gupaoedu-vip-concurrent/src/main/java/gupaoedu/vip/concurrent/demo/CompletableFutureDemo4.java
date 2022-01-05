package gupaoedu.vip.concurrent.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        final CompletableFuture<Object> future = new CompletableFuture<>();
        System.out.println(future.isDone());

        future.runAsync(() -> {
            try {
                Object result = future.get(3000, TimeUnit.MILLISECONDS);//cny_note 在超时时间内如果没返回就抛异常
                System.out.println("执行逻辑,结果："+result);
            } catch (Exception e) {
                future.completeExceptionally(new Exception("出错了"));
            }
            System.out.println("任务执行完毕");
        });
        Thread.sleep(1000);
        future.complete(11);
        System.out.println(future.isDone());


    }

}
