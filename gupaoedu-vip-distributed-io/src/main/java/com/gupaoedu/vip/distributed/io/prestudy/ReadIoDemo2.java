package com.gupaoedu.vip.distributed.io.prestudy;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ReadIoDemo2 {
    /*字符流*/
    public static void main(String[] args) {
        String rootPath = ReadIoDemo2.class.getResource("").getPath();
        Reader reader=null;
        try {
            reader = new FileReader(rootPath + "testio2.txt");
            char[] chars = new char[2];
            while (reader.read(chars)>0){
                System.out.println(chars);
            }
            /*int len=0;
            while ((len=reader.read())>0) {
                System.out.println(len);
            }*/

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
