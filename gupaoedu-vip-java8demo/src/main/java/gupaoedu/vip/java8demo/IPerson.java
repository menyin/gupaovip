package gupaoedu.vip.java8demo;

public interface IPerson {
    default void showName(){
        System.out.println("显示一个人的名称...");
    }
}
