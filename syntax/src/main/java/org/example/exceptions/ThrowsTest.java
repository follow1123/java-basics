package org.example.exceptions;

import java.io.FileInputStream;
import java.io.IOException;

public class ThrowsTest {
    public void method() throws IOException {
        FileInputStream fis = new FileInputStream("a.txt");
        fis.close();
    }

    public void testThrows() {
        try {
            method();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
