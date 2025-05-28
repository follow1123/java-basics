package org.example.collection.collections;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchTest {

    /**
     * 最大值
     */
    public void testMax() {
        // 根据自然排序找出集合内最大的元素
        List list = RandomUtil.getRandomIntList(10, 50);
        System.out.println(list);

        System.out.println(Collections.max(list));

        System.out.println("---------");
        // 根据定制排序找出集合内最大的元素
        List strList = RandomUtil.getRandomStringList(10, 10);
        System.out.println(strList);

        // 根据字符串长度判断元素大小
        System.out.println(Collections.max(strList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        }));
    }

    /**
     * 最小值
     */
    public void testMin() {
        // 根据自然排序找出集合内最小的元素
        List list = RandomUtil.getRandomIntList(10, 50);
        System.out.println(list);

        System.out.println(Collections.min(list));

        System.out.println("---------");
        // 根据定制排序找出集合内最小的元素
        List strList = RandomUtil.getRandomStringList(10, 10);
        System.out.println(strList);

        // 根据字符串长度判断元素大小
        System.out.println(Collections.min(strList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        }));
    }

    /**
     * 二分搜索
     */
    public void testBinarySearch() {
        // 根据自然排序搜索元素
        List list = RandomUtil.getRandomIntList(10, 50);
        // 使用二分搜索时集合内的元素必须有序
        Collections.sort(list);
        Object value = list.get(RandomUtil.getRandomInteger(list.size()));
        System.out.println(list + " search: " + value);

        System.out.println(Collections.binarySearch(list, value));

        System.out.println("-------");
        // 根据定制排序搜索元素
        List list1 = RandomUtil.getRandomIntList(10, 50);
        Comparator<Integer> comparator = new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -Integer.compare(o1, o2);
            }
        };
        // 使用二分搜索时集合内的元素必须有序
        Collections.sort(list1, comparator);
        Integer value1 = (Integer) list1.get(RandomUtil.getRandomInteger(list1.size()));
        System.out.println(list1 + " search: " + value1);

        System.out.println(Collections.binarySearch(list1, value1, comparator));
    }

    /**
     * 元素出现的频率
     */
    public void testFrequency() {
        List list = RandomUtil.getRandomIntList(10, 5);
        System.out.println(list);
        // 获取元素出现的次数
        System.out.println(Collections.frequency(list, 1));
    }
}
