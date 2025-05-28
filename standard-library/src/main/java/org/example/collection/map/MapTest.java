package org.example.collection.map;

import java.util.*;

public class MapTest {

    public void testAddNull() {
        HashMap map = new HashMap();
        map.put(null, null);

        System.out.println(map);

        Hashtable hashtable = new Hashtable();
        // 添加是key或value值为null都会报错
        // hashtable.put(null, "value");
        // hashtable.put("key", null);
        System.out.println(hashtable);

    }

    public void testAdd() {
        HashMap map = new HashMap();
        map.put("aa", 1);
        map.put("bb", 2);

        System.out.println(map);

        HashMap m = new HashMap();
        m.put("cc", 3);
        m.put("dd", 4);

        map.putAll(m);
        System.out.println(map);
    }

    public void testDelete() {
        HashMap map = new HashMap();
        map.put("aa", 1);
        map.put("bb", 2);
        System.out.println(map);

        Object removedValue = map.remove("aa");
        System.out.println(removedValue);
        System.out.println(map);
    }

    public void testUpdate() {
        HashMap map = new HashMap();
        map.put("aa", 1);
        map.put("bb", 2);
        map.put("cc", 3);
        System.out.println(map);

        // put方法在map内已经存在相同的key时就是修改这个key的值
        map.put("bb", 10);
        System.out.println(map);

        HashMap m = new HashMap();
        m.put("cc", 20);
        m.put("dd", 4);

        // putAll时如果存在相同的key则修改，不存在就添加
        map.putAll(m);
        System.out.println(map);
    }

    public void testGet() {
        HashMap map = new HashMap();
        map.put("aa", 1);
        map.put("bb", 2);
        map.put("cc", 3);

        System.out.println(map.get("aa"));
        System.out.println(map.get("cc"));
    }

    public void testIterate() {
        HashMap map = new HashMap();
        map.put("aa", 1);
        map.put("bb", 2);
        map.put("cc", 3);

        // 使用增强for循环遍历map中所有的key
        for (Object key : map.keySet()) {
            System.out.println(key);
        }

        System.out.println("-------------");
        // 使用迭代器遍历map中的所有value
        Collection values = map.values();
        Iterator valIterator = values.iterator();
        while (valIterator.hasNext()) {
            System.out.println(valIterator.next());
        }

        System.out.println("-------------");
        // 使用迭代器遍历map中的Entry并获取里面对应的key-value
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry next = (Map.Entry) iterator.next();
            System.out.println("next.getKey() = " + next.getKey());
            System.out.println("next.getValue() = " + next.getValue());
        }

        System.out.println("-------------");

        // 根据keySet遍历所有的key-value
        Set keySet = map.keySet();
        for (Object key : keySet) {
            System.out.println("key = " + key);
            System.out.println("map.get(key) = " + map.get(key));
        }
    }

    public void testOthers() {
        HashMap map = new HashMap();
        map.put("aa", 1);
        map.put("bb", 2);
        map.put("cc", 3);

        System.out.println(map);

        // map的大小，就是entrySet的长度
        System.out.println(map.size());

        // 判断map中key为aa的数据
        System.out.println(map.containsKey("aa"));

        // 判断map中是否存在value为4的数据
        System.out.println(map.containsValue(4));

        // 清空map
        map.clear();

        System.out.println(map);
    }
}
