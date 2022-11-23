package gupaoedu.vip.concurrent.demo;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2022年11月18日 14:54
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 同步代码块可以指定锁其它对象，不会锁住方法所在的对象，从而避免非同步的方法也被阻塞了
 */
public class SynchronizedTest {
    public static SynchronizedTest obj;
    public synchronized void testSyncFuc(){
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public  void testSyncBlock(){

        try {
            synchronized(SynchronizedTest.class){
                Thread.sleep(1000000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized void test(){
        System.out.println("执行了test方法");
    }

    public static void main(String[] args) {
//        test1();
        test2();

    }

    public static void test1(){
        obj = new SynchronizedTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.testSyncFuc();
            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.test();
            }
        }).start();
    }
    public static void test2(){
        obj = new SynchronizedTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.testSyncBlock();
            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.test();
            }
        }).start();

    }

    public class Person{
        private String name;

        Person(String name) {
            this.name = name;
        }
        Person(String name,String nick) {
            this.name = name+nick;
        }
    }

    class Student extends Person {

        Student(String name) {
            super(name);
        }

    }
}
