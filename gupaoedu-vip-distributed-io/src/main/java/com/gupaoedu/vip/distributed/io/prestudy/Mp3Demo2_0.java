package com.gupaoedu.vip.distributed.io.prestudy;

import java.io.*;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class Mp3Demo2_0 {
    public static void main(String[] args) throws IOException {
        mergeMp3();
    }

    private static void mergeMp3() throws IOException {
        String rootPath = Mp3Demo2_0.class.getResource("").getPath();
        final LinkedHashSet<InputStream> inputStreams = new LinkedHashSet<InputStream>();
        boolean add1 = inputStreams.add(new FileInputStream(rootPath + "1.txt"));
        boolean add2 = inputStreams.add(new FileInputStream(rootPath + "2.txt"));
        boolean add3 = inputStreams.add(new FileInputStream(rootPath + "3.txt"));
        final Iterator<InputStream> iterator = inputStreams.iterator();
        SequenceInputStream sequenceInputStream = new SequenceInputStream(new Enumeration<InputStream>() {
            public boolean hasMoreElements() {
                return iterator.hasNext();
            }

            public InputStream nextElement() {
                return iterator.next();
            }
        });
        FileOutputStream fileOutputStream = new FileOutputStream(rootPath + "123.txt");
        byte[] buff = new byte[100];
        int len;
        while ((len = sequenceInputStream.read(buff)) != -1) {
            fileOutputStream.write(buff);
        }
    }
}
