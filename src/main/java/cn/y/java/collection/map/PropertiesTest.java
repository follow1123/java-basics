package cn.y.java.collection.map;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesTest {
    public static void main(String[] args) {
        Properties props = new Properties();
        InputStream is = null;
        try {
            // 配置文件放在resources目录下
            is = PropertiesTest.class.getClassLoader().getResourceAsStream("config.properties");
            props.load(is);
            // 获取配置文件信息
            System.out.println("name=" + props.getProperty("name"));
            System.out.println("password=" + props.getProperty("password"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
