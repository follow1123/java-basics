package cn.y.java.io.object_stream.object_serialization;

import java.io.Serializable;

/**
 * 实现Serializable接口的对象，但是没添加serialVersionUID属性
 */
public class ObjNoVersionUID implements Serializable {
    private String field1;
    private Integer field2;

    private Integer field3;

    public ObjNoVersionUID(String field1, Integer field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    @Override
    public String toString() {
        return "ObjNotImplSerializable{" +
                "field1='" + field1 + '\'' +
                ", field2=" + field2 +
                '}';
    }
}
