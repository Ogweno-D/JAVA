package com.example.sports.team.football.variants.street;

import com.example.sports.team.football.core.StreetFootball;

public class PannaFootball implements StreetFootball {
    @Override
    public void play() {
        System.out.println("Playing panna football: 1v1 on a small street cage, aiming to nutmeg the opponent.");
    }

    @Override
    public void score() {
        System.out.println("Scoring in panna football: Nutmeg (panna) or goal counts as a point.");
    }

    @Override
    public void endGame() {
        System.out.println("Panna football game ended after 3 minutes or first to 3 points.");
    }

  
    @Override
    public void SetTeamFormation(){
        System.out.println("Setting panna football formation: 1 player per side, focusing on individual skill.");
    }


    @Override
    public void kickOff() {
        System.out.println("Panna football kick-off: Ball starts with a toss in the cage center.");
    }

    @Override
    public void setPlayStyle() {
        System.out.println("Setting panna play style: Aggressive dribbling and nutmeg attempts.");
    }

    @Override
    public void setStreetType(String streetType) {
         System.out.println("Narrow Streets plus plus.");
   }

   
}