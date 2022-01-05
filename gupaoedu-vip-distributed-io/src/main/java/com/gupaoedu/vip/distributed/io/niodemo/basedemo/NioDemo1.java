package com.gupaoedu.vip.distributed.io.niodemo.basedemo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioDemo1 {
    public static void main(String[] args) {
        String rootPath = NioDemo1.class.getResource("").getPath();

        FileOutputStream fos=null;
        FileChannel channel=null;
        try {
            fos=new FileOutputStream(rootPath+"testio1.txt");
            channel = fos.getChannel();
            ByteBuffer buffW = ByteBuffer.allocate(1024);
            buffW.put("社会主义好".getBytes());
            buffW.flip();
            channel.write(buffW);
            /*ByteBuffer buffR = ByteBuffer.allocate(1024);
            channel.read(buffR);
            byte[] bytes = new byte[1024];
            buffR.get(bytes);
            String s = new String(bytes);
            System.out.println(s);*/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (channel != null) {
                    channel.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
