package com.example.sports.team.football.variants.outdoor;
import com.example.sports.team.football.core.OutdoorFootball;

public class BeachFootball implements OutdoorFootball {
    @Override
    public void play() {
        System.out.println("Playing beach football: 5 players per team on a sandy beach, high-scoring and acrobatic.");
    }
    @Override
    public void score() {
        System.out.println("Scoring a goal in beach football! Acrobatic shot on sand.");
    }

    @Override
    public void endGame() {
        System.out.println("Beach football game ended after three 12-minute periods.");
    }

    @Override
    public void kickOff() {
        System.out.println("Beach football kick-off: Ball starts on the sandy center line.");
    }

    @Override
    public void setFieldCondition() {
        System.out.println("Setting beach football field: Soft sand, 36m x 27m.");
    }

    @Override
    public void setFieldType() {
        System.out.println("Setting beach football field: Soft sand, 36m x 27m.");
    }

    @Override
    public void SetTeamFormation() {
        System.out.println("Setting beach football formation: 1-3-1 (goalkeeper, midfielders, forward).");
    }
}