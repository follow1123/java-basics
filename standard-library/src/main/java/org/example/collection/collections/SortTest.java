package org.example.collection.collections;

import java.util.*;


public class SortTest {

    /**
     * 反转集合
     */
    public void testReverse() {
        List list = RandomUtil.getRandomIntList(10, 50);
        System.out.println(list);

        Collections.reverse(list);

        System.out.println(list);
    }

    /**
     * 打乱集合内元素位置
     */
    public void testShuffle() {
        List list = RandomUtil.getRandomIntList(10, 50);
        // 先排序
        Collections.sort(list);
        System.out.println(list);

        // 打乱
        Collections.shuffle(list);

        System.out.println(list);
    }

    /**
     * 排序
     */
    public void testSort() {
        // 自然排序
        List list = RandomUtil.getRandomIntList(10, 50);
        System.out.println(list);

        Collections.sort(list);

        System.out.println(list);

        System.out.println("--------");
        // 定制排序
        List list1 = RandomUtil.getRandomIntList(10, 50);
        System.out.println(list1);

        // 改为降序
        Collections.sort(list1, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return -Integer.compare((Integer) o1, (Integer) o2);
            }
        });

        System.out.println(list1);
    }

    /**
     * 调换元素位置
     */
    public void testSwap() {
        List list = RandomUtil.getRandomIntList(10, 50);
        System.out.println(list);

        // 头尾元素调换
        Collections.swap(list, 0, list.size() - 1);

        System.out.println(list);
    }
}
