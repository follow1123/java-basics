package cn.y.java.oop.method;

public class People {
    String name;

    int age;

    public void setName(String n){
        name = n;
    }

    public void setAge(int a){
        age = a;
    }

    public String info(){
        return "我的名字是：" + name + ", "  +  age + "岁";
    }
}
