package org.example.oop;

public class PhoneTest {
    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.name = "hw";
        phone.price = 6999.0;

        phone.call();
        phone.playGame();
    }
}
