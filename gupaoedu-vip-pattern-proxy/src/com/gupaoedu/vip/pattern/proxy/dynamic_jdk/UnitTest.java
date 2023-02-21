package com.gupaoedu.vip.pattern.proxy.dynamic_jdk;

import com.gupaoedu.vip.pattern.proxy.dynamic_jdk.Son;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class UnitTest {
    public static void main(String[] args) throws IOException {

        Person son = new Son();//如果代理对象未实现任何接口则会报错
        Person sonProxy = (Person)ProxyFactory.getProxyInstance(son);
        sonProxy.findLove();

        //通过反编译工具查看源代码
//        Class<? extends Person> aClass = sonProxy.getClass();
        byte[] $Proxy0s = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Person.class});
        FileOutputStream fileOutputStream = new FileOutputStream("E://$Proxy0.class");
        fileOutputStream.write($Proxy0s);
        fileOutputStream.flush();
        fileOutputStream.close();

    }
}
