package org.example.reflection;

import org.example.reflection.data.User;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class FieldTest {
    public void testGetAllFields() {
        Class<User> userClass = User.class;
        // 获取当前类以及父类所有的public修饰的属性
        Field[] fields = userClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }

        System.out.println("---------------");
        // 只获取当前类所有修饰符的属性，不包含父类的属性
        Field[] declaredFields = userClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }
    }

    public void testGetFieldInfo() {
        Class<User> userClass = User.class;
        Field[] declaredFields = userClass.getDeclaredFields();
        for (Field f : declaredFields) {
            // 修饰符
            int modifiers = f.getModifiers();
            // 类型
            Class<?> type = f.getType();
            // 名称
            String name = f.getName();

            System.out.printf("field modifier: %s, field type: %s, field name: %s\n",
                    Modifier.toString(modifiers), type.getName(), name);
        }
    }

    public void testSetPublicField() throws Exception {
        Class<User> userClass = User.class;
        User user = userClass.getConstructor().newInstance();

        Field nameField = userClass.getField("name");

        nameField.set(user, "zs");
        System.out.println(nameField.get(user));
    }

    public void testSetNonPublicField() throws Exception {
        Class<User> userClass = User.class;
        User user = userClass.getConstructor().newInstance();

        Field ageField = userClass.getDeclaredField("age");

        ageField.setAccessible(true);

        ageField.set(user, 18);
        System.out.println(ageField.get(user));
    }

    public void testSetStaticField() throws Exception {
        Class<User> userClass = User.class;

        Field uidField = userClass.getDeclaredField("uid");
        uidField.setAccessible(true);

        // 静态属性第一个参数可以填null
        // uidField.set(null, 111);
        // System.out.println(uidField.get(null));

        uidField.set(userClass, 111);
        System.out.println(uidField.get(userClass));
    }
}
