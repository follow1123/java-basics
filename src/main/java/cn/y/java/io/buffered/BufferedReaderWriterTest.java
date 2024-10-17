package cn.y.java.io.buffered;

import org.junit.jupiter.api.Test;

import java.io.*;

public class BufferedReaderWriterTest {

    /**
     * 读取utf8文件输出到控制台
     */
    @Test
    public void testReadUTF8File() {
        String projectPath = System.getProperty("user.dir");
        File file = new File(projectPath, "src/main/resources/file_utf8.txt");
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            char[] chars = new char[1024];
            int len;
            while ((len = br.read(chars)) != -1){
                System.out.println(new String(chars, 0, len));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 读取utf8文件输出到控制台，使用readLine方法
     */
    @Test
    public void testReadUTF8FileUseReadLine() {
        String projectPath = System.getProperty("user.dir");
        File file = new File(projectPath, "src/main/resources/file_utf8.txt");
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            // 每次读取一行，不包括行尾的换行符
            while ((line = br.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 复制文件，使用flush方法
     */
    @Test
    public void testCopyFileUseBuffered() {
        String projectPath = System.getProperty("user.dir");
        File file = new File(projectPath, "src/main/resources/file_utf8.txt");
        File dest = new File(projectPath, "src/main/resources/file_utf8_copy.txt");

        try(BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedWriter bw = new BufferedWriter(new FileWriter(dest))) {
            String line;
            while ((line = br.readLine()) != null){
                bw.write(line);
                bw.newLine();
                /*
                 使用flush()将内存的数据实时写出到磁盘
                 调用close()方法关闭流时也会将数据写入到磁盘
                 如果未调用flush()，结束时也未调用close()方法关闭流，可能出现数据丢失问题
                 */
                bw.flush();
            }
            System.out.println("done");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
