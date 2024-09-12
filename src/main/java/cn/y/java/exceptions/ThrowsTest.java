package cn.y.java.exceptions;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class ThrowsTest {

    public void method() throws IOException {
        FileInputStream fis = new FileInputStream("a.txt");
        fis.close();
    }

    @Test
    public void testThrows() {
        try {
            method();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
