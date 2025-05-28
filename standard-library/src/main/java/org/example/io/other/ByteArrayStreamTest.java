package org.example.io.other;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ByteArrayStreamTest {
    public void testByteArrayStream() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(5);
            baos.write(1);
            baos.write(new byte[]{2, 3, 4, 5});

            // 转换为byte数组
            byte[] byteArray = baos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);

            System.out.println(bais.read());
            System.out.println(bais.read());
            System.out.println(bais.read());
            System.out.println(bais.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
