package org.example.stream;

import org.example.stream.data.User;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class TerminateTest {

    @Test
    public void testMatchAndFind() {
        List<User> users = User.listUser();
        // 匹配所有用户的年龄是否都大于20岁
        System.out.println(users.stream().allMatch(u -> u.getAge() > 20));
        // 匹配是否有一个用户的年龄大于20岁
        System.out.println(users.stream().anyMatch(u -> u.getAge() > 20));
        // 匹配是否没有大于50岁的用户
        System.out.println(users.stream().noneMatch(u -> u.getAge() > 50));

        // 获取流内的第一个元素
        System.out.println(users.stream().findFirst().get());
        // 随机获取流内的一个元素
        System.out.println(users.stream().findAny().get());

        // 获取年龄大于40岁的用户的个数
        System.out.println(users.stream().filter(u -> u.getAge() > 30).count());

        // 获取年龄最大或最小的用户
        System.out.println(users.stream().max((u1, u2) -> Integer.compare(u1.getAge(), u2.getAge())).get());
        System.out.println(users.stream().min((u1, u2) -> Integer.compare(u1.getAge(), u2.getAge())).get());
        // users.stream().max(Comparator.comparing(User::getAge))

        // 遍历集合
        users.stream().forEach(System.out::println);
    }

    @Test
    public void testReduce() {
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        /*
         累计集合内的元素
         第一个参数是初始值
         */
        System.out.println(ints.stream().reduce(0, (i1, i2) -> i1 + i2));
        System.out.println(ints.stream().reduce(10, (i1, i2) -> i1 + i2));
        // 使用方法引用代替
        System.out.println(ints.stream().reduce(10, Integer::sum));

        List<User> users = User.listUser();

        // 计算年龄总和
        Optional<Integer> reduce = users.stream().map(User::getAge).reduce(Integer::sum);
        System.out.println(reduce);
    }

    @Test
    public void testCollect() {
        List<User> users = User.listUser();
        // 获取用户的年龄都大于20岁的，并存入另一个集合内
        List<User> youngerUsers = users.stream().filter(u -> u.getAge() < 20).collect(Collectors.toList());
        System.out.println(youngerUsers);

        // 根据id转换为Map
        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(User::getId, u -> u));
        Set<Map.Entry<Integer, User>> entries = userMap.entrySet();
        for (Map.Entry<Integer, User> entry : entries) {
            System.out.printf("%s=%s\n", entry.getKey(), entry.getValue());
        }

        // 求平均年龄
        Double avgAge = users.stream().collect(Collectors.averagingInt(User::getAge));
        System.out.println(avgAge);

        // 根据性别分组
        Map<Boolean, List<User>> usersGroupByGender = users.stream().collect(Collectors.groupingBy(User::getGender));
        Set<Map.Entry<Boolean, List<User>>> entries1 = usersGroupByGender.entrySet();
        for (Map.Entry<Boolean, List<User>> entry : entries1) {
            System.out.printf("%s=%s\n", entry.getKey(), entry.getValue());
        }
    }

}
