package gupaoedu.vip.concurrent.demo;

import java.util.concurrent.TimeUnit;

/**
 * 前提：当一个线程处于阻塞状态下（例如休眠）的情况下，调用了该线程的interrupt()方法就会抛出异常InterruptedException
 * 预期：在抛出InterruptedException之后，thread.isInterrupted()标志位有重置为false了
 */
public class InterruptDemo {
    public static void main(String[] args) throws
            InterruptedException{
        Thread thread=new Thread(()->{
            while(true){
                try {
                    Thread.sleep(10000);

                } catch (InterruptedException e) {
                //抛出该异常，会将复位标识设置为 false
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();//设置复位标识为 true
        TimeUnit.SECONDS.sleep(1);
        System.out.println(thread.isInterrupted());//false
    }
}
