package com.gupaoedu.vip.pattern.singleton;

import com.gupaoedu.vip.pattern.singleton.hungry.Hungry;
import com.gupaoedu.vip.pattern.singleton.lazy.LazyOne;
import com.gupaoedu.vip.pattern.singleton.lazy.LazyThree;
import com.gupaoedu.vip.pattern.singleton.lazy.LazyTwo;

import java.util.concurrent.CountDownLatch;

public class TestSynchronized {
    private static int tempCount=0;
    public static void main(String[] args) {
//        int count = 200000000;
        int count = 1;
        long begin = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
//            Hungry instance = Hungry.getInstance();
//            LazyTwo instance = LazyTwo.getInstance();
            LazyThree instance = LazyThree.getInstance();
        }
        long totalTimes = System.currentTimeMillis() - begin;
        System.out.println("总时长："+totalTimes);
    }
}
