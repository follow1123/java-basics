package org.example.juc.atomic.reference;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceTest {
    public static void main(String[] args) {
        AtomicStampedReference<String> str = new AtomicStampedReference<String>("a", 1);
        System.out.println(str.getReference()); // a
        System.out.println(str.getStamp()); // 1
        int[] stamp = new int[1];
        String val = str.get(stamp);
        System.out.println(val); // a
        System.out.println(stamp[0]); // 1
        // 只修改戳，不修改值
        System.out.println(str.attemptStamp(str.getReference(), str.getStamp() + 1)); // true
        str.set("b", 3);
        System.out.println(str.compareAndSet("b", "c", 3, 5)); // true
    }
}
