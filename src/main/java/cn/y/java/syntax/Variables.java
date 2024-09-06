package cn.y.java.syntax;

import org.junit.jupiter.api.Test;

/**
 * 变量
 */
public class Variables {

    @Test
    public void testVariables() {
        boolean result = true;

        char c1 = 'C';
        char c2 = 65; // 等同于A
        char c3 = '\u221a'; // 等同于√
        char c4 = '\t'; // 制表符

        byte b = 100;
        short s = 10000;
        int i = 100000;

        // 数值较大可以使用下划线分割
        long creditCardNumber = 1234_5678_9012_3456L;
        long socialSecurityNumber = 999_99_9999L;
        float pi =  3.14_15F;
        long hexBytes = 0xFF_EC_DE_5E; // 十六进制表示
        long hexWords = 0xCAFE_BABE;
        long maxLong = 0x7fff_ffff_ffff_ffffL;
        byte nybbles = 0b0010_0101; // 二进制表示
        long bytes = 0b11010010_01101001_10010100_10010010;
        double d = 1.797E308; // 科学计数法表示
    }

    /**
     * 测试精度问题
     */
    @Test
    public void testFloat() {
        System.out.println(0.1 + 0.2); // 0.30000000000000004

        float ff1 = 123123123F;
        float ff2 = ff1 + 1;
        System.out.println(ff1); // 1.2312312E8
        System.out.println(ff1); // 1.2312312E8
        System.out.println(ff1 == ff2); // true
    }

    /**
     * 测试自动类型提升
     */
    @Test
    public void testAutoTypePromotion(){
        int i1 = 100;
        long l1 = i1;
        float f1 = l1;
        double d1 = f1;

        
        byte b1 = 1;
        // byte b2 = b1 + 1; // 编译不通过
        int i2 = b1 + 1;

        short s1 = 434;
        short s2 = 4543;
        // short s3 = s1 + s2; // 编译不通过

        char c1 = 'a';
        int i3 = c1 + 1; // 98
    }

    /**
     * 测试强制类型转换
     */
    @Test
    public void testTypeConversion(){
        long l1 = 34;
        // int i1 = l1; // 编译不通过
        int i1 = (int) l1;

        float f1 = 1.9F;
        int i2 = (int) f1; // 1

        long l2 = 128;
        byte b1 = (byte) l2; // -128

        long l3 = -130;
        byte b2 = (byte) l3; // 126
    }
}
