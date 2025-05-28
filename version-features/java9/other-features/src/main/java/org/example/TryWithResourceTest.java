package org.example;

import org.junit.jupiter.api.Test;

import java.io.*;

public class TryWithResourceTest {

    @Test
    public void testTryWithResource() throws FileNotFoundException {
        File resDir = new File(System.getProperty("user.dir"), "src/main/resources");
        FileInputStream fis = new FileInputStream(new File(resDir, "1.txt"));
        FileOutputStream fos = new FileOutputStream(new File(resDir, "2.txt"));
        try (fis; fos) {
            byte[] data = new byte[1024];
            int len;
            while ((len = fis.read(data)) != -1) {
                fos.write(data, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
