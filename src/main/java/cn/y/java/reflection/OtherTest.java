package cn.y.java.reflection;

import cn.y.java.reflection.data.User;
import org.junit.jupiter.api.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class OtherTest {

    @Test
    public void testGetSuperclass() {
        Class<User> userClass = User.class;
        // 获取运行时类的父类
        System.out.println(userClass.getSuperclass());
    }
    
    @Test
    public void testGetInterfaces() {
        Class<User> userClass = User.class;
        // 获取运行时类实现的接口
        Class<?>[] interfaces = userClass.getInterfaces();
        for (Class<?> i : interfaces) {
            System.out.println(i);
        }
    }

    @Test
    public void testGetPackage() {
        Class<User> userClass = User.class;
        // 获取运行时类所在的包
        System.out.println(userClass.getPackage());
    }

    @Test
    public void testGetGenericSuperclass() {
        Class<User> userClass = User.class;
        // 获取运行时类带泛型的父类
        System.out.println(userClass.getGenericSuperclass());
    }

    @Test
    public void testGetSuperclassGenericType() {
        Class<User> userClass = User.class;
        // 获取运行时类带泛型的父类的泛型参数
        ParameterizedType pt = (ParameterizedType)userClass.getGenericSuperclass();
        Type[] typeArgs = pt.getActualTypeArguments();
        for (Type typeArg : typeArgs) {
            System.out.println(typeArg);
        }
    }
    @Test
    public void testGetInterfaceGenericType() {
        Class<User> userClass = User.class;
        // 获取运行时类带泛型的接口的泛型参数
        Type[] ts = userClass.getGenericInterfaces();
        for (Type t : ts) {
            System.out.println(t.getTypeName());
            // 由于有些接口不带泛型，所以需要判断
            if (t instanceof ParameterizedType){
                ParameterizedType pt = (ParameterizedType) t;
                Type[] typeArgs = pt.getActualTypeArguments();
                for (Type typeArg : typeArgs) {
                    System.out.println(typeArg);
                }
            }
        }
    }
}
