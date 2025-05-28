package org.example.optional;

import org.junit.jupiter.api.Test;

import java.util.Optional;

public class OptionalTest {

    @Test
    public void testCreate() {
        String s1 = "aaa";
        String s2 = null;
        Optional<String> optional1 = Optional.of(s1);
        // 传入null值会报错，空指针异常
        // Optional<String> optional2 = Optional.of(s2);

        Optional<Object> emptyOptional = Optional.empty();

        // 可以为空
        Optional<String> optional3 = Optional.ofNullable(s1);
        Optional<String> optional4 = Optional.ofNullable(s2);
    }

    @Test
    public void testMethods() {
        Optional<String> optional = Optional.of("100");
        optional
                // 映射
                .map(Integer::parseInt)
                // 过滤
                .filter(i -> i > 50)
                // 获取，如果存在才执行指定的逻辑
                .ifPresent(System.out::println);

        // 判断值是否存在
        boolean present = optional.isPresent();
        // 直接获取值
        String s = optional.get();
        // 获取值，不存在则返回提供的默认值
        String s1 = optional.orElse("aa");
        // 获取值，不存在则指定指定逻辑返回对应值
        String s2 = optional.orElseGet(() -> 10 + "");
        // 获取值，不存在直接抛出异常
        String s3 = optional.orElseThrow(RuntimeException::new);
    }
}
