package cn.y.java.multithreading.creation_method.impl_runnable;

public class ImplRunnableTest {

    public static void main(String[] args) {

        // 使用实现Runnable接口的类实现
        PrintNumber printNumber = new PrintNumber();

        new Thread(printNumber).start();

        System.out.println(Thread.currentThread().getName() + "main thread");

        // 提供Runnable接口的匿名实现类实现
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    if (i % 2 != 0){
                        System.out.println(Thread.currentThread().getName() + ": " + i);
                    }
                }
            }
        }).start();
    }

}
