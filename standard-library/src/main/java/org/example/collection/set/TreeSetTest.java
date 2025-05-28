package org.example.collection.set;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetTest {

    public void testAdd() {
        TreeSet set = new TreeSet();
        set.add(1);
        set.add(2);
        set.add(3);
        // set.add("4"); // 添加不同类型元素会报错，因为添加是会对元素比较大小

        System.out.println(set);
    }

    public void testAddObject() {
        TreeSet set = new TreeSet();
        // 由于对象未实现排序接口，所以添加时报错
        set.add(new Person("zs", 11));
        set.add(new Person("ls", 12));
        set.add(new Person("ww", 13));

        System.out.println(set);
    }

    /**
     * 实现排序
     */
    public void testComparator() {
        TreeSet set = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 == o2) return 0;
                if (o1 instanceof Person && o2 instanceof Person) {
                    Person p1 = (Person) o1;
                    Person p2 = (Person) o2;
                    // 先按name排序
                    int v = p1.getName().compareTo(p2.getName());
                    if (v != 0) return v;

                    // name相同再按age排序
                    return Integer.compare(p1.getAge(), p2.getAge());

                }
                throw new RuntimeException("类型不匹配");
            }
        });
        // 由于对象未实现排序接口，所以添加时报错
        set.add(new Person("zs", 11));
        set.add(new Person("ls", 12));
        set.add(new Person("ww", 13));

        System.out.println(set);
    }
}
