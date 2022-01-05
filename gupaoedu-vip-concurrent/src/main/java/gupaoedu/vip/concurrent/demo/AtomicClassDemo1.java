package gupaoedu.vip.concurrent.demo;

import java.util.concurrent.TimeUnit;

public class AtomicClassDemo1 {
    private static volatile int count;
    public static void main(String[] args) throws InterruptedException {
        //发现volatile解决不了count++;原子性问题，看AtomicClassDemo2
        for (int i = 0; i < 2000; i++) {
            new Thread(()->{
                count++;
            }).start();
        }
        TimeUnit.SECONDS.sleep(2);
        System.out.println(count);
    }
}
