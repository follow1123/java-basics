package cn.y.java.syntax.arrays;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

/**
 * 数组相关操作
 */
public class ArrayOperator {

    /**
     * 获取a到b之间的随机数
     */
    private int getRandomInteger(int a, int b) {
        return (int) (Math.random() * (b - a + 1) + a);
    }

    private int[] getRandomIntegerArray(int a, int b, int length) {
        int[] integers = new int[length];
        for (int i = 0; i < length; i++) {
            integers[i] = getRandomInteger(a, b);
        }
        return integers;
    }

    /**
     * 按指格式打印数组
     */
    private void printArray(int[] array) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]).append(", ");
        }
        sb.replace(sb.length() - 2, sb.length(), "]");
        System.out.println(sb);
    }

    /**
     * 最大值
     */
    @Test
    public void testMax() {
        int[] array = getRandomIntegerArray(10, 59, 10);

        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        printArray(array);
        System.out.println(max);
    }

    /**
     * 最小值
     */
    @Test
    public void testMin() {
        int[] array = getRandomIntegerArray(10, 59, 10);

        int min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (min > array[i]) {
                min = array[i];
            }
        }
        printArray(array);
        System.out.println(min);
    }

    /**
     * 求和
     */
    @Test
    public void testSum() {
        int[] array = getRandomIntegerArray(10, 59, 10);

        int count = 0;
        for (int i = 0; i < array.length; i++) {
            count += array[i];
        }
        printArray(array);
        System.out.println(count);
    }

    /**
     * 平均数
     */
    @Test
    public void testAvg() {
        int[] array = getRandomIntegerArray(10, 59, 10);

        int count = 0;
        for (int i = 0; i < array.length; i++) {
            count += array[i];
        }
        printArray(array);
        System.out.println(count / array.length);
    }

    /**
     * 杨辉三角，自己做的
     */
    @Test
    public void testPascalTriangleCustom() {
        int[][] arrays = new int[10][];

        for (int i = 0; i < 10; i++) {
            arrays[i] = new int[i + 1];
            for (int j = 0; j <= i; j++) {
                if (i >= 2 && j > 0 && j < arrays[i].length - 1) {
                    arrays[i][j] = arrays[i - 1][j - 1] + arrays[i - 1][j];
                } else {
                    arrays[i][j] = 1;
                }

            }
        }

        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays[i].length; j++) {
                System.out.print(arrays[i][j] + "\t");
            }
            System.out.println();
        }

    }

    /**
     * 杨辉三角
     */
    @Test
    public void testPascalTriangle() {
        int[][] arrays = new int[10][];

        for (int i = 0; i < arrays.length; i++) {
            // 边框
            arrays[i] = new int[i + 1];
            arrays[i][0] = arrays[i][i] = 1;
            // 内部
            for (int j = 1; j < arrays[i].length - 1; j++) {
                arrays[i][j] = arrays[i - 1][j - 1] + arrays[i - 1][j];
            }
        }

        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays[i].length; j++) {
                System.out.print(arrays[i][j] + "\t");
            }
            System.out.println();
        }

    }

    /**
     * 回形数
     * 1  2  3  4
     * 12 13 14 5
     * 11 16 15 6
     * 10 9  8  7
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num;
        while ((num = scanner.nextInt()) != 0) {
            int[][] arrays = new int[num][num];

            int loopNum = num * num;
            // 1 右 2 下 3 左 4 上
            int direction = 1;

            int i = 0, j = 0;
            for (int data = 1; data <= loopNum; data++) {
                if (direction == 1) {
                    if (j < num && arrays[i][j] == 0) {
                        arrays[i][j++] = data;
                    } else {
                        direction = 2;
                        i++;
                        j--;
                        data--;

                    }
                } else if (direction == 2) {
                    if (i < num && arrays[i][j] == 0) {
                        arrays[i++][j] = data;
                    } else {
                        direction = 3;
                        i--;
                        j--;
                        data--;
                    }
                } else if (direction == 3) {
                    if (j >= 0 && arrays[i][j] == 0) {
                        arrays[i][j--] = data;
                    } else {
                        direction = 4;
                        i--;
                        j++;
                        data--;
                    }
                } else if (direction == 4) {
                    if (i >= 0 && arrays[i][j] == 0) {
                        arrays[i--][j] = data;
                    } else {
                        direction = 1;
                        i++;
                        j++;
                        data--;
                    }
                }

            }
            for (int m = 0; m < arrays.length; m++) {
                for (int n = 0; n < arrays[m].length; n++) {
                    System.out.print(arrays[m][n] + "\t");
                }
                System.out.println();
            }

        }
    }

    /**
     * 数组翻转
     */
    @Test
    public void testReverse() {
        int[] arr = new int[]{39, 59, 25, 51, 38, 11, 26, 47, 31, 37, 25};


        int lastIdx = arr.length - 1;
        int halfLen = arr.length / 2;

        for (int i = 0; i < halfLen; i++) {
            // 分别将头尾的元素交换
            int tmp = arr[i];
            arr[i] = arr[lastIdx - i];
            arr[lastIdx - i] = tmp;
            // arr[i] = arr[i] ^ arr[lastIdx - i];
            // arr[lastIdx - i] = arr[i] ^ arr[lastIdx - i];
            // arr[i] = arr[i] ^ arr[lastIdx - i];
        }
        printArray(arr);
    }

    /**
     * 数组扩容
     */
    @Test
    public void testScaling() {
        // 38, 11, 26
        int[] arr = new int[]{39, 59, 25, 51};
        // 创建新数组
        int[] newArr = new int[arr.length * 2];
        // 将原数组内的数据添加进来
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        // 添加新元素
        newArr[arr.length] = 38;
        newArr[arr.length + 1] = 11;
        newArr[arr.length + 2] = 26;

        printArray(newArr);
    }

}
