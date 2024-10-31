package cn.y.java.reflection;

import cn.y.java.reflection.data.User;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MethodTest {

    @Test
    public void testGetAllMethods() {
        Class<User> userClass = User.class;

        // 获取当前类以及父类所有的public修饰的方法
        Method[] methods = userClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        System.out.println("---------------");
        // 只获取当前类所有修饰符的方法，不包含父类的方法
        Method[] declaredMethods = userClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }
    }

    @Test
    public void testGetMethodInfo() {
        Class<User> userClass = User.class;
        Method[] methods = userClass.getDeclaredMethods();
        for (Method m : methods) {
            System.out.printf("name\t\t\t%s\n", m.getName());
            /*
                获取方法上声明的注解，可以有多个
                注解必须使用@Retention(RetentionPolicy.RUNTIME)声明，保证运行时存在
             */
            Annotation[] annotations = m.getAnnotations();
            System.out.printf("annotations\t\t%d\n", annotations.length);
            for (Annotation annotation : annotations) {
                System.out.println(annotation);
            }

            // 修饰符
            System.out.printf("modifiers\t\t%s\n", Modifier.toString(m.getModifiers()));
            // 返回值类型
            System.out.printf("return type\t\t%s\n", m.getReturnType().getName());

            // 形参列表
            Class<?>[] parameterTypes = m.getParameterTypes();
            System.out.printf("params type\t\t%s\n", parameterTypes.length);
            for (Class<?> parameterType : parameterTypes) {
                System.out.println(parameterType.getName());
            }

            // 抛出的异常
            Class<?>[] exceptionTypes = m.getExceptionTypes();
            System.out.printf("exceptions type\t%s\n", exceptionTypes.length);
            for (Class<?> exceptionType : exceptionTypes) {
                System.out.println(exceptionType);
            }

            System.out.println("------------------------------------------------------------");
        }
    }

    @Test
    public void testInvokeMethod() throws Exception {
        Class<User> userClass = User.class;
        User user = userClass.getConstructor().newInstance();

        // 调用带参数的方法
        Method sleepMethod = userClass.getDeclaredMethod("sleep", int.class);
        sleepMethod.setAccessible(true);

        sleepMethod.invoke(user, 8);

        // 调用有返回值的方法
        Method workingMethod = userClass.getDeclaredMethod("working");
        workingMethod.setAccessible(true);

        System.out.println(workingMethod.invoke(user));
    }

    @Test
    public void testInvokeStaticMethod() throws Exception {
        Class<User> userClass = User.class;

        // 调用静态方法
        Method seeMethod = userClass.getDeclaredMethod("see");
        seeMethod.setAccessible(true);
        seeMethod.invoke(null);
    }
}
