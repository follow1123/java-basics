package cn.y.java.net.minichat.core;

import cn.y.java.net.minichat.model.FileModel;
import cn.y.java.net.minichat.model.UserModel;
import cn.y.java.net.minichat.service.UserService;
import cn.y.java.net.minichat.utils.RandomUtil;
import cn.y.java.net.minichat.utils.SocketUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {

    private ServerSocket serverSocket;
    private final ThreadPoolExecutor socketHandler = (ThreadPoolExecutor) Executors.newFixedThreadPool(6);
    private boolean running = true;
    private UserService userService;

    public void start() {
        try {
            int port = 8989;
            serverSocket = new ServerSocket(port);
            System.out.printf("server started listening %s\n", port);
            UserModel userModel = new UserModel();
            FileModel fileModel = new FileModel();
            this.userService = new UserService(userModel, fileModel);
            while (running) {
                if (serverSocket.isClosed()) running = false;
                Socket socket = serverSocket.accept();
                System.out.printf("client %s is connected\n", socket.getInetAddress().getHostAddress());
                new UserSocket(socket, socketHandler, userService);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            SocketUtil.close(serverSocket);
        }
    }
}
