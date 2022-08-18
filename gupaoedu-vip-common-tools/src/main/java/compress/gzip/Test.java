package compress.gzip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2022年07月27日 17:26
 */
public  class Test {
    public static void main(String[] args) {
//        String pStr=Gzip.compress("oTRFy6rJzvGyOMC_HJoUmEJlNrE8");
        //        System.out.println(pStr);
        String uStr=Gzip.uncompress("H4sIAAAAAAAAAMsPCXKrNCvyqipzr/T3dY738MoPzXX1yvErcrUAAM0Q6ZQcAAAA");
        System.out.println(uStr);
    }

    public static class Gzip{
        /**
         * 使用gzip压缩字符串
         * @param str 要压缩的字符串
         * @return
         */
        public static String compress(String str) {
            if (str == null || str.length() == 0) {
                return str;
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            GZIPOutputStream gzip = null;
            try {
                gzip = new GZIPOutputStream(out);
                gzip.write(str.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (gzip != null) {
                    try {
                        gzip.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return new sun.misc.BASE64Encoder().encode(out.toByteArray());
        }

        /**
         * 使用gzip解压缩
         * @param compressedStr 压缩字符串
         * @return
         */
        public static String uncompress(String compressedStr) {
            if (compressedStr == null) {
                return null;
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ByteArrayInputStream in = null;
            GZIPInputStream ginzip = null;
            byte[] compressed = null;
            String decompressed = null;
            try {
                compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedStr);
                in = new ByteArrayInputStream(compressed);
                ginzip = new GZIPInputStream(in);
                byte[] buffer = new byte[1024];
                int offset = -1;
                while ((offset = ginzip.read(buffer)) != -1) {
                    out.write(buffer, 0, offset);
                }
                decompressed = out.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (ginzip != null) {
                    try {
                        ginzip.close();
                    } catch (IOException e) {
                    }
                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                    }
                }
            }
            return decompressed;
        }
    }
}

