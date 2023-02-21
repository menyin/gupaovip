package gupaoedu.vip.concurrent.demo;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockClass {
    private static Map<String, Object> memoryMap = new HashMap<>();
    private static ReentrantReadWriteLock rwlock=new ReentrantReadWriteLock();
    private static Lock rLock = rwlock.readLock();
    private static Lock wLock = rwlock.writeLock();

    public static  Object getObj(String key) {
        rLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"获得了读锁");
            return memoryMap.get(key);
        }finally {
            rLock.unlock();
            System.out.println(Thread.currentThread().getName()+"释放了读锁");
        }
    }

    public static void setObj(String key, Object obj) {
        wLock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"获得了写锁");
            memoryMap.put(key, obj);
            TimeUnit.SECONDS.sleep(4);
        }catch (Exception e){
            System.out.println("出错"+e.getMessage());
        }
        finally {
            System.out.println(Thread.currentThread().getName()+"释放了写锁");
            wLock.unlock();
        }
    }

}
