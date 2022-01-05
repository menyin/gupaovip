package com.gupaoedu.vip.performace.jvm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class JvmController {


    @RequestMapping("/index")
    public String index() {
        return "/index";
    }

    /**
     * 模拟cpu飙高问题的查找
     *
     * @return
     */
    @RequestMapping("/jvm-cpu")
    @ResponseBody
    public String cpu() {
        boolean flag = true;
        while (flag) {

        }
        return "测试CPU跑满";
    }


    /**
     * 输出GC相关信息
     *
     * @return
     */
    @RequestMapping("/jvm-info")
    @ResponseBody
    public String info() {
        List<GarbageCollectorMXBean> l = ManagementFactory.getGarbageCollectorMXBeans();
        StringBuffer sb = new StringBuffer();
        for (GarbageCollectorMXBean b : l) {
            sb.append(b.getName() + "\n");
        }
        return sb.toString();
    }

    /**
     * 模拟内存飙高问题的查找
     * 当发生Tomcat下，请求的控制器方法中出现堆内存溢出时，此时，当前方法在栈中的信息是不是会发生出栈？
     * 1.通过实际测试，当前线程是没有死亡的，应该是因为Tomcat使用的是线程池
     * 2.当内存溢出后并内存并没有恢复，个人推测应该是bigObj是静态变量，存储在非堆区（jdk1.7-是永久区，jdk1.7+是元空间），不实现垃圾回收
     * 3.#memory方法在内存溢出后应该已经出栈，通过#memory2来验证
     *
     * @return
     */
    private static List<int[]> bigObj = new ArrayList();

    @RequestMapping("/jvm-memory")
    @ResponseBody
    public String memory() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());//为了在jconsole或jvisualvm里找到对应线程，所以打印出来

        for (int i = 0; i < 1000; ++i) {
//            Thread.sleep(200L);
            if (i == 0) {
//                Thread.sleep(500L);
                Thread.sleep(200L);
                System.out.println("start = [" + new Date() + "]");
            } else {
//                Thread.sleep(4000L);
                Thread.sleep(1000L);
            }
            bigObj.add(new int[524288]);
        }
        return "当前请求生成了大量的对象占用内存";
    }

    /**
     * ？？为什么内存飙高后  内存溢出  但是没有回落
     *
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("/jvm-memory2")
    @ResponseBody
    public String memory2() throws InterruptedException {
        List<int[]> bigObj2 = new ArrayList();
        System.out.println(Thread.currentThread().getName());//为了在jconsole或jvisualvm里找到对应线程，所以打印出来
        for (int i = 0; i < 1000; ++i) {
//            Thread.sleep(200L);
            if (i == 0) {
//                Thread.sleep(500L);
                Thread.sleep(200L);
                System.out.println("start = [" + new Date() + "]");
            } else {
//                Thread.sleep(4000L);
                Thread.sleep(1000L);
            }
            bigObj2.add(new int[524288]);
        }
        return "当前请求生成了大量的对象占用内存";
    }

    @RequestMapping("/jvm-thread-life")
    @ResponseBody
    public String threadLife() {
        try {
            System.out.println(Thread.currentThread().getName());//为了在jconsole或jvisualvm里找到对应线程，所以打印出来
            Thread.sleep(120000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //由于在tomcat有线程池，所以即使当前方法出现异常，线程也不会死亡
        return "测试线程存活（在本地测试）";
    }


    /**
     * 用Jmater模拟2000并发请求
     * @return
     */
    @RequestMapping("/jvm-concurrent")
    @ResponseBody
    public String concurrent() {
        try {
            Thread.sleep(200);//模拟业务请求延时
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //由于在tomcat有线程池，所以即使当前方法出现异常，线程也不会死亡
        return "response success！！！";
    }


    @RequestMapping("/jvm-io")
    public String io() throws InterruptedException, IOException {
        while (true) {
            File file = new File("/tmp/iotest/" + System.currentTimeMillis() + ".txt");
            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(file);
                for (int i = 51; i < 10000; i++) {
                    outputStream.write(new byte[256]);
                }
                outputStream.flush();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (outputStream != null) {
                    outputStream.close();
                }
            }

        }
    }
}
