package cn.y.java.syntax;

import org.junit.jupiter.api.Test;

/**
 * 控制流程
 */
public class ControlFlow {

    @Test
    public void testIfElseStatements() {
        int i1 = 0;
        if (i1 > 0) {
            i1--;
        }
        System.out.println(i1); // 0
        if (i1 > 0) {
            i1--;
        } else {
            i1++;
        }
        System.out.println(i1); // 1
        if (i1 > 1) {
            i1--;
        } else if (i1 == 1) {
            i1 = 0;
        }
        System.out.println(i1); // 0
    }

    @Test
    public void testSwitchCase() {
        int week = 5;
        String weekStr;
        switch (week) {
            case 1:
                weekStr = "Monday";
                break;
            case 2:
                weekStr = "Tuesday";
                break;
            case 3:
                weekStr = "Wednesday";
                break;
            case 4:
                weekStr = "Thursday";
                break;
            case 5:
                weekStr = "Friday";
                break;
            case 6:
                weekStr = "Saturday";
                break;
            case 7:
                weekStr = "Sunday";
                break;
            default:
                weekStr = "error";
                break;
        }
        System.out.println(weekStr); // Friday

        // case 5 未加上break
        switch (week) {
            case 1:
                weekStr = "Monday";
                break;
            case 2:
                weekStr = "Tuesday";
                break;
            case 3:
                weekStr = "Wednesday";
                break;
            case 4:
                weekStr = "Thursday";
                break;
            case 5:
                weekStr = "Friday";
                // break;
            case 6:
                weekStr = "Saturday";
                break;
            case 7:
                weekStr = "Sunday";
                break;
            default:
                weekStr = "error";
                break;
        }
        System.out.println(weekStr); // Saturday
    }

    @Test
    public void testForStatement() {
        /*
         for(初始化; 循环条件; 迭代){
            循环体
         }

         依次输出1到10
         */
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
        }
    }

    @Test
    public void testWhileStatements() {
        /*
         初始化
         while(循环条件){
             循环体
             迭代
         }

         依次输出1到10
         */
        int i = 1;
        while (i <= 10) {
            System.out.println(i);
            i++;
        }
    }

    @Test
    public void testDoWhileStatements() {
        /*
         初始化
         do {
             循环体
             迭代
         }while(循环条件)

         依次输出1到10
         */
        int i = 1;
        do {
            System.out.println(i);
            i++;
        }while (i <= 10);

        /*
         初始化
         do {
             循环体
             迭代
         }while(循环条件)

         依次输出1到10
         */
        int i1 = 1;
        do {
            System.out.println(i1);
            i1++;
            break;
        }while (i1 <= 10);
        System.out.println(i1); // 2

    }
    @Test
    public void testBreakContinue() {
        // 跳出循环
        for (int i = 1; i <= 10; i++) {
            if (i > 4){
                break;
            }
            System.out.println(i);
        }

        System.out.println("---");

        // 跳过偶数
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0){
                continue;
            }
            System.out.println(i);
        }

    }
}
