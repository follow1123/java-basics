package cn.y.java.oop.method;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 变量值传递机制
 */
public class ValueTransfer {

    public void addOne(int i){
        i = + 1;
    }

    /**
     * 基本数据类型
     */
    @Test
    public void testBasicDataType() {
        int i = 1;
        addOne(i);
        System.out.println(i); // 1
    }

    public void addOne(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] + 1;
        }
    }

    /**
     * 数组
     */
    @Test
    public void testArray(){
        int[] arr = new int[]{1, 1, 1, 1, 1};
        addOne(arr);
        System.out.println(Arrays.toString(arr)); // 2, 2, 2, 2, 2
    }

    public void growUp(People people){
        people.setAge(people.age + 1);
    }

    /**
     * 引用数据类型
     */
    @Test
    public void testReferenceDataType() {
        People people = new People();
        people.setName("zs");
        people.setAge(11);
        System.out.println(people.info()); // 我的名字是：zs, 11岁

        growUp(people);
        System.out.println(people.info()); // 我的名字是：zs, 12岁
    }

}
