package com.gupaoedu.vip.zookeeper.prestudy.synchronize;

public class TestSynchronized {
    public static void main(String[] args) {
        Person person = new Person();
        Producer producer = new Producer(person);
        new Thread(producer).start();
        Customer customer = new Customer(person);
        new Thread(customer).start();

    }

}
