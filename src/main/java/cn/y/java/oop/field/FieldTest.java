package cn.y.java.oop.field;

import org.junit.jupiter.api.Test;

public class FieldTest {

    @Test
    public void testEmployee() {
        Employee employee = new Employee();
        employee.id = 1;
        employee.name = "zs";
        employee.work("做表格");
    }
}
