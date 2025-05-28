package org.example.basics.arrays;

/**
 * 数组相关算法
 */
public class ArrayAlgorithm {
    /**
     * 按指格式打印数组
     */
    private static void printArray(int[] array) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]).append(", ");
        }
        sb.replace(sb.length() - 2, sb.length(), "]");
        System.out.println(sb);
    }

    /**
     * 线性查找
     */
    private static int linearSearch(int[] arr, int data) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == data) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 线性查找
     */
    public static void testLinearSearch() {
        int[] arr = new int[]{39, 59, 25, 51, 38, 11, 26, 47, 31, 37, 25};
        // 查找26
        System.out.println(linearSearch(arr, 26)); // 6

        // 查找77
        System.out.println(linearSearch(arr, 77)); // -1

    }

    /**
     * 二分查找
     */
    private static int binarySearch(int[] arr, int data) {
        int head = 0;
        int tail = arr.length - 1;
        while (head <= tail) {
            int mid = (head + tail) / 2;
            if (data == arr[mid]) {
                return mid;
            } else if (data > arr[mid]) {
                head = mid + 1;
            } else {
                tail = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找
     */
    public static void testBinarySearch() {

        int[] arr = new int[]{11, 25, 26, 31, 37, 38, 39, 47, 51, 59};
        System.out.println(arr[binarySearch(arr, 51)]);
    }


    /**
     * 冒泡排序
     */
    private static void bubbleSort(int[] arr) {
        int tmp;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    /**
     * 冒泡排序
     */
    public static void testBubbleSort() {
        int[] arr = new int[]{39, 59, 25, 51, 38, 11, 26, 47, 31, 37, 25};
        bubbleSort(arr);
        printArray(arr);
    }


    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            int base = arr[start];
            int low = start;
            int high = end + 1;
            while (true) {
                while (low < end && arr[++low] - base <= 0) ;
                while (high > start && arr[--high] - base >= 0) ;
                if (low < high) {
                    swap(arr, low, high);
                } else {
                    break;
                }
            }
            swap(arr, start, high);

            quickSort(arr, start, high - 1);
            quickSort(arr, high + 1, end);
        }
    }

    /**
     * 快速排序
     */
    public static void testQuickSort() {
        int[] arr = new int[]{39, 59, 25, 51, 38, 11, 26, 47, 31, 37, 25};
        quickSort(arr, 0, arr.length - 1);
        printArray(arr);
    }

}
