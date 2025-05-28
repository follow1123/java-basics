package org.example.oop.interfaceobj;

public class InterfaceTest {
    public void testInterface() {
        Plane plane = new Plane();
        plane.fly();
        plane.attack();

        Kite kite = new Kite();
        kite.fly();
    }


    public void testInterfaceExtends() {

        Gunship gunship = new Gunship();
        // Gunship未实现Flyable接口，也可以调用fly方法，因为Hovering接口继承了Flyable接口
        gunship.fly();
        gunship.hover();
        gunship.attack();
    }

    private void fly(Flyable flyable) {
        flyable.fly();
    }

    public void testAnonymousInterface() {
        fly(new Kite());

        Flyable flyable = new Flyable() {
            @Override
            public void fly() {
                System.out.println("飞");
            }
        };

        fly(flyable);
    }
}
