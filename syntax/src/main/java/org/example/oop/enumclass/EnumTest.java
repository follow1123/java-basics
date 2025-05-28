package org.example.oop.enumclass;

public class EnumTest {
    public void testEnumBefore() {
        System.out.println(SeasonBefore.SPRING);
        System.out.println(SeasonBefore.SUMMER);
        System.out.println(SeasonBefore.AUTUMN);
        System.out.println(SeasonBefore.WINTER);
    }

    public void testEnum() {
        // 由于* 使用`enum`关键字定义的枚举类默认父类是`java.lang.Enum`，toString方法默认会输出实例对象的命令
        System.out.println(Season.SPRING);
        System.out.println(Season.SUMMER);
        System.out.println(Season.AUTUMN);
        System.out.println(Season.WINTER);

        System.out.println("--");
        // Enum父类中的常用方法
        // name() 返回实例的名称
        System.out.println(Season.WINTER.name());

        // 静态方法values
        Season[] values = Season.values();
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
        }

        // 静态方法valueOf
        System.out.println(Season.valueOf("SPRING")); // 根据命令获取枚举实例
        // System.out.println(Season.valueOf("SPRING1")); // 命令写错则会报错，IllegalArgumentException

        // ordinal返回当前枚举实例在枚举类中的位置索引
        System.out.println(Season.WINTER.ordinal());
    }

    public void testEnumWithInterface() {
        // 继承至Runnable接口实现run方法
        // 未主动重写run方法，使用类重写的run方法
        SeasonWithInterface.SUMMER.run();
        SeasonWithInterface.WINTER.run();

        // 定义时重写自己的run方法
        SeasonWithInterface.SPRING.run();
        SeasonWithInterface.AUTUMN.run();
    }
}
