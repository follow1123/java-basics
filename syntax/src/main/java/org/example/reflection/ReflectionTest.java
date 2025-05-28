package org.example.reflection;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

public class ReflectionTest {
    public void testNormal() {
        // 创建对象
        Person person = new Person();

        // 调用属性
        person.name = "zs";
        System.out.println(person.name);

        // 调用方法
        person.eat();
    }

    public void testUseReflection() throws Exception {
        // 创建对象
        Class<Person> personClass = Person.class;
        Person person = personClass.newInstance();

        // 调用属性
        Field nameField = personClass.getField("name");
        nameField.set(person, "zs");
        System.out.println(nameField.get(person));

        // 调用方法
        Method eatMethod = personClass.getMethod("eat");
        eatMethod.invoke(person);
    }

    public void testAccessPrivateMembers() throws Exception {
        // 调用私有构造器
        Class<Person> personClass = Person.class;
        Constructor<Person> personConstructor = personClass.getDeclaredConstructor(String.class, int.class);
        // 调用私有构造器前需要设置构造器的访问权限
        personConstructor.setAccessible(true);
        Person person = personConstructor.newInstance("zs", 18);
        System.out.println(person);

        // 调用私有属性
        Field ageField = personClass.getDeclaredField("age");
        // 调用私有属性前需要设置属性的访问权限
        ageField.setAccessible(true);
        ageField.set(person, 20);
        System.out.println(ageField.get(person));

        // 调用私有方法
        Method sleepMethod = personClass.getDeclaredMethod("sleep", int.class);
        // 调用私有方法前需要设置方法的访问权限
        sleepMethod.setAccessible(true);
        String str = (String) sleepMethod.invoke(person, 8);
        System.out.println(str);
    }

    /**
     * 获取Class的几种方式
     */
    public void testGetClass() throws Exception {
        // 使用运行时静态属性获取
        Class<Person> class1 = Person.class;

        // 使用对象实例的getClass()方法获取
        Person person = new Person();
        Class<? extends Person> class2 = person.getClass();

        // 使用Class.forName方法获取
        Class<?> class3 = Class.forName("org.example.reflection.Person");

        // 使用类加载器获取
        Class<?> class4 = ClassLoader.getSystemClassLoader().loadClass("org.example.reflection.Person");

        // Class在运行时只会创建一次
        System.out.println(class1 == class2);
        System.out.println(class1 == class3);
        System.out.println(class1 == class4);
    }

    public void testDataTypeClass() {
        Class<Object> c1 = Object.class;
        Class<Comparable> c2 = Comparable.class;
        Class<String[]> c3 = String[].class;
        Class<int[][]> c4 = int[][].class;
        Class<ElementType> c5 = ElementType.class;
        Class<Override> c6 = Override.class;
        Class<Integer> c7 = int.class;
        Class<Void> c8 = void.class;
        Class<Class> c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class<? extends int[]> aClass = a.getClass();
        Class<? extends int[]> bClass = b.getClass();
        // 只要类型一样，Class就是一样的，和类型的大小无关
        System.out.println(aClass == bClass);
    }

    public void testGetClassLoader() {
        // jdk8和jdk17类加载器名称不同
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
        ClassLoader parent = systemClassLoader.getParent();
        System.out.println(parent);
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);

        System.out.println("----------");

        // 使用应用程序类加载器加载
        System.out.println(Person.class.getClassLoader());

        // java核心api使用引导类加载器加载
        System.out.println(String.class.getClassLoader());
    }

    public void testLoadProperties() throws IOException {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("config.properties");
        Properties prop = new Properties();
        prop.load(is);
        System.out.println(prop.get("name"));
    }
}
