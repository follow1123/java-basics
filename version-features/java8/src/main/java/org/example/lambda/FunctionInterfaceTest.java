package org.example.lambda;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionInterfaceTest {

    @Test
    public void testConsumer() {
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("123412");
    }

    @Test
    public void testSupplier() {
        Supplier<Integer> supplier = () -> 1;
        System.out.println(supplier.get());
    }

    @Test
    public void testFunction() {
        Function<String, Integer> function = s -> Integer.parseInt(s);
        System.out.println(function.apply("100"));
    }

    @Test
    public void testPredicate() {
        Predicate<Integer> predicate = i -> i > 100;
        System.out.println(predicate.test(30));
    }
}
