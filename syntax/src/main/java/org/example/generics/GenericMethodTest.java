package org.example.generics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericMethodTest {
    /**
     * 普通泛型方法
     */
    public <E> List<E> add(E[] arr) {
        List<E> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        return list;
    }

    /**
     * 静态多个参数的泛型方法
     */
    public static <K, V> Map<K, V> put(K k1, V v1, K k2, V v2) {
        Map<K, V> map = new HashMap<>();
        map.put(k1, v1);
        map.put(k2, v2);

        return map;
    }

    public void test() {
        // 指定泛型参数为Integer
        List<Integer> list = add(new Integer[]{1, 3, 4, 43,});
        System.out.println(list);

        // 指定泛型参数为String和Integer
        Map<String, Integer> map = put("a", 1, "b", 2);
        System.out.println(map);
    }
}
