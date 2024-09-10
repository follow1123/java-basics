package cn.y.java.oop.abstract_class;

/**
 * 圆形
 */
public class Circle extends Geometry{
    int radius;
    @Override
    double getArea() {
        return Math.PI * (radius * radius);
    }
}
