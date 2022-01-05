package gupaoedu.vip.concurrent.liondemo;


import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class FlowControl3 {
    /**
     * netty的GlobalChannelTrafficShapingHandler实现限流
     * 在netty固定环境中使用，不好写demo 详见lion的GatewayClient的使用
     * @param args
     */
    public static void main(String[] args) {
        PersonHolderFactory factory=Person::new;
        Person jack = factory.create(18, "jack");
        System.out.println(jack);
    }

    @FunctionalInterface
    private interface PersonHolderFactory {
        Person create(int age,String name);
    }
    public static class  Person{
        private int age;
        private String name;

        private Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

    }
}
