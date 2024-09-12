package cn.y.java.exceptions;

import org.junit.jupiter.api.Test;
import java.util.Date;

public class ExceptionTest {

    /**
     * 测试ArrayIndexOutOfBoundsException
     */
    @Test
    public void testArrayIndexOutOfBoundsException() {
        int[] ints = new int[4];
        System.out.println(ints[4]);
    }

    /**
     * 测试NullPointerException
     */
    @Test
    public void testNullPointerException() {
        String name = "zs";
        name = null;
        System.out.println(name.length());
    }

    /**
     * 测试ClassCastException
     */
    @Test
    public void testClassCastException() {
        Object o = new Object();
        Date date = (Date) o;
        System.out.println(date);
    }

    /**
     * 测试NumberFormatException
     */
    @Test
    public void testNumberFormatException() {
        String number = "132abc";
        Integer integer = Integer.valueOf(number);
        System.out.println(integer);
    }

    /**
     * 测试ArithmeticException
     */
    @Test
    public void testArithmeticException() {
        System.out.println(10 / 0);
    }

    /**
     * 测试ClassNotFoundException编译时异常
     */
    @Test
    public void testClassNotFoundException() {
        // Class.forName("a.b.c").var
    }

    /**
     * 测试FileNotFoundException和IOException编译时异常
     */
    @Test
    public void testFileNotFoundExceptionAndIOException() {
        // FileNotFoundException
        // FileInputStream fis = new FileInputStream("a.txt");
        // IOException
        // fis.close();
    }

}
