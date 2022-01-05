package com.gupaoedu.vip.distributed.io.prestudy.collection;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CollectionsDemo {
    public static void main(String[] args) {
        //Collections是一个工具类
        Set<String> set= new HashSet<>();
        Collections.synchronizedSet(set);//内部采用装饰器模式对set进行包装，每个方法都添加了synchronized进行同步。synchronizedMap、synchronizedList同理

    }
}
