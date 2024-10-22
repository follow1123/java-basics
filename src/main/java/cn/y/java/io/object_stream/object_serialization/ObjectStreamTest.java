package cn.y.java.io.object_stream.object_serialization;

import org.junit.jupiter.api.Test;

import java.io.*;

public class ObjectStreamTest {

    /**
     * 对没有实现Serializable接口的对象进行序列化
     * 报错NotSerializableException
     */
    @Test
    public void testObjNotImplSerializable() {
        String projectPath = System.getProperty("user.dir");
        File file = new File(projectPath, "src/main/resources/object1.txt");
        // 序列化对象
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            ObjNonSerializable obj = new ObjNonSerializable("a", 1);
            oos.writeObject(obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 测试实现Serializable接口的对象，但是没添加serialVersionUID属性
     */
    @Test
    public void testObjNoVersionUID() {
        String projectPath = System.getProperty("user.dir");
        File file = new File(projectPath, "src/main/resources/object2.txt");
        // 序列化对象
        // try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
        //     ObjNoVersionUID obj = new ObjNoVersionUID("a", 1);
        //     oos.writeObject(obj);
        // } catch (IOException e) {
        //     throw new RuntimeException(e);
        // }

        // 反序列化对象
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            /*
             反序列化正常
             但如果在类中新加一个属性后就会报错InvalidClassException
             因为实现Serializable接口后默认自动生成一个serialVersionUID
             而修改类后这个serialVersionUID就和之前的不一样了，而文件内还是使用的之前的id，所以报错
             */
            ObjNoVersionUID obj = (ObjNoVersionUID) ois.readObject();
            System.out.println(obj);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 测试实现Serializable接口的对象，并添加serialVersionUID属性
     */
    @Test
    public void testSerializableObject() {
        String projectPath = System.getProperty("user.dir");
        File file = new File(projectPath, "src/main/resources/object3.txt");
        // 序列化对象
        // try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
        //     ObjSerializable obj = new ObjSerializable("a", 1);
        //     oos.writeObject(obj);
        // } catch (IOException e) {
        //     throw new RuntimeException(e);
        // }

        // 反序列化对象
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            /*
             反序列化正常，无论怎么修改类都正常
             */
            ObjSerializable obj = (ObjSerializable) ois.readObject();
            System.out.println(obj);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 测试实现Serializable接口的对象，但有个属性没有实现Serializable接口
     * 报错NotSerializableException
     */
    @Test
    public void testHasNonSerializableField() {
        String projectPath = System.getProperty("user.dir");
        File file = new File(projectPath, "src/main/resources/object4.txt");
        // 序列化对象
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            ObjHasNonSerializableField obj = new ObjHasNonSerializableField("a", 1, new ObjNonSerializable("b", 2));
            oos.writeObject(obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 测试序列化实现Serializable接口的对象，有transient关键字标记的属性和静态属性
     */
    @Test
    public void testSerializationObjHasSpecialFields() {
        String projectPath = System.getProperty("user.dir");
        File file = new File(projectPath, "src/main/resources/object5.txt");
        // 序列化对象
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            ObjHasSpecialField obj = new ObjHasSpecialField("a", 1, 1.1F);
            oos.writeObject(obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 测试反序列化实现Serializable接口的对象，有transient关键字标记的属性和静态属性
     * 由于静态属性赋值后直到程序结束不会消失，所以反序列化需要单独测试
     */
    @Test
    public void testDeserializationObjHasSpecialFields() {
        String projectPath = System.getProperty("user.dir");
        File file = new File(projectPath, "src/main/resources/object5.txt");
        // 反序列化对象
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            /*
             反序列化正常
             使用transient标记的属性和静态属性都未保存
             */
            ObjHasSpecialField obj = (ObjHasSpecialField) ois.readObject();
            System.out.println(obj);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
