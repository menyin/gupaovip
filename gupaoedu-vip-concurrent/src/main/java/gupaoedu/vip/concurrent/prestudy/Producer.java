package gupaoedu.vip.concurrent.prestudy;

public class Producer extends Thread {
    private Person person;
    public Producer(Person person){
        this.person = person;
    }

    @Override
    public void run() {
        int i=0;
        while (true) {
            if(i%2==0) {
                this.person.write("jack","男");
            }else{
                this.person.write("rose","女");
            }
            System.out.println("生产......");
            i++;
        }
    }
}
