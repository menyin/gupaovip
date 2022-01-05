package com.gupaoedu.vip.pattern.proxy.dynamic_cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class CglibProxy implements MethodInterceptor {
    private Object target;//需要代理的目标对象

    //重写拦截方法
    @Override
    public Object intercept(Object obj, Method method, Object[] arr, MethodProxy proxy) throws Throwable {
        System.out.println("Cglib动态代理，监听开始！");
        Object invoke = method.invoke(target, arr);//方法执行，参数：target 目标对象 arr参数数组
        System.out.println("Cglib动态代理，监听结束！");
        return invoke;
    }
    //定义获取代理对象方法
    public Object getCglibProxy(Object objectTarget){
        //为目标对象target赋值
        this.target = objectTarget;
        Enhancer enhancer = new Enhancer();
        //设置父类,因为Cglib是针对指定的类生成一个子类，所以需要指定父类
        enhancer.setSuperclass(objectTarget.getClass());
        enhancer.setCallback(this);// 设置回调
        return enhancer.create();//创建并返回代理对象;
    }

    /**
     * 代理有参构造函数的对象
     * 注意：此方法为适配目标代理对象是有参构造函数的情况
     *       实际上传入的构造参数值没有赋予到代理对象
     * @param objectTarget 目标代理对象
     * @param constructorArgTypes 目标代理对象构造函数参数类型
     * @param constructorArgs 目标代理对象构造函数参数值
     * @return
     */
    public Object getCglibProxy(Object objectTarget,Class[] constructorArgTypes, Object[] constructorArgs) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //为目标对象target赋值
        this.target=objectTarget;
        Enhancer enhancer = new Enhancer();
        //设置父类,因为Cglib是针对指定的类生成一个子类，所以需要指定父类
        enhancer.setSuperclass(objectTarget.getClass());
        enhancer.setCallback(this);// 设置回调
        return enhancer.create(constructorArgTypes,constructorArgs);//创建并返回代理对象
    }

    /*public static void main(String[] args) {
        CglibProxy cglib = new CglibProxy();//实例化CglibProxy对象
        UserManager user =  (UserManager) cglib.getCglibProxy(new UserManagerImpl());//获取代理对象
        user.delUser("admin");//执行删除方法
    }*/

}