package org.example.collection.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OthersTest {

    /**
     * 复制
     */
    public void testCopy() {
        List list = RandomUtil.getRandomIntList(10, 50);
        System.out.println(list);

        // 由于创建新的数组后大小是0，直接使用会报错，所以需要使用下面的操作
        List<Object> newList = Arrays.asList(new Object[list.size()]);
        Collections.copy(newList, list);
        System.out.println(newList);
    }

    /**
     * 全部替换
     */
    public void testReplaceAll() {
        List list = RandomUtil.getRandomIntList(10, 5);
        System.out.println(list);
        System.out.println(Collections.replaceAll(list, 1, 100));
        System.out.println(list);
    }

    /**
     * 转换为只读集合或Map
     */
    public void testUnmodifiableXxx() {
        List list = RandomUtil.getRandomIntList(10, 50);
        System.out.println(list.get(0));
        list.add("1234");
        System.out.println(list);

        // 转换为只读集合
        List list1 = Collections.unmodifiableList(list);
        // 正常读取
        System.out.println(list.get(0));
        // list1.add(1234); 写入元素会报错
    }

    /**
     * 添加多个元素
     */
    public void testAddAll() {
        List list = RandomUtil.getRandomIntList(5, 50);
        List list1 = RandomUtil.getRandomStringList(5, 5);

        System.out.println(list1);
        Collections.addAll(list, list1.toArray());
        System.out.println(list);
    }

    /**
     * 转换为线程安全的集合或Map
     */
    public void testSynchronizedXxx() {
        // 线程数组，用于等待所有线程执行完
        ArrayList<Thread> threadList = new ArrayList();

        // 普通集合在多线程环境下添加100000个元素
        ArrayList list = new ArrayList();
        for (int i = 0; i < 100000; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    list.add("a");
                }
            });
            threadList.add(thread);
            thread.start();
        }

        // 同步集合在多线程环境下添加100000个元素
        List syncList = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 100000; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    syncList.add("a");
                }
            });
            threadList.add(thread);
            thread.start();
        }

        // 等待线程执行完成
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // 普通集合元素的个数
        System.out.println(list.size());

        // 同步集合元素的个数
        System.out.println(syncList.size());
    }
}
