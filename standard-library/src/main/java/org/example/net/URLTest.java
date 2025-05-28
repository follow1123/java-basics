package org.example.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class URLTest {
    public void testURL() {
        String str = "https://127.0.0.1:8080/examples/a.txt?type=1";
        try {
            URL url = new URL(str);
            System.out.println(url.getProtocol()); // 协议名
            System.out.println(url.getHost()); // 主机号
            System.out.println(url.getPort()); // 端口号
            System.out.println(url.getPath()); // 文件路径
            System.out.println(url.getFile()); // 文件名
            System.out.println(url.getQuery()); // 查询参数
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 统一资源标识，相当于都封装一个链接的对象，不能获取资源
     */
    public void testURI() {
        String str = "https://127.0.0.1:8080/examples/a.txt?type=1";
        try {
            URI uri = new URI(str);
            // System.out.println(uri.getProtocol()); // 协议名
            System.out.println(uri.getHost()); // 主机号
            System.out.println(uri.getPort()); // 端口号
            System.out.println(uri.getPath()); // 文件路径
            // System.out.println(uri.getFile()); // 文件名
            System.out.println(uri.getQuery()); // 查询参数
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 测试下载URL的图片
     */
    public void testDownloadImg() {
        try {
            String projectPath = System.getProperty("user.dir");
            File file = new File(projectPath, "src/main/resources/test.png");
            URL url = new URL("");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            try (InputStream is = connection.getInputStream();
                 FileOutputStream fos = new FileOutputStream(file)) {
                byte[] bytes = new byte[1024];
                int len;
                while ((len = is.read(bytes)) != -1) {
                    fos.write(bytes, 0, len);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            connection.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
