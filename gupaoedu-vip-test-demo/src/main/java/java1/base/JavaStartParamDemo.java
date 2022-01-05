package java1.base;

import java.io.IOException;

/**
 * 测试java程序启动参数
 * 测试：
 *  target\classes目录下在运行cmd "java java1.base.JavaStartParamDemo 1 2 3 4"
 */
public class JavaStartParamDemo {
    public static void main(String[] args) throws IOException {
        System.out.println("------");
        for (String arg : args) {
            System.out.println(arg);
        }
        System.in.read();
    }
}
