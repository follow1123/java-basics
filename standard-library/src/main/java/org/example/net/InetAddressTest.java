package org.example.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
    public void testInetAddress() {
        try {
            // 指定域名或IP获取地址信息
            InetAddress addr1 = InetAddress.getByName("192.168.173.109");
            System.out.println(addr1);
            InetAddress addr2 = InetAddress.getByName("www.baidu.com");
            System.out.println(addr2);
            InetAddress addr3 = InetAddress.getByName("127.0.0.1");
            System.out.println(addr3);

            // 获取本机地址信息
            InetAddress addr4 = InetAddress.getLocalHost();
            System.out.println(addr4);

            // 常用方法
            System.out.println(addr4.getHostName()); // 获取域名
            System.out.println(addr4.getHostAddress()); // 获取域名对应的IP地址
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
