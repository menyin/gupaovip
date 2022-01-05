package gupaoedu.vip.concurrent.prestudy;

import java.util.concurrent.*;

public class ThreadDemo3 {
    public static void main(String[] args) {
        ExecutorService ex = Executors.newCachedThreadPool();
        /*ex.execute(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println("嗨起来");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });*/
        Future<String> future = ex.submit(new Callable<String>() {
            public String call() throws Exception {
                int i = 0;
                while (true) {
                    if (i == 999999) {

                        break;
                    }
//                    System.out.println("...."+i);
                    i++;
                }
                return "社会主义好";
            }
        });

        try {

            String result = future.get();//future.get()会一直阻塞知道异步得到结果
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
