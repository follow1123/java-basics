package org.example.reference;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.function.Function;
import java.util.function.Supplier;

public class ConstructorRefTest {

    @Test
    public void test() {
        // 无参数构造器
        Supplier<String> supplier = String::new;
        // 相当于调用new String()
        String s = supplier.get();

        // 有参数构造器
        Function<Long, Date> function = Date::new;
        // 相当于调用new Date(1000L)
        Date date = function.apply(1000L);
    }

    @Test
    public void testArrayRef() {
        Function<Integer, String[]> function = String[]::new;

        // 相当于调用new String[10]
        String[] strArr = function.apply(10);
        System.out.println(strArr.length);
    }
}
