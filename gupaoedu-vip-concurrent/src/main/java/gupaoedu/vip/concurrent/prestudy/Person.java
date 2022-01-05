package gupaoedu.vip.concurrent.prestudy;

public class Person {
    private String name;
    private String gender;
    private boolean flage;

    public boolean isFlage() {
        return flage;
    }

    public void setFlage(boolean flage) {
        this.flage = flage;
    }

    public Person() {
    }

    public Person(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }
    public synchronized void write(String name, String gender){
        if(this.flage){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.name = name;
        this.gender = gender;
        this.flage=true;
        this.notify();

    }
    public synchronized void read(){
        if(!this.flage){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(this.name+"===="+this.gender);
        this.flage=false;
        this.notify();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
