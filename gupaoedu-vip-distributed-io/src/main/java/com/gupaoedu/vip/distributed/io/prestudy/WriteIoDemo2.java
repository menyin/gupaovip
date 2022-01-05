package com.gupaoedu.vip.distributed.io.prestudy;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class WriteIoDemo2 {
    /*字符流*/
    public static void main(String[] args) {
        String rootPath = WriteIoDemo2.class.getResource("").getPath();
        Writer writer=null;
        try {
            writer = new FileWriter(rootPath + "testio2.txt");
            writer.write("资本主义好~~~");
            writer.write("社会主义好主义好~~~");
//            writer.flush();//此时在此处断点，到此断点后查看textios2.txt并没有内容，而是在调用此方法后才有内容到达textio2.txt
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
