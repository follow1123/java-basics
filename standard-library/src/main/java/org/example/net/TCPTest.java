package org.example.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPTest {
    public void testServer() {
        // 创建ServerSocket
        try (ServerSocket ss = new ServerSocket(8989)) {
            // 接收客户端Socket
            Socket socket = null;
            InputStream is = null;
            OutputStream os = null;
            try {
                socket = ss.accept();
                System.out.println("连接到客户端：" + socket.getInetAddress().getHostAddress());
                // 接收数据
                is = socket.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] bytes = new byte[5];
                int len;
                while ((len = is.read(bytes)) != -1) {
                    baos.write(bytes, 0, len);
                }
                System.out.println("接收客户端数据：" + baos);
                System.out.println("回复客户端数据：" + socket.getInetAddress().getHostAddress());

                // 回复数据
                os = socket.getOutputStream();
                os.write("收到".getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (os != null) os.close();
                if (is != null) is.close();
                if (socket != null) socket.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void testClient() {
        // 创建Socket
        try (Socket socket = new Socket(InetAddress.getLocalHost(), 8989)) {
            System.out.println("连接到服务器：" + socket.getInetAddress().getHostAddress());
            // 接收客户端Socket
            OutputStream os = null;
            InputStream is = null;
            try {
                os = socket.getOutputStream();
                // 发送数据
                os.write("Hello".getBytes());
                // 如果写完数据后不再发送数据必须关闭输出流，否则服务端不知道数据有多少，会阻塞
                socket.shutdownOutput();

                is = socket.getInputStream();
                // 接收回复
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] bytes = new byte[5];
                int len;
                while ((len = is.read(bytes)) != -1) {
                    baos.write(bytes, 0, len);
                }
                System.out.println("接收服务端回复数据：" + baos);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (is != null) is.close();
                if (os != null) os.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
