package org.example.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class EncodeDecodeTest {

    public void testPrintFromUTF8File() {
        String projectPath = System.getProperty("user.dir");
        File file = new File(projectPath, "src/main/resources/file_utf8.txt");
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
            char[] chars = new char[1024];
            int len;
            while ((len = isr.read(chars)) != -1) {
                System.out.println(new String(chars, 0, len));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void testPrintFromGBKFile() {
        String projectPath = System.getProperty("user.dir");
        File file = new File(projectPath, "src/main/resources/file_gbk.txt");
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(file), Charset.forName("gbk"))) {
            char[] chars = new char[1024];
            int len;
            while ((len = isr.read(chars)) != -1) {
                System.out.println(new String(chars, 0, len));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void testUTF8toGBK() {
        String projectPath = System.getProperty("user.dir");
        File file = new File(projectPath, "src/main/resources/file_utf8.txt");
        File file1 = new File(projectPath, "src/main/resources/file_gbk.txt");
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
             OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file1), Charset.forName("gbk"))) {
            char[] chars = new char[1024];
            int len;
            while ((len = isr.read(chars)) != -1) {
                osw.write(chars, 0, len);
            }
            System.out.println("done");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
