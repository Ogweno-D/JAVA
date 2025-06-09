package com.example.sports.core;

import com.example.sports.team.football.variants.indoor.Futsal;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SportTest {
    @Test
    public void testStartGame() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Futsal futsal = new Futsal();
        futsal.startGame();

        assertEquals("The game has started!\n", outContent.toString());
        System.setOut(System.out);
    }

    @Test
    public void testDisplayRules() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Sport.displayRules();

        assertEquals("Displaying the rules of the game.\n", outContent.toString());
        System.setOut(System.out);
    }
}