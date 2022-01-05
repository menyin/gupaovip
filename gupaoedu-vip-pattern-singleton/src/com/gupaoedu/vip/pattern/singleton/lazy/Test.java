package com.gupaoedu.vip.pattern.singleton.lazy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        LazyThree lazyThree0=LazyThree.getInstance();
        Constructor<LazyThree> constructor1 = LazyThree.class.getDeclaredConstructor();
        constructor1.setAccessible(true);
        LazyThree lazyThree1 = constructor1.newInstance();
        System.out.println(lazyThree0==lazyThree1);


    }
}
