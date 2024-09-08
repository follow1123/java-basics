package cn.y.java.oop.method;

/**
 * 可变形参
 */
public class VariableParameters {

    /**
     * 可变形参方法
     */
    public int add(int... nums){
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
        }
        return count;
    }

    /**
     * 可变形参方法不能与相同类型的数组的方法存在同一个类中，编译不通过
     */
    // public int add(int[] nums){
    //     int count = 0;
    //     for (int i = 0; i < nums.length; i++) {
    //         count += nums[i];
    //     }
    //     return count;
    // }

    /**
     * 可变形参必须声明在形参列表的最后面
     */
    public boolean equalsAll(long a, int... nums){
        return a ==  add(nums);
    }

    /**
     * 可变形参在方法内只能声明一次，编译不通过
     */
    // public boolean equalsAll(long... a, int... nums){
    //    return add(a) == add(nums);
    // }
}

