package apche.commons.lang3demo;

import org.apache.commons.lang3.SystemUtils;

public class SystemUtilsDemo {
    public static void main(String[] args) {
        //原理都是调用System.getProperty()
        System.out.println(System.getProperty("java.version"));


        System.out.println(SystemUtils.getJavaHome());
        System.out.println(SystemUtils.getJavaIoTmpDir());
        System.out.println(SystemUtils.OS_NAME);
    }
}
