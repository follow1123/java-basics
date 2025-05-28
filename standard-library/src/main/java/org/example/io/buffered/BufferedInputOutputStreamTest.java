package org.example.io.buffered;



import java.io.*;

public class BufferedInputOutputStreamTest {

    /**
     * 不使用缓冲流复制大文件
     */
    private void copyVideoNoBuffered(String file, String dest) {
        try (FileInputStream fis = new FileInputStream(file);
             FileOutputStream fos = new FileOutputStream(dest)) {

            byte[] bytes = new byte[1024];
            int len;
            while ((len = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }

            System.out.println("done");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用缓冲流复制大文件
     */
    private void copyVideoWithBuffered(String file, String dest) {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest))) {

            byte[] bytes = new byte[1024];
            int len;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }

            System.out.println("done");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 发现使用缓冲流时速度明显更快
     */
    public void testCopyVideo() {
        String file = "";
        String dest1 = "";
        String dest2 = "";

        long start = System.currentTimeMillis();
        copyVideoNoBuffered(file, dest1);
        long end1 = System.currentTimeMillis();
        copyVideoWithBuffered(file, dest2);
        long end2 = System.currentTimeMillis();

        System.out.println("不使用缓冲流耗时：" + (end1 - start));
        System.out.println("使用缓冲流耗时：" + (end2 - end1));
    }
}
