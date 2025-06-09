package com.example.sports.team.football.variants.street;
import com.example.sports.team.football.core.StreetFootball;

public class FreestyleFootball implements StreetFootball {
    @Override
    public void play() {
        System.out.println("Playing freestyle football: Individual or small groups performing tricks on urban surfaces.");
    }

    @Override
    public void score() {
        System.out.println("Scoring in freestyle football: Judged on creativity and difficulty of tricks.");
    }

    @Override
    public void endGame() {
        System.out.println("Freestyle football session ended after performance rounds.");
    }

    @Override
    public void SetTeamFormation() {
        System.out.println("Setting freestyle football formation: Solo or duo performers.");
    }

    @Override
    public void kickOff() {
        System.out.println("Freestyle football kick-off: Performer starts with a trick sequence.");
    }

    @Override
    public void setPlayStyle() {
        System.out.println("Setting freestyle play style: Creative tricks with flair and music.");
    }

    @Override
    public void setStreetType(String streetType) {
         System.out.println("Narrow Streets plus plus.");
   }
}