package org.example.generics.wildcards;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class WildcardsTest {

    /**
     * 泛型和继承的关系
     */
    public void test() {
        // 容器相同，泛型参数是子父类的关系
        ArrayList<Object> list1 = null;
        ArrayList<String> list2 = new ArrayList<>();
        /*
        无法将泛型参数为Object的数组赋值给泛型参数为String的数组，尽管String是Object的子类
        如果可以的话会出现以下情况
        list1 = list2;
        list2.add("a");
        list1.add(100);

        此时获取获取下标为1的数据时是一个整数，而list2是String类型的数组
        list2.get(1);
         */
        // list1 = list2;

        // 容器是子父类关系，泛型参数相同
        HashSet<String> set1 = null;
        LinkedHashSet<String> set2 = new LinkedHashSet<>();

        // 可以赋值
        set1 = set2;

        set1.add("a");
        set2.add("b");
        System.out.println(set1);
    }

    /**
     * 测试通配符
     */
    public void testWildcards() {
        List<?> list = null;
        List<String> list1 = new ArrayList<>();

        list1.add("a");
        list = list1;

        // 可以读取数据，数据类型统一为Object
        Object o = list.get(0);

        // 无法写入数据
        // list.add("b");

        // 但是可以写入null值，因为所有引用类型的值都可以是null
        list.add(null);
    }

    /**
     * 测试extends条件的通配符赋值情况
     */
    public void testExtendsWildcards() {
        List<? extends Father> list = null;

        List<Object> list1 = null;
        List<Father> list2 = null;
        List<Son> list3 = null;

        // 无法赋值Father类型的父类
        // list = list1;

        // 可以赋值Father类型及其子类
        list = list2;
        list = list3;
    }

    /**
     * 测试extends条件的通配符操作情况
     */
    public void testOperateExtendsWildcards() {
        List<? extends Father> list = null;
        List<Father> list1 = new ArrayList<>();

        list1.add(new Father());
        list = list1;

        // 可以获取数据，数据的类型确定为Father
        Father father = list.get(0);

        /*
         无法添加除null以外的所有数据
         从List<? extends Father>泛型参数无法看出运行时集合内元素的具体类型，
         因为Father的子类可以无限被其子类继承
         */
        list.add(null);
        // list.add(new Father());
        // list.add(new Son());
    }

    /**
     * 测试super条件的通配符赋值情况
     */
    public void testSuperWildcards() {
        List<? super Father> list = null;

        List<Object> list1 = null;
        List<Father> list2 = null;
        List<Son> list3 = null;

        // 可以赋值为Father和它的父类
        list = list1;
        list = list2;

        // 无法赋值Father的子类
        // list = list3;
    }

    /**
     * 测试super条件的通配符操作情况
     */
    public void testOperateSuperWildcards() {
        List<? super Father> list = null;
        List<Father> list1 = new ArrayList<>();

        list1.add(new Father());
        list = list1;

        // 可以获取数据，但无法获取具体类型，所以返回类型是Object
        Object o = list.get(0);

        /*
         无法添加Father的父类，可以添加Father及其子类
         从List<? super Father>泛型参数可以看出运行时集合内元素至少是一个Father类型
         所以可以添加Father及其子类
         */
        // list.add(new Object());
        list.add(new Father());
        list.add(new Son());
    }
}
