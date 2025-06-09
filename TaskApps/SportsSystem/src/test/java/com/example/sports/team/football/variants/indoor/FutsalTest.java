package com.example.sports.team.football.variants.indoor;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FutsalTest {
    @Test
    public void testPlay() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Futsal futsal = new Futsal();
        futsal.play();

        assertEquals("Playing futsal: 5 players per team on a 40m x 20m indoor court, focusing on ball control.\n", outContent.toString());
        System.setOut(System.out);
    }
}
