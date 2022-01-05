package gupaoedu.vip.concurrent.demo;

public class WaitDemo {
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程开始");
                synchronized (WaitDemo.class) {
                    System.out.println("子线程进入锁");
                    try {
                        WaitDemo.class.wait(500);
                        System.out.println("子线程被唤醒");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("子线程退出锁");
            }
        }).start();
        System.out.println("主线程1");
        Thread.sleep(1000);

        synchronized (WaitDemo.class) {
            System.out.println("主线程进入锁");
            WaitDemo.class.notify();
            System.out.println("主线程唤醒子线程");
        }
        System.out.println("主线程退出锁");
            Thread.sleep(10000000);

      /*  主线程1
                主线程进入锁
        主线程唤醒子线程
                主线程退出锁
        子线程开始
                子线程进入锁*/
    }


    /**
     * 测试wait()方法是释放了其对应的synchronized的锁对象，别的线程到了wait()方法对应的synchronized的同步代码块，还是能进入执行的。
     * 而Thread.sleep()是不会释放当前的锁，也就是别的线程到了Thread.sleep()不能往下执行
     * @param args
     * @throws InterruptedException
     */
    /*public static void main(String[] args) throws InterruptedException {
        final Object obj = new Object();
        new Thread(()->{
                show(obj);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        },"t1").start();
        System.out.println("执行t1线程");
        Thread.sleep(1000);
        new Thread(()->{
            while (true) {
                show(obj);
            }
        },"t2").start();
        System.out.println("执行t2线程");
    }
    public static void show(Object obj){
        synchronized (obj){
            try {
                System.out.println(Thread.currentThread().getName());
                if (Thread.currentThread().getName().equals("t1")) {
                    obj.wait();
                    System.out.println("t1过了wait");
                    Thread.sleep(11000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/
}
