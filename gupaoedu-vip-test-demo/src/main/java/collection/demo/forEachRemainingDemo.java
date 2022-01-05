package collection.demo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class forEachRemainingDemo {
    public static void main(String[] args) {
       /* //forEachRemaining表示执行过的不会重复执行
        List<String> list = Arrays.asList("小明", "小黄", "小可", "小王", "小李", "小张");
        Iterator<String> iterator = list.iterator();
        try {
            iterator.forEachRemaining(str -> {
                System.out.println(str);
                if ("小可".equals(str)) {
                    throw new RuntimeException("到小可出问题了....");
                }
            });
        } catch (Exception e) {
            System.out.println("+++++++iterator没有遍历完++++++++");
        }
        iterator.forEachRemaining(str -> {
            System.out.println(str);
        });*/


    }

}
