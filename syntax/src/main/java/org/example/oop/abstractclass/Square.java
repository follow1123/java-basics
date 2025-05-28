package org.example.oop.abstractclass;

/**
 * 正方形
 */
public class Square extends Geometry {
    int side;

    @Override
    double getArea() {
        return side * side;
    }
}
