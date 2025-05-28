package org.example.stream;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class InitTest {

    @Test
    public void testInitByCollection() {
        ArrayList<String> list = new ArrayList<>();
        // 顺序流
        Stream<String> stream = list.stream();

        // 并行流
        Stream<String> stringStream = list.parallelStream();
    }

    @Test
    public void testInitByArray() {
        Integer[] integers = {23, 324, 1234, 234};
        Stream<Integer> stream = Arrays.stream(integers);
    }

    @Test
    public void testInitByStaticMethod() {
        Stream<Integer> integerStream = Stream.of(324, 324, 8790, 4543);
    }
}
