package gupaoedu.vip.concurrent.prestudy;


public class Customer extends Thread {
    private Person person;
    public Customer(Person person){
        this.person = person;
    }

    @Override
    public void run() {
        while (true) {
            this.person.read();
        }
    }
}