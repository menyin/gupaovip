package compress.base64;

import org.apache.commons.codec.binary.Base64;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2022年07月27日 17:34
 */
public class Test {
    public static void main(String[] args) {
        String pStr=Bse64.compress("oTRFy6rJzvGyOMC_HJoUmEJlNrE8");
                System.out.println(pStr);
//        String uStr= Bse64.uncompress("H4sIAAAAAAAAAMsPCXKrNCvyqipzr/T3dY738MoPzXX1yvErcrUAAM0Q6ZQcAAAA");
//        System.out.println(uStr);
    }
    public static class Bse64{
        /**
         * 使用org.apache.commons.codec.binary.Base64压缩字符串
         * @param str 要压缩的字符串
         * @return
         */
        public static String compress(String str) {
            if (str == null || str.length() == 0) {
                return str;
            }
           return new String(Base64.encodeBase64(str.getBytes()));
//            return Base64.encodeBase64String(str.getBytes());
        }

        /**
         * 使用org.apache.commons.codec.binary.Base64解压缩
         * @param compressedStr 压缩字符串
         * @return
         */
        public static String uncompress(String compressedStr) {
            if (compressedStr == null) {
                return null;
            }
            return new String(Base64.decodeBase64(compressedStr.getBytes()));

//            return Base64.decodeBase64(compressedStr);
        }
    }
}
