package cn.y.java.oop.annotation;

import org.junit.jupiter.api.Test;


public class AnnotationTest {

    @Test
    public void testMyAnnotation() {

        @MyAnnotation String name = "234";

    }
}
