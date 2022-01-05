package gupaoedu.vip.concurrent.demo;

public class LockDemo4 {
    public static void main(String[] args) throws InterruptedException {
        synchronized (LockDemo4.class) {
            Thread.sleep(1000);
            synchronized (LockDemo4.class) {
                System.out.println("社会主义好");
            }
        }
    }
}
