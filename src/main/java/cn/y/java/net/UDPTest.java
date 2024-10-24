package cn.y.java.net;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.*;

public class UDPTest {
    
    @Test
    public void testSender() {
        int port = 8990;
        try (DatagramSocket ds = new DatagramSocket()) {
            // 准备数据
            byte[] bytes = "Hello".getBytes();
            // 包内填写发送的地址
            DatagramPacket dp = new DatagramPacket(bytes, 0, bytes.length, InetAddress.getLocalHost(), port);
            // 发送
            ds.send(dp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Test
    public void testReceiver() {
        // 开启UDP服务
        int port = 8990;
        try (DatagramSocket ds = new DatagramSocket(port)) {
            // 准备包
            byte[] bytes = new byte[1024];
            DatagramPacket dp = new DatagramPacket(bytes, 0, bytes.length);
            // 接收数据
            ds.receive(dp);
            // 获取数据
            String str = new String(dp.getData(), 0, dp.getLength());
            System.out.println(str);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
