package cn.y.java.oop.annotation;

public @interface MyAnnotation {
    String value() default "123";
}
