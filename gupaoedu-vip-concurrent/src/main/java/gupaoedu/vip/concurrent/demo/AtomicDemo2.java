package gupaoedu.vip.concurrent.demo;

public class AtomicDemo2 {
    volatile int i;
public void inc() {
    i++;
}
    public static void main(String[] args) {
        new AtomicDemo2().inc();
    }
}
