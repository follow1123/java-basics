package org.example.generics;

import java.util.*;

public class GenericsTest {
    /**
     * 没有泛型时的问题
     */
    public void testNoGenerics() {
        ArrayList list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);

        // 如果添加其他类型的元素后，后面就会报错
        // list.add("123");
        for (Object o : list) {
            // 在遍历集合内元素时需要强制转换后才能进行特殊操作
            Integer i = (Integer) o;
            System.out.println(i + 1);
        }
    }

    /**
     * 使用泛型后解决的问题
     */
    public void testWithGenerics() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        // 此时添加其他类型的元素，编译就不通过
        // list.add("1234")
        for (Integer i : list) {
            // 进行特殊操作时不需要强转
            System.out.println(i + 1);
        }
    }

    /**
     * 集合配合泛型使用
     */
    public void testGenericsList() {
        ArrayList<String> list = new ArrayList<>();

        // 添加
        list.add("a");
        list.add("b");

        // 获取，直接获取指定类型对象，无需强转
        String s = list.get(0);

        // 遍历
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            System.out.println(str);
        }

        for (String string : list) {
            System.out.println(string);
        }
    }

    /**
     * Map配合泛型使用
     */
    public void testGenericsMap() {
        HashMap<String, Integer> map = new HashMap<>();

        // 添加
        map.put("a", 1);
        map.put("b", 2);

        // 获取
        Integer integer = map.get("a");

        // 遍历
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = entries.iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Integer> next = iterator.next();
            String key = next.getKey();
            Integer value = next.getValue();
            System.out.println("key = " + key);
            System.out.println("value = " + value);
        }
    }

    /**
     * 测试自然排序
     */
    public void testCompare() {

        ArrayList<User> users = new ArrayList<>();

        users.add(new User("zs", 11));
        users.add(new User("ls", 21));
        users.add(new User("ww", 15));
        users.add(new User("zl", 32));

        System.out.println(users);
        // 默认根据年龄排序
        Collections.sort(users);

        System.out.println(users);
    }

    /**
     * 测试定制排序
     */
    public void testComparator() {

        ArrayList<User> users = new ArrayList<>();

        users.add(new User("zs", 11));
        users.add(new User("ls", 21));
        users.add(new User("zw", 21));
        users.add(new User("ww", 15));
        users.add(new User("zl", 32));

        System.out.println(users);

        // 定制排序，先根据年龄降序排序，再根据姓名排序
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if (o1 == o2) return 0;

                int compare = Integer.compare(o1.getAge(), o2.getAge());
                if (compare != 0) {
                    return -compare;
                }
                return o1.getName().compareTo(o2.getName());
            }
        });

        System.out.println(users);
    }
}
