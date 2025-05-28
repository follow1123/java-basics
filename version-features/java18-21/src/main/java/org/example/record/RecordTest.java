package org.example.record;

import org.junit.jupiter.api.Test;

public class RecordTest {

    @Test
    public void testWithInstanceof() {
        Color color = new RGBColor(255, 255, 100);
        if (color instanceof RGBColor(int red, int green, int blue)) {
            System.out.printf("[%d, %d, %d]\n", red, green, blue);
        }
    }

    @Test
    public void testWithSwitch() {
        Color color = new HexColor(0xFFFFFE);
        switch (color) {
            case RGBColor(int red, int green, int blue) -> System.out.printf("[%d, %d, %d]\n", red, green, blue);
            case HexColor(int hex) -> System.out.printf("%x\n", hex);
            default -> System.out.println("error");
        }
    }
}
