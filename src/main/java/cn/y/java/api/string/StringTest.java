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
        System.out.println("字符串转charArray，再还原成字符串");
        // 获取字符串内的char数组
        String s1 = "hello";
        char[] charArray = s1.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            System.out.println(charArray[i]);
        }
        String s2 = new String(charArray);
        System.out.println(s2);

        System.out.println("字符串转byteArray，再还原成字符串");
        // 获取字符串内的byte数组
        String s3 = "abc测试";

        System.out.println("使用默认utf-8编码");
        byte[] bytes = s3.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(bytes[i]);
        }

        System.out.println("使用gbk编码");
        // 获取字符串内的byte数组
        byte[] gbkBytes = s3.getBytes(Charset.forName("gbk"));
        for (int i = 0; i < gbkBytes.length; i++) {
            System.out.println(gbkBytes[i]);
        }

        System.out.println("使用gbk解码");
        // 解码
        System.out.println(new String(gbkBytes, Charset.forName("gbk")));

    }

    @Test
    public void testStringMethods() {
        // 是否为空字符串
        String s1 = new String();
        System.out.println(s1.isEmpty()); // true

        // 获取字符串长度
        String s2 = "hello";
        System.out.println(s2.length()); // 5

        // 拼接字符串
        System.out.println(s2.concat(" world")); // hello world

        String s3 = "Hello";

        // 判断两个字符串是否相同，忽略大小写进行比较
        System.out.println(s2.equals(s3)); // false
        System.out.println(s2.equalsIgnoreCase(s3)); // true

        // 比较字符串大小，忽略大小写进行比较
        String s4 = "abc";
        String s5 = "def";
        String s6 = "aBc";
        /*
        abc和def
        先比较第一位
        a 的ASCII码是97
        d 的ASCII码是100
        97 - 100 = -3
         */
        System.out.println(s4.compareTo(s5)); // -3
        /*
        abc和aBc
        先比较第一位，两个一样
        比较第二个
        b 98
        B 66
        98 - 66 = 32
         */
        System.out.println(s4.compareTo(s6)); // 32
        System.out.println(s4.compareToIgnoreCase(s6)); // 0

        // 大小写转换
        String s7 = "ABC";
        String s8 = s7.toLowerCase();
        System.out.println(s8); // abc
        String s9 = s8.toUpperCase();
        System.out.println(s9); // ABC

        // 去除前后空格
        String s10 = "  abc ";
        System.out.println(s10.length()); // 6
        System.out.println(s10.trim().length()); // 3

        // 获取常量池内的共享值
        String s11 = "aaa";
        String s12 = new String("aaa");
        System.out.println(s11 == s12); // false
        System.out.println(s11 == s12.intern()); // true

        // 当前字符串是否包含某个字符串
        String s13 = "abcdef";
        System.out.println(s13.contains("de")); // true

        // 在当前字符串中查找某个字符串，如果有则返回第一个匹配的下标
        System.out.println(s13.indexOf("b")); // 1
        System.out.println(s13.indexOf("b", 2)); // -1

        // 和上面一样，只不过查找的方向相反
        System.out.println(s13.lastIndexOf("e")); // 4
        System.out.println(s13.lastIndexOf("e", 3)); // -1

        // 截取字符串，包含起始下标，不包含结束下标
        String s14 = "hello world!";
        System.out.println(s14.substring(6)); // world!
        System.out.println(s14.substring(4, 7)); // o w

        // 获取指定下标的字符
        String s15 = "helloworld";
        System.out.println(s15.charAt(3)); // l

        // 将charArray转换为字符串，valueOf和copyValueOf底层实现是一样的
        char[] chars1 = {'h', 'e', 'l', 'l', 'o'};
        String s16 = String.valueOf(chars1, 0, 3);
        System.out.println(s16); // hel
        String s17 = String.copyValueOf(chars1);
        System.out.println(s17); // hello

        // 判断字符串是否以某个字符串开头或结尾
        String s18 = "abc";
        System.out.println(s18.startsWith("a")); // true
        System.out.println(s18.endsWith("a")); // false

        // 替换字符串
        String s19 = "hello world";
        System.out.println(s19.replace('o', 'e')); // helle world
        System.out.println(s19.replace("ll", "")); // heo world
        System.out.println(s19.replaceAll("o", "1")); // hell1 w1rld

    }
}
