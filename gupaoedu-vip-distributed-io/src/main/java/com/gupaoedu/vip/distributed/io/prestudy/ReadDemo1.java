package com.gupaoedu.vip.distributed.io.prestudy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadDemo1 {
    /*字节流*/
    public static void main(String[] args) {
        String rootPath = ReadDemo1.class.getResource("").getPath();
        FileInputStream fileInputStream=null;
        try {
            fileInputStream = new FileInputStream(rootPath+"\\testio.txt");
            byte[] bytes = new byte[1024];
            int read =0;
            while ((read=fileInputStream.read(bytes))>0){
                System.out.println(new String(bytes));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
