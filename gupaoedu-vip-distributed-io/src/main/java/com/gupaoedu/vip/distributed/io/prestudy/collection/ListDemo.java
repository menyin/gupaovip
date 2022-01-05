package com.gupaoedu.vip.distributed.io.prestudy.collection;

import java.util.ArrayList;
import java.util.List;

public class ListDemo {
    public static void main(String[] args) {
        //以下均为list接口特有方法
        List<String> list = new ArrayList<>();
        list.add(0, "小明");
        list.add(0, "小红");
        list.add(0, "小张");
        list.add(0, "小黄");
        list.add(1, "小李");
        System.out.println(list);

        list.remove(1);

        list.set(0, "小刚");

        System.out.println(list.get(0));

        List<String> strings = list.subList(1, 3);
        System.out.println(strings);
        System.out.println(list);



    }
}
