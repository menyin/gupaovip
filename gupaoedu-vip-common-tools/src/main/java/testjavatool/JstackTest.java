package testjavatool;

public class JstackTest {
    public static void main(String[] args) {
        while (true) {
            try {
                Thread.sleep(2000);
                System.out.println("利用jps和jstack查看java程序的堆栈情况");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
