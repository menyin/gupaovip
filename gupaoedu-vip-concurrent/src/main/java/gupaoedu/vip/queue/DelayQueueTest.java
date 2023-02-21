package gupaoedu.vip.queue;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2022年12月12日 14:56
 */
public class DelayQueueTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
       /* DelayQueue<DelayEntity> delayQueue = new DelayQueue<>();
        delayQueue.put(new DelayEntity("10", 10l));
        delayQueue.put(new DelayEntity("2", 2l));
        delayQueue.put(new DelayEntity("4", 3l));
        while (true) {
            DelayEntity take = delayQueue.take();
            System.out.println("参数:" + take.getStr() + ";计划执行时间:" + take.showScheduleTime() + ";实际执行时间:" + new Date().toString());
        }*/

        /*Future<String> future = Executors.newSingleThreadExecutor().submit(()->{
            Thread.sleep(2000);
            return new String("test。。。");
        });

        String str = future.get();
        System.out.println(str);*/

        Future<?> submit = Executors.newSingleThreadExecutor().submit(() -> {
            System.out.println(123);
        });
        Object o = submit.get();
        System.out.println("输出");
        System.out.println(o);
    }
}
