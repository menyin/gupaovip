package gupaoedu.vip.concurrent.demo;

import java.util.concurrent.TimeUnit;

public class Demo1 {
    public static void main(String[] args) {
        new Thread(Demo1::task).start();//lamda 要求jdk1.8+
    }
    public static void task(){
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("任务进行中...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
