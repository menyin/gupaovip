package com.gupaoedu.vip.distributed.io.prestudy;

import java.io.*;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class Mp3Demo2 {
    public static void main(String[] args) throws IOException {
        mergeMp3();
    }
    private static void mergeMp3() throws IOException {
        String rootPath = Mp3Demo2.class.getResource("").getPath();
         LinkedHashSet<InputStream> inputStreams = new LinkedHashSet<InputStream>();

        inputStreams.add(new FileInputStream(new File(rootPath + "lang-0.mp3")));
        inputStreams.add(new FileInputStream(new File(rootPath + "lang-1.mp3")));
        inputStreams.add(new FileInputStream(new File(rootPath + "lang-2.mp3")));
        final Iterator<InputStream> iterator = inputStreams.iterator();

        SequenceInputStream sequenceInputStream = new SequenceInputStream(new Enumeration<InputStream>(){

            public boolean hasMoreElements() {
                return iterator.hasNext();
            }

            public InputStream nextElement() {
                return iterator.next();
            }
        });
        FileOutputStream fileOutputStream = new FileOutputStream(rootPath + "langCopy.mp3");
        byte[] buff = new byte[1024];
        int len;
        while ((len = sequenceInputStream.read(buff)) != -1) {

            fileOutputStream.write(buff,0,len);

        }
        sequenceInputStream.close();
        fileOutputStream.close();


    }
}
