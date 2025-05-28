package org.example.oop.wrapper;

public class WrapperTest {

    /**
     * 基本数据类型转包装类
     */
    public void testBasicData2Class() {
        int i = 1;
        Integer integer = Integer.valueOf(i);

        float f = 1.1F;
        Float float_value = Float.valueOf(f);

        boolean b = true;
        Boolean.valueOf(b);
    }

    /**
     * 包装类转基本数据类型
     */
    public void testClass2BasicData() {
        Integer i = Integer.valueOf(1);
        int int_value = i.intValue();

        Double d = Double.valueOf(1.1);
        double double_value = d.doubleValue();

        Boolean b = Boolean.valueOf(true);
        boolean bool = b.booleanValue();
    }

    /**
     * 自动拆箱装箱
     */
    public void testAutoBoxingUnboxing() {
        Integer i = 1;
        Double d = 2.2;
        Boolean b = false;

        int i1 = i;
        double d1 = d;
        boolean b1 = b;
    }

    /**
     * 基本数据类型、包装类转String
     */
    public void testBasicDataAndClass2String() {
        // 调用String.valueOf()方法
        int i = 1;
        String s1 = String.valueOf(i);

        double d = 2.2;
        String s2 = String.valueOf(d);

        boolean b = true;
        String s3 = String.valueOf(b);

        // 或直接拼接空串
        String s4 = i + "";
        String s5 = d + "";
        String s6 = b + "";
    }

    /**
     * String转基本数据类型、包装类
     */
    public void testString2BasicDataAndClass() {
        String s1 = "132";
        int i1 = Integer.parseInt(s1);
        String s2 = "132abc";
        // int i2 = Integer.parseInt(s2); // 不是数值无法转换，报错NumberFormatException

        String s3 = "true";
        boolean b1 = Boolean.parseBoolean(s3);

        String s4 = "TrUe";
        boolean b2 = Boolean.parseBoolean(s4); // boolean转换时可以无视大小写
    }

    public void testAutoBoxing() {
        Integer i1 = 1;
        Integer i2 = 1;
        System.out.println(i1 == i2);

        Integer i3 = 128;
        Integer i4 = 128;
        System.out.println(i3 == i4);
    }
}