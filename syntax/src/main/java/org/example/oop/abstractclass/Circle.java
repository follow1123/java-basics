package org.example.oop.abstractclass;

/**
 * 圆形
 */
public class Circle extends Geometry {
    int radius;

    @Override
    double getArea() {
        return Math.PI * (radius * radius);
    }
}
