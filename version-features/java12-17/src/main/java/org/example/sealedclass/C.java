package org.example.sealedclass;

/**
 * 将C也使用sealed修饰后可以正常继承
 * 表示C类也变成了密封类
 */
public sealed class C extends A permits E {
}
