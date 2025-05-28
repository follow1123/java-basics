package org.example.oop.field;

/**
 * 员工
 */
public class Employee {
    int id;
    String name;

    public void work(String workName) {
        System.out.println("员工：" + name + "正在：" + workName);
    }
}
