package gupaoedu.vip.concurrent.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo3 {

    public static class StoreServic{
        /**
         * 伪仓储服务 begin
         */
        private int store1=200;//仓库1
        private int store2=0;//仓库2
        public int getStore1() {
            return store1;
        }
        public int getStore2() {
            return store2;
        }
        public void decrStore1(){
            store1--;
        }
        public void incrStore2(){
            store2++;
        }
    }

    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static StoreServic storeServic =new StoreServic();//伪仓储服务
    public static void main(String[] args) throws InterruptedException {

        /**开启201个线程去执行业务逻辑**/
        for (int i = 1; i <=201 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    try {
                        int store1=storeServic.getStore1();
                        int store2=storeServic.getStore2();
                        storeServic.decrStore1();//仓库1搬出1件
                        if(Thread.currentThread().getName().equals("t1")){//当前线程满足某种业务条件导致处理时间会比较长，所以先await()
                            System.out.println(Thread.currentThread().getName()+" 执行await()...");
                            condition.await();
                        }
                        if(store2<200){
                            storeServic.incrStore2();//仓库2搬进1件
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.unlock();
                }
            },"t"+i).start();
        }
         /**开启1个线程去唤醒t1线程**/
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    try {
                        Thread.sleep(1000);
                        condition.signal();
                    } catch (Exception e) {
                    }
                    lock.unlock();

                }
            }, "t-signal").start();
        /**执行完获取结果**/
        Thread.sleep(3000);
        System.out.println("store1:"+storeServic.getStore1());
        System.out.println("store2:"+storeServic.getStore2());
    }
}
