package cn.y.java.syntax;

/**
 * 字符串
 */
public class StringDataType {
    public static void main(String[] args) {
        int i1 = 234;
        float f1 = 34.55F;
        boolean b1 = true;
        String s1 = "str";

        String s2 = s1 + i1; // str234
        String s3 = s1 + f1; // str34.55
        String s4 = s1 + b1; // strtrue

        String s5 = i1 + f1 + s1; // 268.55str

        String s6 = s1 + f1 + i1; // str34.55234
    }
}
