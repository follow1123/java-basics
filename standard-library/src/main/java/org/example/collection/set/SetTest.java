package org.example.collection.set;

import java.util.HashSet;

public class SetTest {

    public void testSet() {
        HashSet set = new HashSet();
        set.add(123);
        set.add(1.1);
        set.add("string");
        System.out.println(set);
    }

    public void testSeq() {
        HashSet set = new HashSet();
        set.add(123);
        set.add(1.1);
        set.add("string");
        // 对象重新equals方法后还是会被重复放入Set内
        // 需要重写hashCode方法
        Person p1 = new Person("zs", 11);
        Person p2 = new Person("zs", 11);
        set.add(p1);
        set.add(p2);
        System.out.println(p1.equals(p2));
        System.out.println(set);
    }
}
