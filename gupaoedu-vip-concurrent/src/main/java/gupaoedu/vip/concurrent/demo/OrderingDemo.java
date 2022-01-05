package gupaoedu.vip.concurrent.demo;

import java.util.concurrent.TimeUnit;

public class OrderingDemo {
    public static int x=0,y=0;
    public static int a=0,b=0;

    public static void main(String[] args) {
        new Thread(()->{
            a=1;
            x=b;
        }).start();
        new Thread(()->{
            b=1;
            y=a;
        }).start();
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("a="+a);
            System.out.println("b="+b);
            System.out.println("x="+x);
            System.out.println("y="+y);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
