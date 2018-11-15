package com.gupaoedu.vip.pattern.singleton.lazy;

public class LazyTwo {
    private static LazyTwo lazyTwo=null;
    private LazyTwo(){}
    public synchronized static LazyTwo  getInstance(){
        if (lazyTwo==null) {
            lazyTwo = new LazyTwo();
        }
        return lazyTwo;
    }
}
