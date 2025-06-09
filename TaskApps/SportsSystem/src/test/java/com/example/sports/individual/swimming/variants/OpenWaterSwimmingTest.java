package com.example.sports.individual.swimming.variants;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class OpenWaterSwimmingTest {
    @Test
    public void testPlay() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        OpenWaterSwimming openWater = new OpenWaterSwimming();
        openWater.play();

        assertEquals("Competing in open water swimming: Long-distance in natural waters.\n", outContent.toString());
        System.setOut(System.out);
    }
}