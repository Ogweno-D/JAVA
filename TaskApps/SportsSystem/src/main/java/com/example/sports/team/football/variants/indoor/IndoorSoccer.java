package com.example.sports.team.football.variants.indoor;
import com.example.sports.team.football.core.IndoorFootball;

public class IndoorSoccer implements IndoorFootball {
    @Override
    public void play() {
        System.out.println("Playing indoor soccer: 6 players per team in an enclosed arena, fast-paced with wall rebounds.");
    }

    @Override
    public void score() {
        System.out.println("Scoring a goal in indoor soccer! Shot into a standard goalpost.");
    }

    @Override
    public void endGame() {
        System.out.println("Indoor soccer game ended after two 25-minute halves.");
    }


    @Override
    public void kickOff() {
        System.out.println("Indoor soccer kick-off: Ball starts at the center of the enclosed arena.");
    }

    @Override
    public void setCourtType() {
        System.out.println("Setting indoor soccer court: Artificial turf with surrounding walls.");
    }

    @Override
    public void SetTeamFormation() {
        System.out.println("Setting indoor soccer formation: 2-3-1 (goalkeeper, defenders, midfielders, forward).");
    }
}
