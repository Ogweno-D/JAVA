package com.example.sports.team.football.variants.indoor;
import com.example.sports.team.football.core.IndoorFootball;

public class Futsal implements IndoorFootball {
    @Override
    public void play() {
        System.out.println("Playing futsal: 5 players per team on a 40m x 20m indoor court, emphasizing ball control.");
    }

    @Override
    public void score() {
        System.out.println("Scoring a goal in futsal! Ball kicked into a smaller goalpost.");
    }

    @Override
    public void endGame() {
        System.out.println("Futsal game ended after two 20-minute halves.");
    }


    @Override
    public void kickOff() {
        System.out.println("Futsal kick-off: Ball starts at the center of the indoor court.");
    }

    @Override
    public void setCourtType() {
        System.out.println("Setting futsal court: Hard surface with low-bounce ball.");
    }


    @Override
    public void SetTeamFormation() {
        System.out.println("Setting futsal formation: 1-2-1 (goalkeeper, defenders, attackers).");
    }
}