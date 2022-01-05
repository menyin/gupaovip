/**
 * 将此类放到linux里用java命令编译执行，再分别用jps，jstack命令查看java程序的线程状态
 */
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
