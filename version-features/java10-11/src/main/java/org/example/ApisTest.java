package org.example;

import org.junit.jupiter.api.Test;

import java.util.Optional;

public class ApisTest {

    @Test
    public void testOptional() {
        Optional<Object> optional = Optional.empty();
        // 判断Optional内的值是否为空
        System.out.println(optional.isEmpty());
    }

    @Test
    public void testString() {
        // 非空判断
        String s = "";
        String s1 = " ";

        System.out.println(s.isEmpty()); // true
        System.out.println(s.isBlank()); // true
        System.out.println(s1.isEmpty()); // false
        System.out.println(s1.isBlank()); // true

        // 字符串重复
        String s2 = "a";
        System.out.println(s2.repeat(3)); // aaa

        // 获取字符串内的每行组成一个Stream
        String s3 = "a\nb\nc";
        s3.lines().forEach(System.out::println);

        // 去除字符串首位空格
        String s4 = " a b c d ";
        System.out.println(s4.strip().length()); // 7
        System.out.println(s4.stripLeading().length()); // 8
        System.out.println(s4.stripTrailing().length()); // 8
    }
}
