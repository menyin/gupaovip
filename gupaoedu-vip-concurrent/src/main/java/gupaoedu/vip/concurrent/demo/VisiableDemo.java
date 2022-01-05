package gupaoedu.vip.concurrent.demo;

import java.util.concurrent.TimeUnit;

public class VisiableDemo {
    public volatile static boolean flag=true;
    public static void main(String[] args) {
        new Thread(()->{
            while (flag) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("任务运行种...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(4);
            flag=false;
            System.out.println("4s attach...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
