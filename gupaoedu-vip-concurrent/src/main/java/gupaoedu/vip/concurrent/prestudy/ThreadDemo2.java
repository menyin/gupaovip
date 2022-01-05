package gupaoedu.vip.concurrent.prestudy;

public class ThreadDemo2 {
    //用wait notify实现需求：要求只有生产者生产后才能消费

    public static void main(String[] args) {
        Person person = new Person();
        //生产者
        new Producer(person).start();
        //消费者
        new Customer(person).start();
    }
}
