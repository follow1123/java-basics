package org.example.sealedclass;

public sealed class A permits B, C, D {
    private String name;
}
