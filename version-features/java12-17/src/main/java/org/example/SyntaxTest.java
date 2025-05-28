package org.example;

import org.junit.jupiter.api.Test;

public class SyntaxTest {
    @Test
    public void testSwitchExpression() {
        int i = 0;
        String a = switch (i) {
            case 1, 2 -> "case 1";
            case 3, 4 -> "case 2";
            default -> {
                System.out.println("123");
                // yield表示在代码块中的返回值，相当于方法里面的return
                yield "case default";
            }
        };
        System.out.println(a);
    }

    @Test
    public void testTextBlock() {
        String s = """
                12321
                123123ad
                qweqwe
                """;
        System.out.println(s);

        // 文本块内使用'\'取消换行
        String s2 = """
                1 2 \
                3
                a b \
                c""";
        System.out.println(s2);
    }

    @Test
    public void testInstanceof() {
        Object o = "aaa";
        if (o instanceof String str) {
            System.out.println(str);
        }
    }
}
