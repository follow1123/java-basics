package cn.y.java.oop.object.clone;

import org.junit.jupiter.api.Test;

public class CloneTest {

    @Test
    public void testClone() throws CloneNotSupportedException {

        Person zs = new Person("zs");
        Person zsCopy = (Person) zs.clone();

        // 克隆后两个对象并不是同一个
        System.out.println(zs == zsCopy);

        // 克隆后修改原始对象内的基本数据类型的属性，不会改变克隆对象内的基本数据类型属性
        zs.setName("zhangsan");
        System.out.println(zs.getName());
        System.out.println(zsCopy.getName());
    }
}
