package org.example.oop.method;

public class MethodTest {
    public void testReturn() {
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                return; // 提前返回
            }
        }
        System.out.println("方法结束"); // 未输出
    }

    public void testPeople() {
        People people = new People();
        people.setAge(22);
        people.setName("zs");
        System.out.println(people.info());
    }
}
