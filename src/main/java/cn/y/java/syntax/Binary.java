package cn.y.java.syntax;

import org.junit.jupiter.api.Test;

/**
 * 进制
 */
public class Binary {

    @Test
    public void testNumber() {
        int n1 = 2134; // 十进制
        int n2 = 0b1011; // 二进制
        int n3 = 033; // 八进制
        int n4 = 0xfa33; // 十六进制

        System.out.println(n1);
        System.out.println(n2);
        System.out.println(n3);
        System.out.println(n4);
    }

}
