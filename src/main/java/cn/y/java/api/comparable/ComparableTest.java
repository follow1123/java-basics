package cn.y.java.api.comparable;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ComparableTest {

    @Test
    public void testCompareString() {
        String[] strings = {"f", "g", "d", "e", "p"};

        Arrays.sort(strings);

        System.out.println(Arrays.toString(strings));
    }

    @Test
    public void testCompareObj() {
        Product[] products = new Product[3];

        products[0] = new Product("vde", 1000);
        products[1] = new Product("ads", 2000);
        products[2] = new Product("zdd", 1000);

        Arrays.sort(products);

        for (Product product : products) {
            System.out.println(product);
        }
    }
}
