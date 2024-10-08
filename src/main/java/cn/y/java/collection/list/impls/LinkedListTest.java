package cn.y.java.collection.list.impls;

import java.util.LinkedList;

public class LinkedListTest {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        // 从头部添加
        list.addFirst(1);
        list.addFirst(2);
        // 从尾部添加
        list.addLast(9);
        list.addLast(8);
        System.out.println(list);

        // 获取头部的元素
        System.out.println(list.getFirst());
        // 获取尾部的元素
        System.out.println(list.getLast());

        // 删除头部的元素
        list.removeFirst();

        // 删除尾部的元素
        list.removeLast();
        System.out.println(list);
    }
}
