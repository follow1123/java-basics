package cn.y.java.oop.method;


/**
 * 人
 */
public class Person {

    String name;

    public void eat(){
        System.out.println(name + "吃饭");
    }

    public void sleep(int hour){
        System.out.println("先睡" + hour + "小时");
    }

    public boolean isHappy(){
        return true;
    }

}
