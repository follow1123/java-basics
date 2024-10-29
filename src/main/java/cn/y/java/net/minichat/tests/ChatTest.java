package cn.y.java.net.minichat.tests;

import cn.y.java.net.minichat.core.Message;
import cn.y.java.net.minichat.utils.MsgUtil;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatTest {

    private void printSocketState(Socket socket){
        System.out.println("socket.isInputShutdown() = " + socket.isInputShutdown());
        System.out.println("socket.isOutputShutdown() = " + socket.isOutputShutdown());
    }

    @Test
    public void testServer() throws IOException {
        ServerSocket ss = new ServerSocket(8990);
        System.out.println("server started!");
        Socket s = ss.accept();

        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

        System.out.println("receive msg: " + br.readLine());

        String msg = "s1\n";
        System.err.println("send msg: " + msg);
        bw.write(msg);
        bw.flush();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        msg = "s2\n";
        System.err.println("send msg: " + msg);
        bw.write(msg);
        printSocketState(s);
        if (!s.isInputShutdown() || !s.isOutputShutdown()){
            bw.flush();
        }
        System.out.println("receive msg: " + br.readLine());

        ss.close();
    }

    @Test
    public void testClient() throws Exception {
        Socket s = new Socket(InetAddress.getLocalHost(), 8990);
        System.out.println("client started!");
        Thread.sleep(1000);
        s.close();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String msg = "c1\n";
        System.err.println("send msg: " + msg);
        bw.write(msg);
        bw.flush();

        System.out.println("receive msg: " + br.readLine());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        msg = "c2\n";
        System.err.println("send msg: " + msg);
        bw.write(msg);
        bw.flush();

        System.out.println("receive msg: " + br.readLine());
        s.close();
    }

    @Test
    public void testMsgServer() throws IOException {
        ServerSocket ss = new ServerSocket(8990);
        System.out.println("server started!");
        Socket s = ss.accept();

        InputStream is = s.getInputStream();
        OutputStream os = s.getOutputStream();

        System.out.println("receive msg: " + MsgUtil.readMsg(is).getBody());

        Message msg = Message.fromString("s1\ns11");
        System.err.println("send msg: " + msg);
        MsgUtil.writeMsg(msg, os);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        msg = Message.fromString("s2\ns22\ns222");
        System.err.println("send msg: " + msg);
        MsgUtil.writeMsg(msg, os);
        System.out.println("receive msg: " + MsgUtil.readMsg(is).getBody());

        ss.close();
    }

    @Test
    public void testMsgClient() throws IOException {
        Socket s = new Socket(InetAddress.getLocalHost(), 8990);
        System.out.println("client started!");

        InputStream is = s.getInputStream();
        OutputStream os = s.getOutputStream();

        Message msg = Message.fromString("c1\nc11");
        System.err.println("send msg: " + msg);
        MsgUtil.writeMsg(msg, os);

        System.out.println("receive msg: " + MsgUtil.readMsg(is).getBody());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        msg = Message.fromString("c2\nc22\nc222");
        System.err.println("send msg: " + msg);
        MsgUtil.writeMsg(msg, os);

        System.out.println("receive msg: " + MsgUtil.readMsg(is).getBody());
        s.close();
    }

    @Test
    public void testBlockServer() throws IOException {
        ServerSocket ss = new ServerSocket(8990);
        System.out.println("server started!");
        Socket s = ss.accept();
        System.out.println("accept a socket");

        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        br.readLine();
        System.out.println("read message");
        // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

        // System.out.println("receive msg: " + br.readLine());
        //
        // String msg = "s1\n";
        // System.err.println("send msg: " + msg);
        // bw.write(msg);
        // bw.flush();
        //
        // try {
        //     Thread.sleep(1000);
        // } catch (InterruptedException e) {
        //     throw new RuntimeException(e);
        // }
        // msg = "s2\n";
        // System.err.println("send msg: " + msg);
        // bw.write(msg);
        // bw.flush();
        // System.out.println("receive msg: " + br.readLine());
        //
        ss.close();
    }

    @Test
    public void testBlockClient() throws IOException {
        System.out.printf("this is name %s\n", "wr1");
        Socket s = new Socket(InetAddress.getLocalHost(), 8990);
        System.out.println("client started!");
        while (true){
            if (1 != 1) break;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

        // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        // BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        // String msg = "c1\n";
        // System.err.println("send msg: " + msg);
        // bw.write(msg);
        // bw.flush();
        //
        // System.out.println("receive msg: " + br.readLine());
        // try {
        //     Thread.sleep(1000);
        // } catch (InterruptedException e) {
        //     throw new RuntimeException(e);
        // }
        //
        // msg = "c2\n";
        // System.err.println("send msg: " + msg);
        // bw.write(msg);
        // bw.flush();
        //
        // System.out.println("receive msg: " + br.readLine());
        s.close();
    }
}
