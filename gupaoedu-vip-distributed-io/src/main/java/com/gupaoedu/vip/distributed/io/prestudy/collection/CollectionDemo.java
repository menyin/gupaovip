package com.gupaoedu.vip.distributed.io.prestudy.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class CollectionDemo {
    public static void main(String[] args) {
        Collection<String> collection = new ArrayList<>();
        collection.add("小明");
        collection.add("小红");
        collection.hashCode();

        //contains内部使用的是equals进行比较,而不是==
        System.out.println(collection.contains("小明"));
        System.out.println(collection.contains(new String("小明")));

        System.out.println("小明"==new String("小明"));
        System.out.println("小明".equals(new String("小明")));

        System.out.println(collection.isEmpty());

        Iterator<String> iterator = collection.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }

        String[] array = new String[10];
        String[] strings = collection.toArray(array);
        System.out.println(strings==array);

        collection.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        collection.remove("小明");


        collection.removeIf(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.equals("小红");
            }
        });

        collection.clear();
        System.out.println(collection);

    }
}
