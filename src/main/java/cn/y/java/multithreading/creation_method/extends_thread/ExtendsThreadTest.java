package cn.y.java.multithreading.creation_method.extends_thread;

public class ExtendsThreadTest {

    public static void main(String[] args) {
        // 创建对象
        PrintNumber printNumber = new PrintNumber();
        printNumber.start();

        System.out.println(Thread.currentThread().getName() + " main");

        // 创建Thread类的匿名子类对象使用
        new Thread(){
            @Override
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    if (i % 2 == 0){
                        System.out.println(Thread.currentThread().getName() + ": " + i);
                    }
                }
            }
        }.start();

    }

}
