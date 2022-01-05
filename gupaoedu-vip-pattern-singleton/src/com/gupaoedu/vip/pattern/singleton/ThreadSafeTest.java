package com.gupaoedu.vip.pattern.singleton;

import com.gupaoedu.vip.pattern.singleton.hungry.Hungry;
import com.gupaoedu.vip.pattern.singleton.lazy.LazyOne;
import com.gupaoedu.vip.pattern.singleton.lazy.LazyThree;
import com.gupaoedu.vip.pattern.singleton.lazy.LazyTwo;
import com.gupaoedu.vip.pattern.singleton.register.RegisterMap;
import com.gupaoedu.vip.pattern.singleton.seriable.Seriable;

import java.util.concurrent.CountDownLatch;

public class ThreadSafeTest   {
    /**
     * 测试线程安全问题
     * 模拟多线程并发，只要得到的实例都是同一个对象则表示线程安全
     * @param args
     */
    public static void main(String[] args) {
        int count = 200;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
//                        Hungry instance = Hungry.getInstance();//饿汉模式，一直是一个实例对象
//                        LazyOne instance = LazyOne.getInstance();//懒汉模式一，出现了两个实例对象
//                        LazyTwo instance = LazyTwo.getInstance();//懒汉模式二，一直是一个实例对象，但性能差
//                        LazyThree instance = LazyThree.getInstance();//懒汉模式二，一直是一个实例对象，但性能好
//                        RegisterMap instance = RegisterMap.getInstance();//注册模式
                        Seriable instance = Seriable.getInstance();//序列化模式
                        long currTimes = System.currentTimeMillis();
                    System.out.println("时间："+currTimes+"，实例为："+instance);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            countDownLatch.countDown();
        }
        System.out.println("所有线程全部启动完毕，准备全部并发");
    }
}
