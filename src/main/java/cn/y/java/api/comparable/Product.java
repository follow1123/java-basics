package cn.y.java.api.comparable;

public class Product implements Comparable {

    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) return 0;
        if (o instanceof Product){
            Product p = (Product) o;
            int v = Double.compare(this.price, p.price);
            if (v != 0) return v;
            // 如果price相同，则比较name的大小
            return this.name.compareTo(p.name);
        }
        throw new RuntimeException("类型不匹配");
    }
}
