package gupaoedu.vip.concurrent.demo;

public class DaemonThreadDemo {
    public static void main(String[] args) {
        Thread main = new Thread(() -> {
            new Thread(()->{
                System.out.println("1线程名称为："+Thread.currentThread().getName());
            }).start();
            new Thread(()->{
                System.out.println("2线程名称为："+Thread.currentThread().getName());
            }).start();

        });
        main.setDaemon(true);//让main线程作为守护线程，注意必须在start()之前执行setDaemon
        main.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
