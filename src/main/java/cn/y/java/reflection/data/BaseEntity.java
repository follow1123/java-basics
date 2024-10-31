package cn.y.java.reflection.data;

public class BaseEntity<T> {

    private String id;

    public String date;

    public void update(){
        System.out.println("update");
    }

    private void delete(){
        System.out.println("delete");
    }

}
