package com.example.sports.team.rugby.variants;
import com.example.sports.team.rugby.core.Rugby;

public class RugbySevens implements Rugby {
    @Override
    public void play() {
        System.out.println("Playing rugby sevens: 7 players per team, fast-paced with fewer scrums.");
    }

    @Override
    public void score() {
        System.out.println("Scoring a try in rugby sevens! Ball grounded in try zone.");
    }

    @Override
    public void endGame() {
        System.out.println("Rugby sevens game ended after two 7-minute halves.");
    }

    @Override
    public void SetTeamFormation() {
        System.out.println("Setting rugby sevens formation: 3 forwards, 4 backs.");
    }

    @Override
    public void scrum() {
        System.out.println("Forming a scrum in rugby sevens: 3 players per side.");
    }

}
