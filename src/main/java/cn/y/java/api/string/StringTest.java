package cn.y.java.api.string;

import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;

public class StringTest {


    @Test
    public void testStringTable() {
        String s1 = "hello"; // 使用字面量声明
        String s2 = "hello";

        System.out.println(s1 == s2); // true
    }
    
    @Test
    public void testImmutable1() {
        String s1 = "hello";
        String s2 = "hello";
        s2 = "a";

        System.out.println(s1); // hello
    }

    @Test
    public void testImmutable2() {
        String s1 = "hello";
        String s2 = "hello";
        s2 += "world";

        System.out.println(s1); // hello
    }

    @Test
    public void testImmutable3() {
        String s1 = "hello";
        String s2 = "hello";
        String s3 = s1.replace("l", "w");


        System.out.println(s1); // hello
        System.out.println(s2); // hello
        System.out.println(s3); // hewwo
    }

    @Test
    public void testInstance() {
        String s1 = "hello";
        String s2 = "hello";
        String s3 = new String("hello");
        String s4 = new String("hello");

        System.out.println(s1 == s2); // true
        System.out.println(s1 == s3); // false
        System.out.println(s1 == s4); // false
        System.out.println(s3 == s4); // false

        System.out.println(s1.equals(s3)); // true
    }

    @Test
    public void testStringConnect() {
        String s1 = "hello";
        String s2 = "world";

        String s3 = "helloworld";
        String s4 = "hello" + "world";
        String s5 = s1 + "world";
        String s6 = "hello" + s2;
        String s7 = s1 + s2;

        System.out.println(s3 == s4); // true
        System.out.println(s3 == s5); // false
        System.out.println(s3 == s6); // false
        System.out.println(s3 == s7); // false
        System.out.println(s5 == s6); // false
        System.out.println(s5 == s7); // false

        String s8 = s5.intern(); // intern方法返回字符串常量池内的字面量值
        System.out.println(s3 == s8); // true

        String s9 = s1.concat(s2);
        System.out.println(s3 == s9); // false

    }
    
    @Test
    public void testStringConstructor() {

        // 新建空字符串
        String s1 = new String();
        System.out.println(s1); // ""

        // 使用已有的字符串新建字符串
        String s2 = new String(s1); // ""
        System.out.println(s2); // ""

        // 通过char数组新建字符串
        String s3 = new String(new char[]{'h', 'e', 'l', 'l', 'o'});
        System.out.println(s3); // hello

        // 通过char数组新建字符串，并指定使用char数组的哪些部分创建
        String s4 = new String(new char[]{'h', 'e', 'l', 'l', 'o'}, 0, 2);
        System.out.println(s4); // he

        // 通过byte数组新建字符串
        String s5 = new String(new byte[]{97, 98, 99, 100, 101, 102});
        System.out.println(s5); // abcdef

        // 通过byte数组新建字符串，并指定字符集
        String s6 = new String(new byte[]{97, 98, 99, 100, 101, 102}, Charset.defaultCharset());
        System.out.println(s6); // abcdef
    }
}
