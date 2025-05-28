package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class ApisTest {

    @Test
    public void testOptional() {
        Optional<Integer> optional = Optional.of(1);
        // Optional转换为Stream
        optional.stream().forEach(System.out::println);

        // or方法直接指定Optional类型的默认值
        Optional.ofNullable(null)
                .or(() -> Optional.of(100))
                .ifPresent(System.out::println);

        // 使用Lambda表达式指定逻辑
        optional.ifPresentOrElse(v -> {
            System.out.println("获取到值：" + v);
        }, () -> {
            System.out.println("没有值");
        });
    }

    @Test
    public void testCollections() {
        List<Integer> intList = List.of(1, 2, 3);
        // 更新或删除会报错UnsupportedOperationException
        // intList.add(4);
        // intList.remove(0);
        intList.forEach(System.out::println);

        Set<Integer> intSet = Set.of(1, 2, 3);
        // intSet.add(4);
        // intSet.remove(0);

        Map<String, Object> userMap = Map.of("name", "zs", "age", 18);
        // userMap.put("gender", true);
        // userMap.remove("name");
    }

    @Test
    public void testStream() {
        // Stream.of(null);之前传入null会报错
        Stream.ofNullable(null);

        // 第二个参数是限制条件
        Stream.iterate(0, i -> i < 100, i -> i + 1).forEach(System.out::println);
        // 从第一个元素还是判断，满足条件的元素会保留，当遇到一个不满足条件的元素时，后面的元素都丢弃
        Stream.of(1, 2, 3, 4, 5)
                .takeWhile(i -> i < 4)
                .forEach(System.out::println);
        // 从第一个元素还是判断，满足条件的元素会丢弃，当遇到一个不满足条件的元素时，后面的元素都保留
        Stream.of(1, 2, 3, 4, 5)
                .dropWhile(i -> i < 2)
                .forEach(System.out::println);
    }
}
