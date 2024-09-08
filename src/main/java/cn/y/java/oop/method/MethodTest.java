package cn.y.java.oop.method;

import org.junit.jupiter.api.Test;

public class MethodTest {

    @Test
    public void testReturn() {
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                return; // 提前返回
            }
        }
        System.out.println("方法结束"); // 未输出
    }

    @Test
    public void testPeople() {
        People people = new People();
        people.setAge(22);
        people.setName("zs");
        System.out.println(people.info());
    }
}
