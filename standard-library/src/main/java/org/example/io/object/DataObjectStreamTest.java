package org.example.io.object;

import java.io.*;

public class DataObjectStreamTest {

    public void testDataSerialization() {
        String projectPath = System.getProperty("user.dir");
        File file = new File(projectPath, "src/main/resources/data.txt");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeInt(1);
            oos.writeFloat(1.1F);
            oos.writeChar('a');
            oos.writeBoolean(true);
            oos.writeUTF("测试");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void testDataDeserialization() {
        String projectPath = System.getProperty("user.dir");
        File file = new File(projectPath, "src/main/resources/data.txt");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            // 读取的循序必须和写入的顺序一致
            System.out.println(ois.readInt());
            System.out.println(ois.readFloat());
            System.out.println(ois.readChar());
            System.out.println(ois.readBoolean());
            System.out.println(ois.readUTF());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
