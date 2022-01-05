package gupaoedu.vip.performance.jvm;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 测试时，配置运行参数 -XX:+PrintGCDetails
 */
public class GCDemo1 {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 10000; i++) {
            ArrayList<String> list = new ArrayList<String>();
            list.add("aaaaaaaaaaaa");
        }
        System.gc();
        System.in.read();
    }
}
