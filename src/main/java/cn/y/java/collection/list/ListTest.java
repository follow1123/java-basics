package cn.y.java.collection.list;

import org.junit.jupiter.api.Test;

import java.util.*;

public class ListTest {

    /**
     * 添加
     */
    @Test
    public void testAdd() {
        List c1 = new ArrayList();
        c1.add("string");
        c1.add(123);
        c1.add(1.1);
        c1.add(new Object());

        Collection c2 = new ArrayList();
        c2.add(1);
        c2.add(2);
        c1.addAll(c2);
    }

    @Test
    public void testDelete() {
        List c1 = new ArrayList();
        c1.add("string");
        c1.add(123);
        c1.add(1.1);
        System.out.println(c1);
        c1.remove(0);
        c1.remove(1.1);
        System.out.println(c1);
    }

    /**
     * 修改
     */
    @Test
    public void testSet() {
        List c1 = new ArrayList();
        c1.add("string");
        c1.add(123);
        c1.add(1.1);
        System.out.println(c1);
        c1.set(1, "abc");

        System.out.println(c1);
    }

    /**
     * 查询
     */
    @Test
    public void testGet() {
        List c1 = new ArrayList();
        c1.add("string");
        c1.add(123);
        c1.add(1.1);
        System.out.println(c1.get(0));
        System.out.println(c1.get(2));
    }

    /**
     * 插入
     */
    @Test
    public void testInsert() {
        List c1 = new ArrayList();
        c1.add("string");
        c1.add(123);
        c1.add(1.1);

        System.out.println(c1);
        // 插入一个
        c1.add(1, "abc");

        System.out.println(c1);

        // 插入另一个集合
        Collection c2 = new ArrayList();
        c2.add(1);
        c2.add(2);
        c1.addAll(0, c2);

        System.out.println(c1);
    }

    /**
     * 长度
     */
    @Test
    public void testLength() {
        List c1 = new ArrayList();
        c1.add("string");
        c1.add(123);
        c1.add(1.1);

        System.out.println(c1.size());
    }

    /**
     * 迭代器遍历
     */
    @Test
    public void testIterator() {
        List c1 = new ArrayList();
        c1.add("string");
        c1.add(123);
        c1.add(1.1);
        Iterator iterator = c1.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    /**
     * for循环遍历
     */
    @Test
    public void testFor() {
        List c1 = new ArrayList();
        c1.add("string");
        c1.add(123);
        c1.add(1.1);

        // 增强for循环遍历
        for (Object o : c1) {
            System.out.println(o);
        }

        // 普通for循环遍历
        for (int i = 0; i < c1.size(); i++) {
            System.out.println(c1.get(i));
        }
    }

}
