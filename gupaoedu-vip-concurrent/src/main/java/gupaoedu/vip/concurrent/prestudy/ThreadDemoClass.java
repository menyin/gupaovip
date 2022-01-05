package gupaoedu.vip.concurrent.prestudy;

public class ThreadDemoClass extends Thread{
    @Override
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
}
