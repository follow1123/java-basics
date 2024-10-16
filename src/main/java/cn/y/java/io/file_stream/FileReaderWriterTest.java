package cn.y.java.io.file_stream;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderWriterTest {

    /**
     * 将数据从文件内一个字符一个字符的读取
     */
    @Test
    public void testReadChar() {
        // 创建文件的file对象
        String projectPath = System.getProperty("user.dir");
        File file = new File(projectPath, "src/main/resources/test1.txt");
        FileReader fileReader = null;
        try {
            // 创建流
            fileReader = new FileReader(file);
            // 读取数据
            int data;
            while ((data = fileReader.read()) != -1){
                System.out.print((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 使用try-with-resource简化关闭流操作
     * 放入try()内的流会自动关闭
     */
    @Test
    public void testTryWithResource() {
        // 创建文件的file对象
        String projectPath = System.getProperty("user.dir");
        File file = new File(projectPath, "src/main/resources/test1.txt");
        // 创建流
        try(FileReader fileReader = new FileReader(file)){
            // 读取数据
            int data;
            while ((data = fileReader.read()) != -1){
                System.out.print((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过添加临时char数组保存数据，减少对磁盘的访问，提高读取速度
     */
    @Test
    public void testReadCharArray() {
        // 创建文件的file对象
        String projectPath = System.getProperty("user.dir");
        File file = new File(projectPath, "src/main/resources/test1.txt");
        // 创建流
        try(FileReader fileReader = new FileReader(file)){
            char[] chars = new char[4];
            // 读取数据，一次读取多个数据
            int len = 0;
            while ((len = fileReader.read(chars)) != -1){
                System.out.print(new String(chars, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写出数据
     */
    @Test
    public void testWriter() {
        // 创建文件的file对象
        String projectPath = System.getProperty("user.dir");
        File file = new File(projectPath, "src/main/resources/test2.txt");
        /*
         创建文件输出流
         文件不存在默认创建，文件存在默认覆盖
         创建输出流时第二个参数，表示文件存在就追加内容
         */
        try (FileWriter fileWriter = new FileWriter(file)) {
            // 写出数据
            fileWriter.write("abc");
            System.out.println("完成");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
