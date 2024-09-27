package cn.y.java.api.comparable;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorTest {

    @Test
    public void testComparator() {
        Product[] products = new Product[4];

        products[0] = new Product("vde", 1000);
        products[1] = new Product("ads", 2000);
        products[2] = new Product("zdd", 1000);
        products[3] = new Product("zdd", 5000);

        Arrays.sort(products, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 == o2) return 0;

                if(o1 instanceof Product && o2 instanceof Product){
                    Product p1 = (Product) o1;
                    Product p2 = (Product) o2;
                    // 先按name从小到大的排序
                    int v = p1.getName().compareTo(p2.getName());
                    if (v != 0) return v;

                    // name相同再按price从大到小排序
                    return -Double.compare(p1.getPrice(), p2.getPrice());

                }
                throw new RuntimeException("类型不匹配");
            }
        });

        for (Product product : products) {
            System.out.println(product);
        }

    }
}
