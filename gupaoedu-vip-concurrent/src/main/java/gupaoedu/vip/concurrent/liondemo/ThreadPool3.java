package gupaoedu.vip.concurrent.liondemo;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPool3 {
    /**
     * new HashedWheelTimer() 时间轮定时器的使用
     * 个人认为就是可以根据自己需求，定义时间精度等参数，以达到优化执行效率的效果
     * @param args
     */
    public static void main(String[] args) {
//        new HashedWheelTimer(new NamedThreadFactory(ThreadNames.T_CONN_TIMER),tickDuration, TimeUnit.MILLISECONDS, ticksPerWheel);
        //基本使用
        final HashedWheelTimer timer = new HashedWheelTimer(
                Executors.defaultThreadFactory(), //使用Executors的默认线程工厂，也可以自定义工厂
                1, //注意这个时间单位其实是算“最小精度”
                TimeUnit.MILLISECONDS, //“最小精度”的单位
                60); //时间钟的周期 ？？这个的作用
        TimerTask task = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("执行任务1："+new Date().toLocaleString());
                timer.newTimeout(this, 2000, TimeUnit.MILLISECONDS);
            }
        };
        timer.newTimeout(task, 2000, TimeUnit.MILLISECONDS
        );



       /* HashedWheelTimer timer = new HashedWheelTimer(
                new ThreadFactory() {
                    private AtomicInteger count = new AtomicInteger(1);

                    @Override
                    public Thread newThread(Runnable r) {
                        System.out.println("线程工厂被调用 " + count.getAndIncrement() + " 次");
                        return new Thread(r);
                    }
                },
                1000,
                TimeUnit.MILLISECONDS,
                12);
        TimerTask task1 = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("执行任务1：" + new Date().toLocaleString());
                System.out.println(Thread.currentThread().getName());
                timer.newTimeout(this, 3000, TimeUnit.MILLISECONDS);
            }
        };
        TimerTask task2 = new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println("执行任务2：" + new Date().toLocaleString());
                System.out.println(Thread.currentThread().getName());
                timer.newTimeout(this, 1000, TimeUnit.MILLISECONDS);
                Thread.sleep(4000);//！！！在任务中不能使用休眠，否则影响其他任务执行时机，因为所有任务都是用了同一个线程。

            }
        };
        timer.newTimeout(task1, 3000, TimeUnit.MILLISECONDS);
        timer.newTimeout(task2, 1000, TimeUnit.MILLISECONDS);*/

    }
}
