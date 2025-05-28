package org.example.reference;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.function.Consumer;

public class MethodRefTest {

    @Test
    public void testInstanceMethod() {
        Consumer<String> consumer = s -> System.out.println(s);
        System.out.println(consumer);

        // 使用PrintStream的实例方法println代替
        Consumer<String> consumer1 = System.out::println;
        System.out.println(consumer1);
    }

    @Test
    public void testStaticMethod() {
        Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o1, o2);
        System.out.println(comparator.compare(1, 2));

        // 使用Integer的静态方法compare代替
        Comparator<Integer> comparator1 = Integer::compare;
        System.out.println(comparator1.compare(3, 4));
    }

    @Test
    public void testClassInstanceMethod() {
        Comparator<String> comparator = (s1, s2) -> s1.compareTo(s2);
        System.out.println(comparator.compare("aaa", "bbb"));

        /*
            在方法参数不同，返回值相同的话
            如果具体逻辑是第一个参数调用方法并传递第二个参数
            那么就可以使用第一个参数的'类::实例方法'代替
         */
        Comparator<String> comparator1 = String::compareTo;
        System.out.println(comparator1);
    }
}
