package gupaoedu.vip.java8demo;

public class InterfaceDemo {
    /**
     * 接口默认方法有点像C#的虚方法
     * @param args
     */
    public static void main(String[] args) {
        /*gupaoedu.vip.java8demo.Person person = new gupaoedu.vip.java8demo.Person();
        person.showName();*/

        IPerson iPerson = new IPerson() {};
        iPerson.showName();


    }

}
