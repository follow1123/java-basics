package org.example.io.file;

import java.io.*;

public class FileInputOutputStreamTest {

    /**
     * 测试使用Reader和Writer复制图片
     * 复制后图片损坏
     */
    public void testCopyImgUseReaderWriter() {
        String projectPath = System.getProperty("user.dir");
        File file = new File(projectPath, "src/main/resources/jjlight.jpg");
        File destFile = new File(projectPath, "src/main/resources/jjlight_copy.jpg");

        try (FileReader fileReader = new FileReader(file);
             FileWriter fileWriter = new FileWriter(destFile)) {
            char[] chars = new char[4];
            int len;
            while ((len = fileReader.read(chars)) != -1){
                fileWriter.write(chars, 0, len);
            }

            System.out.println("done");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用InputStream/OutputStream复制图片
     * 复制后图片正常显示
     */
    public void testCopyImgUseInputOutputStream() {
        String projectPath = System.getProperty("user.dir");
        File file = new File(projectPath, "src/main/resources/jjlight.jpg");
        File destFile = new File(projectPath, "src/main/resources/jjlight_copy.jpg");

        try (FileInputStream fis = new FileInputStream(file);
             FileOutputStream fos = new FileOutputStream(destFile)) {
            byte[] bytes = new byte[1024];
            int len;
            while ((len = fis.read(bytes)) != -1){
                fos.write(bytes, 0, len);
            }

            System.out.println("done");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用InputStream/OutputStream复制文本文件
     */
    public void testCopyTxtUseInputOutputStream() {
        String projectPath = System.getProperty("user.dir");
        File file = new File(projectPath, "src/main/resources/test1.txt");
        File destFile = new File(projectPath, "src/main/resources/test1_copy.txt");

        try (FileInputStream fis = new FileInputStream(file);
             FileOutputStream fos = new FileOutputStream(destFile)) {
            byte[] bytes = new byte[1024];
            int len;
            while ((len = fis.read(bytes)) != -1){
                fos.write(bytes, 0, len);
            }

            System.out.println("done");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用InputStream读取文本文件到控制台上显示
     * 文本文件内有中文可能乱码
     * 因为中文编码内一个中文是由多个字节组成的
     * 在读取数据时，可能刚好读取中文的一半就输出了，所以导致乱码
     */
    public void testReadTxtUseInputStream() {
        String projectPath = System.getProperty("user.dir");
        File file = new File(projectPath, "src/main/resources/test1.txt");

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] bytes = new byte[4];
            int len;
            while ((len = fis.read(bytes)) != -1){
                String s = new String(bytes, 0, len);
                System.out.print(s);
            }

            System.out.println();
            System.out.println("done");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
