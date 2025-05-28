package org.example.oop.abstractclass;

public class AbstractTest {
    public void testAbstract() {
        Circle circle = new Circle();
        circle.radius = 5;
        System.out.println(circle.getArea());

        Square square = new Square();
        square.side = 5;
        System.out.println(square.getArea());
    }
}
