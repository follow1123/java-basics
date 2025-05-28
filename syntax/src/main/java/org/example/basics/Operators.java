package org.example.basics;

/**
 * 运算符
 */
public class Operators {
    /**
     * 算数运算符
     */
    public static void testArithmeticOperators() {
        // 正号
        System.out.println(+3); // 3
        // 负号
        int i1 = 4;
        System.out.println(-i1); // -4
        // 加
        System.out.println(5 + 5); // 10
        // 减
        System.out.println(6 - 4); // 2
        // 乘
        System.out.println(3 * 4); // 12
        // 除
        System.out.println(5 / 5); // 1
        // 取模（取余）
        System.out.println(7 % 5); // 2
        // 自增（前）：先运算后取值
        int i2 = 2;
        int i3 = ++i2;
        System.out.println(i2); // 3
        System.out.println(i3); // 3
        // 自增（后）：先取值后运算
        int i4 = 2;
        int i5 = i4++;
        System.out.println(i4); // 3
        System.out.println(i5); // 2
        // 自减（前）：先运算后取值
        int i6 = 2;
        int i7 = --i6;
        System.out.println(i6); // 1
        System.out.println(i7); // 1
        // 自减（后）：先取值后运算
        int i8 = 2;
        int i9 = i8--;
        System.out.println(i8); // 1
        System.out.println(i9); // 2
        // 字符串连接
        System.out.println("He" + "llo"); // Hello
    }

    /**
     * 赋值运算符
     */
    public static void testAssignmentOperators() {
        // 将5赋值给变量i1
        int i1 = 5;
        i1 += 1; // 等同于 i1 = i1 + 1
        System.out.println(i1); // 6
        i1 -= 1; // 等同于 i1 = i1 - 1
        System.out.println(i1); // 5
        i1 *= 2; // 等同于 i1 = i1 * 2
        System.out.println(i1); // 10
        i1 /= 2; // 等同于 i1 = i1 / 2
        System.out.println(i1); // 5
        i1 %= 2; // 等同于 i1 = i1 % 2
        System.out.println(i1); // 1
        // 其他类似
    }

    /**
     * 比较运算符
     */
    public static void testComparisonOperators() {
        System.out.println(4 == 3); // false
        System.out.println(4 != 3); // true
        System.out.println(4 < 3); // false
        System.out.println(4 > 3); // true
        System.out.println(4 <= 3); // false
        System.out.println(4 >= 3); // true
        System.out.println("Hello" instanceof String); // true
    }

    /**
     * 逻辑运算符
     */
    public static void testLogicalOperators() {
        boolean a1 = true;
        boolean b1 = true;
        System.out.println(a1 & b1); // true
        System.out.println(a1 && b1); // true
        System.out.println(a1 | b1); // true
        System.out.println(a1 || b1); // true
        System.out.println(!a1); // false
        System.out.println(a1 ^ b1); // false
        System.out.println("-----------");
        boolean a2 = true;
        boolean b2 = false;
        System.out.println(a2 & b2); // false
        System.out.println(a2 && b2); // false
        System.out.println(a2 | b2); // true
        System.out.println(a2 || b2); // true
        System.out.println(!a2); // false
        System.out.println(a2 ^ b2); // true
        System.out.println("-----------");
        boolean a3 = false;
        boolean b3 = true;
        System.out.println(a3 & b3); // false
        System.out.println(a3 && b3); // false
        System.out.println(a3 | b3); // true
        System.out.println(a3 || b3); // true
        System.out.println(!a3); // true
        System.out.println(a3 ^ b3); // true
        System.out.println("-----------");
        boolean a4 = false;
        boolean b4 = false;
        System.out.println(a4 & b4); // false
        System.out.println(a4 && b4); // false
        System.out.println(a4 | b4); // false
        System.out.println(a4 || b4); // false
        System.out.println(!a4); // true
        System.out.println(a4 ^ b4); // false
        // &和&&，|和||之间的关系
        int i = 3;
        boolean bool = true;
        if (bool & (++i > 3)) {
            System.out.println();
        }
        System.out.println(i); // 4

        i = 3;
        bool = false;
        if (bool && (++i > 3)) {
            System.out.println();
        }
        System.out.println(i); // 3

        i = 3;
        bool = true;
        if (bool | (++i > 3)) {
            System.out.println();
        }
        System.out.println(i); // 4

        i = 3;
        bool = true;
        if (bool || (++i > 3)) {
            System.out.println();
        }
        System.out.println(i); // 3
    }

    /**
     * 位运算符
     */
    public static void testBitwiseOperators() {
        // 左移
        System.out.println(11 << 2); // 44
        System.out.println(-11 << 2); // -44
        // 右移
        System.out.println(11 >> 2); // 2
        System.out.println(-11 >> 2); // -3

       /*
       无符号右移
                                 11110101 b1 -11
                                 11111111 & 0xFF
      00000000 00000000 00000000 11110101 i1
      00000000 00000000 00000000 00111101 61
        */
        byte b1 = -11;
        int i1 = b1 & 0xFF;
        i1 >>>= 2;
        System.out.println(i1); // 61

       /*
       00001011
       00010011
       00000011 3

       00001011
       00010011
       00011011 27

       00001011
       00010011
       00011000 24

       00001011
       11110100
       10001011
       10001100 -12

        */
        System.out.println(11 & 19); // 3
        System.out.println(11 | 19); // 27
        System.out.println(11 ^ 19); // 24
        System.out.println(~11); // -12
    }

    /**
     * 条件运算符（三元运算符）
     */
    public static void testConditionalOperators() {
        String a = "Hello";
        String b = "Wrold!";
        boolean c = true;
        System.out.println(c ? a : b); // Hello
    }

}
