package org.example;

import org.junit.jupiter.api.Test;

public class SwitchTest {

    @Test
    public void testPattern() {
        Object o = 100;

        String result = switch (o) {
            case String s -> "2134";
            case Integer i -> String.valueOf(i);
            case null -> "null";
            default -> "err";
        };
        System.out.println(result);
    }

    @Test
    public void testWhenKeyword() {
        Object o = 100;

        String result = switch (o) {
            case Integer i when i >= 100 -> "A";
            case String s -> s;
            case null -> "C";
            default -> "D";
        };
        System.out.println(result);
    }
}
