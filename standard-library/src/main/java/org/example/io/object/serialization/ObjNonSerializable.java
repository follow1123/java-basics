package org.example.io.object.serialization;

/**
 * 未实现Serializable接口的对象
 */
public class ObjNonSerializable {
    private String field1;
    private Integer field2;

    public ObjNonSerializable(String field1, Integer field2) {
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
