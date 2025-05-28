package org.example.io.object.serialization;

import java.io.Serializable;

/**
 * 实现Serializable接口的对象，并添加serialVersionUID属性
 */
public class ObjSerializable implements Serializable {

    private static final long serialVersionUID = 354124132L;
    private String field1;
    private Integer field2;
    // private Integer field3;

    public ObjSerializable(String field1, Integer field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    @Override
    public String toString() {
        return "ObjSerializable{" +
                "field1='" + field1 + '\'' +
                ", field2=" + field2 +
                '}';
    }

    // @Override
    // public String toString() {
    //     return "ObjSerializable{" +
    //             "field1='" + field1 + '\'' +
    //             ", field2=" + field2 +
    //             ", field3=" + field3 +
    //             '}';
    // }
}
