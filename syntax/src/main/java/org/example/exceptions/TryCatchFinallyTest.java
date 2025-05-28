package org.example.exceptions;

public class TryCatchFinallyTest {
    public void testTryCatch() {
        String s = "123abc";
        try {
            Integer integer = Integer.valueOf(s);
            System.out.println(integer);
        } catch (NumberFormatException e) {
            System.out.println("程序出错");
        } catch (NullPointerException e) { // NumberFormatException与NullPointerException异常是并列关系，所以捕获的顺序无所谓
            System.out.println("程序出错");
        } catch (RuntimeException e) { // RuntimeException是上面两个异常的父类，必须放在最后，如果放在第一个，则表示把子类异常一起捕获了
            System.out.println("运行时异常");
        }
        System.out.println("程序结束");
    }

    public void testTryCatchAndPrintStackTrace() {
        String s = "123abc";
        try {
            Integer integer = Integer.valueOf(s);
            System.out.println(integer);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        System.out.println("程序结束");
    }

    private int strToInt(String str) {
        try {
            Integer integer = Integer.valueOf(str);
            return integer;
        } catch (NumberFormatException e) {
            return -1;
        } finally {
            System.out.println("程序执行完成");
        }
    }

    public void testTryCatchFinally() {
        System.out.println(strToInt("123abc"));
    }
}

