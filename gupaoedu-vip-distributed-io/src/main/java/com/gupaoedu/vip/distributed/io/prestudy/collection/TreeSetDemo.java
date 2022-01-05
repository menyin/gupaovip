package com.gupaoedu.vip.distributed.io.prestudy.collection;

import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetDemo {
    public static void main(String[] args) {
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("b");
        treeSet.add("c");
        treeSet.add("a");
        treeSet.add("b");
        treeSet.add("g");
        System.out.println(treeSet);
        Iterator<String> stringIterator = treeSet.descendingIterator();
        while (stringIterator.hasNext()) {
            System.out.println(stringIterator.next());
        }
    }
}
