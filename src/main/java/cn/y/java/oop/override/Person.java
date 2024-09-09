package cn.y.java.oop.override;

/**
 * 人
 */
public class Person {

    String name;

    int age;

    public void eat(){
        System.out.println("吃饭");
    }

    public void read(String word){
        System.out.println("说话：" + word);
    }

    public void sleep(int hour){
        System.out.println("先睡" + hour + "小时");
    }

    public String info(){
        return name + ":" + age;
    }

}
