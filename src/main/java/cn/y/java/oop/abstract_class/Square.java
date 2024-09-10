package cn.y.java.oop.abstract_class;

/**
 * 正方形
 */
public class Square extends Geometry{

    int side;


    @Override
    double getArea() {
       return side * side;
    }
}
