package org.example.basics.arrays;

/**
 * 一维数组
 */
public class OneDimensionalArray {
    public void testArray1() {
        // 声明
        int[] integers;
        String[] names;
        // 初始化
        integers = new int[]{1, 2, 3, 4}; // 静态初始化
        int[] integers1 = {5, 6, 7, 8}; // 类型推断
        names = new String[4]; // 动态初始化

        System.out.println(integers[0]); // 1
        System.out.println(integers[1]); // 2
        // System.out.println(integers[4]); // 报错ArrayIndexOutOfBoundsException

        names[0] = "zs";
        names[1] = "ls";
        // names[4] = "ww"; // 报错ArrayIndexOutOfBoundsException

        System.out.println(integers.length); // 4
        System.out.println(names.length); // 4


        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }

        // foreach遍历
        for (int integer : integers) {
            System.out.println(integer);
        }

    }

    public void testArray2() {
        // 数组默认初始化值
        int[] is = new int[3];
        double[] ds = new double[4];
        String[] strs = new String[5];
        System.out.println(is[0]); // 0
        System.out.println(ds[0]); // 0.0
        System.out.println(strs[1]); // null

    }

    public void testArrayInMemory() {
        int[] arr1 = new int[4];
        arr1[0] = 10;
        arr1[1] = 20;

        String[] arr2 = new String[2];
        arr2[1] = "zs";
        arr2 = new String[3];
    }
}
