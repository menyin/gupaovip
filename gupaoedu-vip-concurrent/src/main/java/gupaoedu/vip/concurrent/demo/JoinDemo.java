package gupaoedu.vip.concurrent.demo;

public class JoinDemo {
    public static void main(String[] args) {
        System.out.println("主线程1");
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程开始");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程结束");
            }
        });

        t.start();
        System.out.println("主线程2");
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程3");

    }
}
