package cn.y.java.collection.iterator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class IteratorTest {

    @Test
    public void testIterator() {
        Collection c1 = new ArrayList();
        c1.add("string");
        c1.add(123);
        c1.add(1.1);
        c1.add(new Object());

        // 获取迭代器对象
        Iterator iterator = c1.iterator();

        // // 依次获取集合内的元素
        // System.out.println(iterator.next());
        // System.out.println(iterator.next());
        // System.out.println(iterator.next());
        // System.out.println(iterator.next());
        // // 如果超出集合内元素的个数则会报错NoSuchElementException
        // System.out.println(iterator.next());

        // 获取迭代器具体实现
        System.out.println(iterator.getClass());

        // 使用迭代器遍历集合
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // 遍历时删除指定元素
        // while (iterator.hasNext()){
        //     if (iterator.next().equals(1.1)){
        //         iterator.remove();
        //     }
        // }
    }

    /**
     * foreach jdk5
     */
    @Test
    public void testForeach() {
        Collection c1 = new ArrayList();
        c1.add("string");
        c1.add(123);
        c1.add(1.1);
        c1.add(new Object());

        for (Object o : c1) {
            System.out.println(o);
        }
    }
}
