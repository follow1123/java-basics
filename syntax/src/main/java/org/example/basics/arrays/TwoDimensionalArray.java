package org.example.basics.arrays;

/**
 * 二维数组
 */
public class TwoDimensionalArray {
    public void testArray1() {
        // 初始化，模拟班级里面分组
        String[][] classroom = new String[][]{
                {"zs", "ls"},
                {"aa", "bb", "cc"},
                {"mm", "nn", "xx", "yy", "zz"}
        };

        // 获取
        // 1组里面的第二个成员
        System.out.println(classroom[0][1]); // ls
        // 3组里面的第三个成员
        System.out.println(classroom[2][2]); // xx
        System.out.println("---");
        // 长度
        // 班级里面有多少个组
        System.out.println(classroom.length); // 3
        // 每个组里面有多少个成员
        System.out.println(classroom[0].length); // 2
        System.out.println(classroom[1].length); // 3
        System.out.println(classroom[2].length); // 5

        System.out.println("---");
        // 遍历
        // 模拟报数
        for (int i = 0; i < classroom.length; i++) {
            System.out.println("第" + (i + 1) + "组报数");
            for (int j = 0; j < classroom[i].length; j++) {
                System.out.println(classroom[i][j] + ": " + (j + 1));
            }
        }
    }
}
