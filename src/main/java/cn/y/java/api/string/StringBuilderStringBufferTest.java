package cn.y.java.api.string;

import org.junit.jupiter.api.Test;

public class StringBuilderStringBufferTest {

    @Test
    public void testStringBuilder() {
        StringBuilder sb = new StringBuilder("aaa");
        // 添加
        sb.append("bbb").append("ccc").append("ddd");
        System.out.println(sb); // aaabbbcccddd

        // 删除
        sb.delete(3, 5);
        System.out.println(sb); // aaabcccddd
        sb.deleteCharAt(3); // aaacccddd
        System.out.println(sb);

        // 修改
        sb.replace(3, 5, "bb");
        System.out.println(sb); // aaabbcddd
        sb.setCharAt(5, 'b');
        System.out.println(sb); // aaabbbddd

        // 查询
        System.out.println(sb.charAt(4)); // b

        // 插入
        sb.insert(6, "ccc");
        System.out.println(sb); // aaabbbcccddd

        // 长度
        System.out.println(sb.length()); // 12

        // 翻转
        sb.reverse();
        System.out.println(sb); // dddcccbbbaaa

        // 修改长度，后面的所有数据用0填充
        sb.setLength(3);
        System.out.println(sb); // ddd

    }
}
