package com.gupaoedu.vip.distributed.io.niodemo.buffdemo;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

public class BufferDemo {
    public static void main(String[] args) {
        decode("社会主义好，测试解码");
        encode("社会主义好，测试编码");
    }



    private static void decode(String str) {
        //模拟数据，将数据推入buff
        ByteBuffer buff = ByteBuffer.allocate(50);
        buff.put(str.getBytes());
        buff.flip();
        //用Charset对ByteBuffer对象进行解码
        Charset charset = Charset.forName("UTF-8");
        CharBuffer charBuffer = charset.decode(buff);
        char[] array = charBuffer.array();//此处array的真实长度是30而不是50
        System.out.println(new String(array));
    }

    private static void encode(String str) {
        CharBuffer charBuff = CharBuffer.allocate(50);
        charBuff.append(str);
        charBuff.flip();
        Charset charset = Charset.forName("UTF-8");
        ByteBuffer byteBuffer = charset.encode(charBuff);
        byte[] bytes = byteBuffer.array();
        byte[] bytes1 = Arrays.copyOf(bytes, byteBuffer.limit());
        System.out.println(Arrays.toString(bytes1));
        System.out.println(Arrays.toString(bytes));//注意输出的数组长度是包含空余的长度的
//        System.out.println(Arrays.toString(bytes).length());
    }
}
