package org.example.stream;

import org.example.stream.data.Role;
import org.example.stream.data.User;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class OperateTest {

    @Test
    public void testFilterSlice() {
        List<User> users = User.listUser();

        // 过滤，查询用户年龄小于20岁的用户
        users.stream().filter(user -> user.getAge() < 20).forEach(System.out::println);

        // 截断，只显示前2个元素
        users.stream().limit(2).forEach(System.out::println);

        // 跳过，跳过前4条数据
        users.stream().skip(4).forEach(System.out::println);

        // 去重，需要对象重写equals()和hashCode()方法
        users.stream().distinct().forEach(System.out::println);
    }

    @Test
    public void testMap() {
        List<String> list = Arrays.asList("a", "b", "c", "d");

        // 小写转大写
        list.stream().map(String::toUpperCase).forEach(System.out::println);

        List<User> users = User.listUser();

        // 过滤，查询用户年龄小于20岁的用户，只显示名称
        users.stream().filter(user -> user.getAge() < 20).map(User::getName).forEach(System.out::println);
        // 只显示用户的id
        users.stream().mapToInt(User::getAge).forEach(System.out::println);

        Role admin = new Role("001", "admin");
        Role test = new Role("002", "test");
        Role dba = new Role("003", "dba");
        Role userRole = new Role("004", "user");
        users.get(0).setRoles(Arrays.asList(admin, test, dba, userRole));
        users.get(3).setRoles(Arrays.asList(test, dba));
        users.get(5).setRoles(Arrays.asList(userRole));

        /*
            获取用户的角色，并判断角色是否为空
            将角色列表转换为流后去重
            只获取角色名
         */
        users.stream().filter(u -> u.getRoles() != null)
                .flatMap(u -> u.getRoles().stream())
                .distinct().map(Role::getName).forEach(System.out::println);
    }

    @Test
    public void testSort() {
        Integer[] integers = {3, 34, 234, 423, 23};
        // 默认升序排序
        Arrays.stream(integers).sorted().forEach(System.out::println);
        Arrays.stream(integers).sorted(Integer::compareTo).forEach(System.out::println);
        // 数组内的元素还是原来的顺序，stream操作不会修改原容器内的元素
        System.out.println(Arrays.toString(integers));

        List<User> users = User.listUser();
        // 定制排序
        users.stream().sorted((u1, u2) -> u1.getName().compareTo(u2.getName())).forEach(System.out::println);
    }

}
