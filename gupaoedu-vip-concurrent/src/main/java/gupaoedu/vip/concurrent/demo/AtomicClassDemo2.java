package gupaoedu.vip.concurrent.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class AtomicClassDemo2 {
    /*AtomicXX类有很多，此处仅以AtomicInteger为例*/
    //此demo用以解决原子性问题
    private static AtomicInteger count =new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        /*for (int i = 0; i < 2000; i++) {
            new Thread(()->{
                count.getAndIncrement();
            }).start();
        }
        TimeUnit.SECONDS.sleep(2);
        System.out.println(count);
        Object obj = new Object();*/

        testConsumer(s->{
            System.out.println(s);
        });

    }

    public static void testConsumer(Consumer<String> consumer) {
        consumer.accept("社会主义好");
    }
}
