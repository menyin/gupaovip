package gupaoedu.vip.java8demo;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsDemo {
    /**
     * stream()使得集合可以像jq那样进行链式编程。
     * 可以实现 筛选、切片、排序、查找、匹配等功能
     * 必须注意在这中间的操作后调用宿主是否变化，如mapToInt就已经将宿主变成了IntStream
     * @param args
     */
    public static void main(String[] args) throws IOException {
         List<Task> tasks = Arrays.asList(
                new Task(Status.OPEN, 10),
                new Task(Status.OPEN, 5),
                 new Task(Status.OPEN, 10),
                 new Task(Status.OPEN, 5),
                 new Task(Status.CLOSE, 8)
        );
        //基本使用
       /*int sum = tasks.stream()
                .filter(t -> t.status == Status.OPEN )//？此处居然可以使用t的私有字段status，是否破坏了private语义。！其实是.status相当于调用getStatus方法
                .mapToInt(t -> t.points) //
                .sum();
        System.out.println(sum);*/

       //并行操作功能 原理详见blog《Java8 Stream 并行计算实现的原理》
       /* tasks.stream().parallel().mapToInt(t->{ //注意这里的lamda只有在sum()方法执行时才会被调用
            System.out.println(Thread.currentThread().getName()); //输出了不同的线程名称
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return t.getPoints();
        }).sum();*/
        Arrays.stream(new int[]{});

        //boxed装箱
        /*int[] ints = new int[]{1,2,3};
        List<Integer> collect = Arrays.stream(ints).boxed().collect(Collectors.toList());*/

        //规约
        /*List<Integer> ints = Arrays.asList(1, 2, 3, 4);
        Integer reduce = ints.stream().reduce(0, (x, y) ->x + y);
        System.out.println(reduce);*/

        //收集,通常collect(Collectors.toCollection(...))配合使用
        /*List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        List<String> list = integers.stream().map(i -> i + "个人").collect(Collectors.toList());
        //integers.stream().map(i -> i + "个人").collect(Collectors.toConcurrentMap(c->c,c->c+":value"));
        //要收集到任意的集合类型，如HashSet*/
        /*HashSet<Integer> hashSet = integers.stream().collect(Collectors.toCollection(HashSet::new));*/
//        System.out.println(list);

        //无限流,相当于一个无限的迭代器，可以通过limit截断
        //List<Integer> collect = Stream.iterate(0, x -> x + 1).limit(10).collect(Collectors.toList());
        //System.out.println(collect);

        //截断
        //List<Task> collect1 = tasks.stream().limit(2).collect(Collectors.toList());
        //System.out.println(collect1);

        //System.in.read();

        tasks.stream().allMatch(task -> task.points % 5 == 0);

    }

    public static class Task {
        private Status status;
        private Integer points;

        public Task(Status status, Integer points) {
            this.status = status;
            this.points = points;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public Integer getPoints() {
            return points;
        }

        public void setPoints(Integer points) {
            this.points = points;
        }
    }

    private enum Status {
        OPEN, CLOSE
    }
}
