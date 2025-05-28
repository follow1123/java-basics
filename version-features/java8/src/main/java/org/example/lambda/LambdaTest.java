package org.example.lambda;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.function.Consumer;

public class LambdaTest {

    @Test
    public void testRunnable() {
        // 原生方式
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("run");
            }
        };
        runnable.run();

        // 使用lambda
        Runnable r = () -> System.out.println("run");
        r.run();
    }

    @Test
    public void testCompare() {
        // 原生方式
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        System.out.println(comparator.compare(1, 2));

        // 使用lambda
        Comparator<Integer> cpt1 = (o1, o2) -> Integer.compare(o1, o2);
        System.out.println(cpt1.compare(34, 534));

        // 使用方法引用
        Comparator<Integer> cpt2 = Integer::compare;
        System.out.println(cpt2.compare(52, 11));
    }

    @Test
    public void testNoArgs() {
        Runnable runnable = () -> {
            System.out.println("run");
        };

        // 只有一行语句时可以省略大括号
        Runnable runnable1 = () -> System.out.println("run");
    }

    @Test
    public void testOneArg() {
        Consumer<String> consumer = (s) -> System.out.println(s);

        // 只有一个参数时可以省略括号
        Consumer<String> consumer1 = s -> System.out.println(s);
    }
}
