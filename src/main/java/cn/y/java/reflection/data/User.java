package cn.y.java.reflection.data;

@Deprecated(since = "11")
public class User extends BaseEntity<User> implements Comparable<User>, Runnable{

    public String name;

    private int age;

    public Long number;

    private static int uid;

    @Deprecated(since = "7")
    public User() {
        System.out.println("init");
    }

    public User(String name) {
        this.name = name;
    }

    private User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Deprecated(since = "10")
    public void eat(){
        System.out.println("吃饭");
    }

    private void sleep(int hours){
        System.out.println("睡了" + hours + "个小时");
    }

    public String working() throws RuntimeException, NullPointerException{
        return "working";
    }

    @Override
    public int compareTo(User o) {
        return 0;
    }

    @Override
    public void run() {
        System.out.println("run");
    }

    public static void see(){
        System.out.println("see");
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", number=" + number +
                '}';
    }
}
