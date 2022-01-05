package java1.base;

import java.io.IOException;

/**
 * 获取指定L范围内的随机数整数
 */
public class RandomDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
//        System.out.println(3.5%5);
        int L = 5;
        System.out.println((int)(Math.random() * L % L));

    }


}
