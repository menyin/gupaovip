package com.gupaoedu.vip.pattern.singleton.lazy;

public class LazyOne {

    private static LazyOne lazyOne = null;
    private LazyOne(){}
    public static LazyOne getInstance(){
        if (lazyOne == null) {
            lazyOne = new LazyOne();
        }
        return lazyOne;
    }


}
