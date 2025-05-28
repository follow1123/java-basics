package org.example.reflection;

import org.example.reflection.data.User;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class OtherTest {
    public void testGetSuperclass() {
        Class<User> userClass = User.class;
        // 获取运行时类的父类
        System.out.println(userClass.getSuperclass());
    }

    public void testGetInterfaces() {
        Class<User> userClass = User.class;
        // 获取运行时类实现的接口
        Class<?>[] interfaces = userClass.getInterfaces();
        for (Class<?> i : interfaces) {
            System.out.println(i);
        }
    }

    public void testGetPackage() {
        Class<User> userClass = User.class;
        // 获取运行时类所在的包
        System.out.println(userClass.getPackage());
    }

    public void testGetGenericSuperclass() {
        Class<User> userClass = User.class;
        // 获取运行时类带泛型的父类
        System.out.println(userClass.getGenericSuperclass());
    }

    public void testGetSuperclassGenericType() {
        Class<User> userClass = User.class;
        // 获取运行时类带泛型的父类的泛型参数
        ParameterizedType pt = (ParameterizedType) userClass.getGenericSuperclass();
        Type[] typeArgs = pt.getActualTypeArguments();
        for (Type typeArg : typeArgs) {
            System.out.println(typeArg);
        }
    }

    public void testGetInterfaceGenericType() {
        Class<User> userClass = User.class;
        // 获取运行时类带泛型的接口的泛型参数
        Type[] ts = userClass.getGenericInterfaces();
        for (Type t : ts) {
            System.out.println(t.getTypeName());
            // 由于有些接口不带泛型，所以需要判断
            if (t instanceof ParameterizedType) {
                ParameterizedType pt = (ParameterizedType) t;
                Type[] typeArgs = pt.getActualTypeArguments();
                for (Type typeArg : typeArgs) {
                    System.out.println(typeArg);
                }
            }
        }
    }
}
