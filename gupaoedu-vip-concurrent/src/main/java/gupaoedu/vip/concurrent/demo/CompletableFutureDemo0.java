package gupaoedu.vip.concurrent.demo;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2022年11月23日 10:32
 */
public class CompletableFutureDemo0 {
    public static void main(String[] args) {

        /***
         *   CompletableFuture一些方法的比较：
         *       -总结：CompletableFuture用线程池及阻塞等技术，将一些任务体串联或并联起来，形成链式编程。
         *       -基础方法：runAsync、supplyAsync 两个方法分别代表运行无返回值和有返回值的内容（注意：这两个方法是静态方法，其它方法都是实例方法，任务通常都是从这两个方法开始）
         *       -Async结尾：通常代表当前任务体和上一个任务体执行是不同的线程池。因此这类方法通常会有Executor类型的参数。
         *       -when开头的方法：只有whenComplete方法带when
         *       -then开头的方法：
         *       -带accept的方法：只接收上一个任务结果，执行当前任务后并不返回值，即只消费
         *       -带apply的方法：和带accept的方法相比就是既接收上一个任务结果，执行完当前任务又会返回值，即又消费又返回。
         *       -带run的方法：不接收上一个任务结果，也不返回当前任务执行结果；
         *       -带Either的方法：Either意思是“或”，也就是两个任务并行取其中最快的结果，并执行当前任务。
         */

        /*==============runAsync或supplyAsync是CompletableFuture的静态方法，任务都是从这两个方法开始的==============*/
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("runAsync是静态方法，不会返回结果值");
        });

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            return "supplyAsync是静态方法，会返回结果值";
        });
        /*======================后续的方法都是在runAsync或supplyAsync执行完后执行=================================*/
        stringCompletableFuture.whenComplete((result,ex)->{
            System.out.println("whenComplete只接收上个任务返回值，自己不返回结果值。另外会接收上个任务的异常");
        });


        stringCompletableFuture.thenAccept(result->{
            System.out.println("thenAccept只接收上个任务返回值，自己不返回结果值");
        });
        stringCompletableFuture.thenRun(()->{
            System.out.println("thenRun不会接收上个任务返回值，自己不返回结果值");
        });

        stringCompletableFuture.thenApply(result->{
            return    "thenApply只接收上个任务返回值，自己还会返回结果值";
        });
        stringCompletableFuture.handle((result,ex)->{
            return    "handle只接收上个任务返回值，自己还会返回结果值,另外会接收上个任务的异常（注意和whenComplete的区别）";
        });



        CompletableFuture<String> taskFuture1 = CompletableFuture.supplyAsync(() -> {
            return "result1";
        });
        CompletableFuture<String> taskFuture2 = CompletableFuture.supplyAsync(() -> {
            return "result2";
        });
        taskFuture1.thenCombine(taskFuture2,(result1,result2)->{
            System.out.println("thenCombine会将两个任务合并，并接收两个任务结果1结果2，最后返回最终结果3。？两个任务是并行执行还是串行执行？！见下一行");
            System.out.println("-不以Async结尾：如果上一个任务执行完毕返回，则当前任务用上一个任务的线程执行，否则当前任务用和上个任务线程池的线程执行");
            System.out.println("thenAcceptBoth和thenCombine区别在于前者并不用返回最终结果3");
            System.out.println("applyToEither和thenCombine区别在于前者接收到的结果是两个任务中最快的任务返回的任务结果");
            return "result3";
        });
        taskFuture1.applyToEither(taskFuture2,resutl->{
            System.out.println("applyToEither会将两个任务并行执行，并接收两个任务最快返回的");
            return resutl;
        });

    }
}
