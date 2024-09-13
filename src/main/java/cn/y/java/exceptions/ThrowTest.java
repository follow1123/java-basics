package cn.y.java.exceptions;

import org.junit.jupiter.api.Test;

public class ThrowTest {

    public void setAge(int age){
        if (age < 0){
            throw new RuntimeException("年龄不能为负数");
        }
        System.out.println(age);
    }

    /**
     * 测试手动抛出运行时异常
     */
    @Test
    public void testThrowRuntimeException() {
        setAge(10);

        setAge(-10); // 报错
    }

    public void setPhone(String phone) throws Exception {
        if (phone.length() != 11){
            throw new Exception("电话号码必须为11位");
        }
        System.out.println(phone);
    }

    /**
     * 测试手动抛出编译时异常
     */
    @Test
    public void testThrowException() {
        try {
            setPhone("12345678901");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
