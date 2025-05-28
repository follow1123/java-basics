package org.example;

import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientTest {

    @BeforeAll
    static void startServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8989), 0);
        server.createContext("/test", exchange -> {
            if ("POST".equals(exchange.getRequestMethod())) {
                InputStream is = exchange.getRequestBody();
                String reqBody = new String(is.readAllBytes());
                System.out.println(reqBody);
            }
            String respDate = "Hello Http";
            exchange.sendResponseHeaders(200, respDate.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(respDate.getBytes());
            os.close();
        });
        server.setExecutor(null);
        server.start();
    }

    @Test
    public void testSend() throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8989/test"))
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> respData = client.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println(respData.body());
    }

    @Test
    public void testSendAsync() {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8989/test"))
                .GET()
                .build();
        HttpClient.newHttpClient().sendAsync(req, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .exceptionally(e -> {
                    System.out.println(e.getMessage());
                    return null;
                });

        // 添加睡眠以防主线程过早退出
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSendPOST() throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8989/test"))
                .POST(HttpRequest.BodyPublishers.ofString("this is post body"))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> respData = client.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println(respData.body());
    }
}
