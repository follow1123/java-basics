package cn.y.java.oop.field;

/**
 * 测试属性赋值顺序
 */
public class AssignmentOrder {

    // int integer; // 默认赋值
    int integer = 1; // 显示赋值

    public AssignmentOrder(){}

    public AssignmentOrder(int i){
        integer = i;
    }

    /**
     * 方法赋值
     */
    public void setInteger(int i){
        integer = i;
    }

    public static void main(String[] args) {
        AssignmentOrder assignmentOrder = new AssignmentOrder();
        // 打开默认赋值代码注释
        // System.out.println(assignmentOrder.integer); // 0

        // 打开显示赋值注释
        System.out.println(assignmentOrder.integer); // 1

        // 构造器赋值
        AssignmentOrder ao2 = new AssignmentOrder(2);
        System.out.println(ao2.integer); // 2

        // 对象.属性赋值可以赋值多次
        ao2.integer = 3;
        ao2.integer = 4;
        System.out.println(ao2.integer); // 4

        // 对象.方法赋值可以赋值多次
        ao2.setInteger(5);
        ao2.setInteger(6);
        System.out.println(ao2.integer); // 6
    }
}
