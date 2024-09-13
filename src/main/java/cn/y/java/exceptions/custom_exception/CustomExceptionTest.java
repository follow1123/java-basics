package cn.y.java.exceptions.custom_exception;

import org.junit.jupiter.api.Test;

public class CustomExceptionTest {

    public void setAge(int age) {
        if (age < 0) {
            throw new WrongAgeException("年龄不能为负数");
        }
        System.out.println(age);
    }

    /**
     * 测试手动抛出自定义WrongAgeException运行时异常
     */
    @Test
    public void testThrowRuntimeException() {
        setAge(10);

        setAge(-10); // 报错
    }

    public void setPhone(String phone) throws WrongPhoneNumberException {
        if (phone.length() != 11) {
            throw new WrongPhoneNumberException("电话号码必须为11位");
        }
        System.out.println(phone);
    }

    /**
     * 测试手动抛出WrongPhoneNumberException编译时异常
     */
    @Test
    public void testThrowException() {
        try {
            setPhone("123456789011");
        } catch (WrongPhoneNumberException e) {
            e.printStackTrace();
        }
    }
}
