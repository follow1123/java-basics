package org.example;

import org.junit.jupiter.api.Test;

public class ApisTest {

    @Test
    public void testString() {
        // 字符串前后添加缩进
        String s = "1234";
        String s1 = "    code";
        String indentLeft = s.indent(4);
        System.out.println(indentLeft.length());
        System.out.println(indentLeft.startsWith("    "));
        String indentRight = s1.indent(-4);
        System.out.println(indentRight.length());
        System.out.println(indentRight.startsWith("    "));

        // 使用transform方法将字符串转大写
        String transform = s1.transform(String::toUpperCase);
        System.out.println(transform);
    }
}
