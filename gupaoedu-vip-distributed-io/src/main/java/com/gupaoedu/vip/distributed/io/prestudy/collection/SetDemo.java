package com.gupaoedu.vip.distributed.io.prestudy.collection;

import java.util.HashSet;
import java.util.Set;

public class SetDemo {
    public static void main(String[] args) {
        Set<String> sets= new HashSet<String>();
        String str1=new String("小红");
        String str2="小红";
        sets.add("小红");
        sets.add("小红");
        sets.add(str1);
        sets.add(str2);

        System.out.println(str1.hashCode());
        System.out.println(str2.hashCode());

        System.out.println(sets);
        System.out.println(sets.contains(new String("小红")));//为true  因为contains使用的是hashCode()和equals()进行比较
        System.out.println(new String("小红")=="小红");


    }
}
