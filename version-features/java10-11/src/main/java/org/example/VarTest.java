package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class VarTest {

    // 无法定义成员变量
    // var a = 10;

    // 无法定义静态变量
    // static var b = "abc";

    @Test
    public void test() {
        // 类型推断
        var list = new ArrayList<>();

        // 无法推断lambda表达式对应的函数式接口
        // var runnable = () -> System.out.println("runnable");
    }
}
