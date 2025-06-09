package com.example.sports.team.rugby.variants;
import com.example.sports.team.rugby.core.Rugby;

public class TouchRugby implements Rugby {
    @Override
    public void play() {
        System.out.println("Playing touch rugby: 6 players per team, non-contact with touch tackles.");
    }

    @Override
    public void score() {
        System.out.println("Scoring a try in touch rugby! Ball grounded after touches.");
    }

    @Override
    public void endGame() {
        System.out.println("Touch rugby game ended after two 20-minute halves.");
    }

    @Override
    public void SetTeamFormation() {
        System.out.println("Setting touch rugby formation: 3 attackers, 3 defenders.");
    }

    @Override
    public void scrum() {
        System.out.println("No scrum in touch rugby; play restarts with a tap.");
    }
}