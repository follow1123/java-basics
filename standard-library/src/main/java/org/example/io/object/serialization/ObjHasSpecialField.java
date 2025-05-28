package org.example.io.object.serialization;

import java.io.Serializable;

/**
 * 实现Serializable接口的对象，有transient关键字标记的属性和静态属性
 */
public class ObjHasSpecialField implements Serializable {

    private static final long serialVersionUID = 354124133L;
    private String field1;
    private transient Integer field2;
    private static Float field3;

    public ObjHasSpecialField(String field1, Integer field2, Float f3) {
        this.field1 = field1;
        this.field2 = field2;
        field3 = f3;
    }

    @Override
    public String toString() {
        return "ObjHasSpecialField{" +
                "field1='" + field1 + '\'' +
                ", field2=" + field2 +
                ", field3=" + field3 +
                '}';
    }
}
