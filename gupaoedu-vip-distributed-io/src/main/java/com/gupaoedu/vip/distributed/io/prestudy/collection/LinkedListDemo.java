package com.gupaoedu.vip.distributed.io.prestudy.collection;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class LinkedListDemo {
    public static void main(String[] args) {
        //以下均为LinkedList特有方法
        LinkedList<String> strings = new LinkedList<>();
        /*strings.addFirst("小A");
        strings.addFirst("小B");
        strings.addFirst("小C");

        System.out.println(strings.getFirst());
        System.out.println(strings.getLast());

        System.out.println(strings.removeFirst());
        System.out.println(strings.removeLast());

        strings.push("小X");
        strings.offer("小Y");*/

        //尾部添加元素
        /*strings.offer("小1");
        strings.offer("小2");
        strings.offer("小3");
        strings.offer("小3");*/
       /* //头部去掉元素
        strings.poll();
        //头部添加元素
        strings.push("a");
        strings.push("b");
        strings.push("c");*/
        //头部去掉元素
        String poll=strings.poll();
//        strings.pop();//和remove的区别是  后者在没有元素的情况下返回null而前者会抛异常。
        System.out.println(strings.poll());
        System.out.println(strings.poll());
        System.out.println(strings.poll());
        Iterator<String> iterator = strings.iterator();
        System.out.println(strings);

        //ListIterator是list类集合的迭代器专有的，他比一般的Iterator多了add、set、hasPrevious、previous方法
        ListIterator<String> iterator1 = (ListIterator)strings.iterator();
        ListIterator<String> stringListIterator = strings.listIterator();
        iterator1.add("社会主义好");
        while (iterator1.hasPrevious()) {
            String previous = iterator1.previous();
            System.out.println(previous);
        }



    }
}
