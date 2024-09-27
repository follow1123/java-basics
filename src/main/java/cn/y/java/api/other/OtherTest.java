package cn.y.java.api.other;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Random;

public class OtherTest {

    @Test
    public void testSystem() {
        // 获取当前毫秒数
        System.out.println(System.currentTimeMillis());

        // 请求系统进行垃圾回收，看系统执行情况是否执行
        System.gc();

        // 获取系统属性
        System.out.println(System.getProperty("java.version")); // java版本
        System.out.println(System.getProperty("java.home")); // jdk路径
        System.out.println(System.getProperty("os.name")); // 系统名称
        System.out.println(System.getProperty("os.version")); // 系统版本
        System.out.println(System.getProperty("user.name")); // 用户名
        System.out.println(System.getProperty("user.home")); // 用户家目录
        System.out.println(System.getProperty("user.dir")); // 当前项目路径

        // 退出程序
        System.exit(0);
        System.out.println("exited"); // 这行将无法执行到
    }

    @Test
    public void testRuntime() {
        Runtime runtime = Runtime.getRuntime();

        // 获取JVM运行时相关数据
        System.out.println(runtime.totalMemory()); // 总内存，单位byte
        System.out.println(runtime.maxMemory()); // 最大内存，单位byte
        System.out.println(runtime.freeMemory()); // 可用内存，单位byte
    }

    @Test
    public void testMath() {
        // 数学运算操作
        // 绝对值
        System.out.println(Math.abs(-1));  // 1
        // 向上取整
        System.out.println(Math.ceil(1.3)); // 2
        // 向下取整
        System.out.println(Math.floor(1.9)); // 1
        // 四舍五入
        System.out.println(Math.round(1.4)); // 1
    }

    @Test
    public void testBigInteger() {
        // 不可变的任意精度整数
        BigInteger bi = BigInteger.valueOf(15);
        // 绝对值
        System.out.println(bi.abs()); // 15
        // 加
        System.out.println(bi.add(BigInteger.valueOf(10))); // 25
        // 减
        System.out.println(bi.subtract(BigInteger.valueOf(10))); // 5
        // 乘
        System.out.println(bi.multiply(BigInteger.valueOf(10))); // 150
        // 除
        System.out.println(bi.divide(BigInteger.valueOf(5))); // 3
        // 取余
        System.out.println(bi.remainder(BigInteger.valueOf(4))); // 3
    }

    @Test
    public void testBigDecimal() {
        // 任意精度的浮点数
        BigInteger bi = new BigInteger("12433241123");
        BigDecimal bd1 = new BigDecimal("12435.351");
        BigDecimal bd2 = new BigDecimal("11");

        System.out.println(bi);

        // HALF_UP表示除不尽就四舍五入
        System.out.println(bd1.divide(bd2, RoundingMode.HALF_UP));
        // 保留小数点后10位
        System.out.println(bd1.divide(bd2, 10, RoundingMode.HALF_UP));
    }

    @Test
    public void testRandom() {
        // 随机数
        Random random = new Random();
        // 随机boolean
        System.out.println(random.nextBoolean());

        // 随机byte数组
        byte[] bytes = new byte[10];
        random.nextBytes(bytes);
        System.out.println(Arrays.toString(bytes));

        // 随机double
        System.out.println(random.nextDouble());

        // 随机float
        System.out.println(random.nextFloat());

        // 随机高斯（正态）分布，中间数据较多
        System.out.println(random.nextGaussian());

        // 随机int
        System.out.println(random.nextInt());

        // 随机int，0-10（不包含10）
        System.out.println(random.nextInt(10));

        // 随机long
        System.out.println(random.nextLong());
    }
}
