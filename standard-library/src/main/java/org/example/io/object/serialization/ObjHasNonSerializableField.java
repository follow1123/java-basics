package org.example.io.object.serialization;

import java.io.Serializable;

/**
 * 实现Serializable接口的对象，但有个属性没有实现Serializable接口
 */
public class ObjHasNonSerializableField implements Serializable {

    private static final long serialVersionUID = 354124132L;
    private String field1;
    private Integer field2;

    private ObjNonSerializable nonSerializableField;

    public ObjHasNonSerializableField(String field1, Integer field2, ObjNonSerializable nonSerializableField) {
        this.field1 = field1;
        this.field2 = field2;
        this.nonSerializableField = nonSerializableField;
    }

    @Override
    public String toString() {
        return "ObjHasNonSerializableField{" +
                "field1='" + field1 + '\'' +
                ", field2=" + field2 +
                ", nonSerializableField=" + nonSerializableField +
                '}';
    }
}
