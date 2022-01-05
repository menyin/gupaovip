package gupaoedu.vip.concurrent.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class AtomicDemo {
    private static int count=0;
    private static CountDownLatch countDownLatch=new CountDownLatch(1000);
    public static void addCount() {
        try {
            countDownLatch.await();
            TimeUnit.SECONDS.sleep(1);
            count++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        //原子性
        for (int i = 0; i < 1000; i++) {
            new Thread(AtomicDemo::addCount).start();
            countDownLatch.countDown();
        }
        try {
            TimeUnit.SECONDS.sleep(6);
            System.out.println(count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
