package com.example.sports.team.football.variants.outdoor;
import com.example.sports.team.football.core.OutdoorFootball;

public class TraditionalFootball implements OutdoorFootball {
    @Override
    public void play() {
        System.out.println("Playing traditional football: 11 players per team on a grass pitch, strategic and physical.");
    }

    @Override
    public void score() {
        System.out.println("Scoring a goal in traditional football! Ball kicked into a standard goalpost.");
    }

    @Override
    public void endGame() {
        System.out.println("Traditional football game ended after two 45-minute halves.");
    }

    @Override
    public void kickOff() {
        System.out.println("Traditional football kick-off: Ball starts at the center of the 105m x 68m pitch.");
    }

    @Override
    public void setFieldCondition() {
        System.out.println("Setting traditional football field: Grass or artificial turf, standard dimensions.");
    }

    @Override
    public void setFieldType() {
        System.out.println("Setting traditional football field: Grass or artificial turf, standard dimensions.");
    }

    @Override
    public void SetTeamFormation() {
        System.out.println("Setting traditional football formation: 4-4-2 (goalkeeper, defenders, midfielders, forwards).");

    }
}