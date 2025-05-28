package org.example.basics.arrays;

import java.util.Arrays;

/**
 * Arrays工具类
 */
public class ArraysUtils {
    public void testArrays() {
        int[] arr = new int[]{39, 59, 25, 51, 38, 11, 26, 47, 31, 37};
        int[] arr1 = new int[]{39, 59, 25, 51, 38, 11, 26, 47, 31, 37, 25};

        System.out.println(Arrays.compare(arr, arr1)); // 比较 0 相同，1 左边数组大，-1左边数组小
        System.out.println(Arrays.equals(arr, arr1)); // 比较， true 相同，false 不同

        Arrays.sort(arr); // 排序
        System.out.println(Arrays.binarySearch(arr, 11)); // 查找

        int[] newArr = Arrays.copyOf(arr, arr1.length + 3); // 扩容

        System.out.println(Arrays.toString(newArr)); // 打印

    }
}
