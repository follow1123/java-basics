package org.example.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class CollectionTest {

    /**
     * 添加
     */
    public void testAdd() {
        // 添加元素
        Collection c1 = new ArrayList();
        c1.add("string");
        c1.add(123);
        c1.add(new Object());
        System.out.println(c1);

        Collection c2 = new ArrayList();
        c2.add(1);
        c2.add(2);

        // 添加集合
        c1.addAll(c2);
        System.out.println(c1);
    }

    /**
     * 判断
     */
    public void testJudge() {
        // 添加元素
        Collection c1 = new ArrayList();
        c1.add("string");
        c1.add(123);
        c1.add(new Object());
        System.out.println(c1);

        Collection c2 = new ArrayList();
        c2.add(1);
        c2.add(2);

        // 添加集合
        c1.addAll(c2);
        System.out.println(c1);

        // 获取集合内元素个数
        System.out.println(c1.size()); // 5

        // 判断集合是否为空
        System.out.println(c1.isEmpty()); // false

        // 判断集合内是否有指定的元素，如果要判断自定义类型，需要重写equals方法
        System.out.println(c1.contains(1)); // true

        // 判断集合内是否包含另一个集合内的所有元素
        System.out.println(c1.containsAll(c2)); // true

        // 判断两个集合是否相同
        System.out.println(c1.equals(c2)); // false
    }

    /**
     * 删除
     */
    public void testDelete() {
        // 添加元素
        Collection c1 = new ArrayList();
        c1.add("string");
        c1.add(123);
        c1.add(1.1);
        c1.add(new Object());

        // 删除指定元素，删除自定义元素时也需要重写equals方法
        // 如果有多个相同的元素，只会删除一个
        c1.remove(1.1);
        System.out.println(c1);

        // 清空集合，遍历将集合内的所有元素都删除
        c1.clear();
        System.out.println(c1.size());

        // 删除当前集合内所有于另一个集合元素相同的元素
        Collection c2 = new ArrayList();
        c2.add(1);
        c2.add(2);
        c2.add(3);
        c2.add(4);
        Collection c3 = new ArrayList();
        c3.add(2);
        c3.add(3);

        c2.removeAll(c3);
        System.out.println(c2); // [1, 4]

        // 取两个集合的交集
        Collection c4 = new ArrayList();
        c4.add(1);
        c4.add(2);
        c4.add(3);
        c4.add(4);
        Collection c5 = new ArrayList();
        c5.add(3);
        c5.add(4);
        c5.add(5);
        c5.add(6);

        c4.retainAll(c5);
        System.out.println(c4); // [3, 4]
    }

    /**
     * 其他
     */
    public void testOther() {
        // 添加元素
        Collection c1 = new ArrayList();
        c1.add("string");
        c1.add(123);
        c1.add(1.1);
        c1.add(new Object());

        // 集合转换为数组
        Object[] array = c1.toArray();

        // 计算集合的hash值
        System.out.println(c1.hashCode());

        // 返回迭代器，用于遍历集合
        c1.iterator();
    }

    /**
     * 集合和数组的相互转换
     */
    public void testCollectionAndArray() {
        // 添加元素
        Collection c1 = new ArrayList();
        c1.add("string");
        c1.add(123);
        c1.add(1.1);
        c1.add(new Object());

        // 集合转换为数组
        Object[] array = c1.toArray();

        // 数组转换为集合
        Collection c2 = Arrays.asList(1, 2, 3, 4);
        System.out.println(c2.size()); // 4

        // asList方法内传入对象数组，才会作为数组，传入基本数据类型的引用只会作为一个元素
        Collection c3 = Arrays.asList(new int[]{1, 2, 3});
        System.out.println(c3.size()); // 1
    }
}
