package google.guava.tool;

import com.google.common.net.HostAndPort;

public class Demo1 {
    public static void main(String[] args) {
        HostAndPort hostAndPort = HostAndPort.fromString("192.168.1.10:8080");
        System.out.println(hostAndPort);
    }
}
