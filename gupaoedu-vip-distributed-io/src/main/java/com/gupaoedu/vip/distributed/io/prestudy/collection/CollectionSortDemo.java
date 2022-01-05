package com.gupaoedu.vip.distributed.io.prestudy.collection;

import java.util.ArrayList;
import java.util.Comparator;

public class CollectionSortDemo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("bbb");
        list.add("aaa");
        list.add("ccc");
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //返回小于0则升序，反之降序
                return o1.length()-o2.length();
            }
        });
        System.out.println(list);
    }
}
