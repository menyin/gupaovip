package gupaoedu.vip.concurrent.liondemo;

import java.util.Date;
import java.util.concurrent.*;

public class ThreadPool1 {
    private static int count=0;
    /**
     * Executors.newSingleThreadScheduledExecutor()单线程任务器的使用
     * @param args
     */
    public static void main(String[] args) {
        //单线程带延迟定时器  initialDelay是首次延迟时间，period是定时间隔
        //注意scheduleAtFixedRate 后一个任务要等前一个任务完成才能执行，实际任务延迟时间=任务执行时间和period两者的最大值
        /*System.out.println(new Date().toLocaleString());//记录初始时间
        Executors.newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(6000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(new Date().toLocaleString());
                    }
                },10,5, TimeUnit.SECONDS);*/

        //单线程带延迟定时器  initialDelay是首次延迟时间，delay是延迟时间
        //scheduleWithFixedDelay是每次执行完任务后再延迟delay，所以实际延迟时间=任务执行时间+delay
        /*System.out.println(new Date().toLocaleString());//记录初始时间
        Executors.newSingleThreadScheduledExecutor()
                .scheduleWithFixedDelay(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(6000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(new Date().toLocaleString());
                    }
                },10,5, TimeUnit.SECONDS);*/

        //可以指向Callable任务或Runable任务
        /*System.out.println(new Date().toLocaleString());//记录初始时间
        ScheduledFuture<String> future = Executors.newSingleThreadScheduledExecutor().schedule(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "社会主义好";
            }
        }, 5, TimeUnit.SECONDS);
        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/

        //抛出运行时异常时不会像Timer那样直接线程终止 ？这个demo没有测试成功
        /*ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService .scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if(count==1){
                    int b=1/0;
                }
                count++;
                System.out.println(11111111);
            }
        },2,2,TimeUnit.SECONDS);*/


    }
}
