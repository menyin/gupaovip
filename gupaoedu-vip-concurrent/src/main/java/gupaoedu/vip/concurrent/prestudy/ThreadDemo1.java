package gupaoedu.vip.concurrent.prestudy;

public class ThreadDemo1 {
    public static void main(String[] args) {
        //使用Runnable
        new Thread(new Runnable() {
            public void run() {
                int i = 0;
                while (true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName()+"---"+i);
                        i++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        },"th1").start();
        new Thread(new Runnable() {
            public void run() {
                int i = 0;
                while (true) {
                    try {
                        Thread.sleep(1200);
                        System.out.println(Thread.currentThread().getName()+"---"+i);
                        i++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        },"th2").start();
        new ThreadDemoClass().start();



    }


}
