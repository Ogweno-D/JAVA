package com.example.sports.team.football.variants.outdoor;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class BeachFootballTest {
    @Test
    public void testPlay() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        BeachFootball beachFootball = new BeachFootball();
        beachFootball.play();

        assertEquals("Playing beach football: 5 players per team on a sandy beach, high-scoring and acrobatic.\n", outContent.toString());
        System.setOut(System.out);
    }
}