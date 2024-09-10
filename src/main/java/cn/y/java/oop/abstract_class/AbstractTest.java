package cn.y.java.oop.abstract_class;

import org.junit.jupiter.api.Test;

public class AbstractTest {

    @Test
    public void testAbstract() {
        Circle circle = new Circle();
        circle.radius = 5;
        System.out.println(circle.getArea());

        Square square = new Square();
        square.side = 5;
        System.out.println(square.getArea());
    }
}
