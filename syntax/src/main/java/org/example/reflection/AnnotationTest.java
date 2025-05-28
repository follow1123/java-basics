package org.example.reflection;

import org.example.reflection.data.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationTest {
    public void testClassGetAnnotations() {
        Class<User> userClass = User.class;
        Deprecated d = userClass.getDeclaredAnnotation(Deprecated.class);
        System.out.println(d);

        System.out.println(d.forRemoval());
        System.out.println(d.since());
    }

    public void testGetMethodAnnotations() throws Exception {
        Class<User> userClass = User.class;
        Method eatMethod = userClass.getDeclaredMethod("eat");
        Deprecated d = eatMethod.getDeclaredAnnotation(Deprecated.class);
        System.out.println(d);
    }

    public void testGetConstructorAnnotations() throws Exception {
        Class<User> userClass = User.class;
        Constructor<User> c = userClass.getDeclaredConstructor();
        Deprecated d = c.getDeclaredAnnotation(Deprecated.class);
        System.out.println(d);
    }
}
