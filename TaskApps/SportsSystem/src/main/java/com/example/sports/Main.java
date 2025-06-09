package com.example.sports;

import com.example.sports.core.Sport;
import com.example.sports.individual.swimming.variants.OpenWaterSwimming;
import com.example.sports.individual.swimming.variants.SynchronizedSwimming;
import com.example.sports.team.football.variants.indoor.Futsal;
import com.example.sports.team.football.core.IndoorFootball;
import com.example.sports.team.football.variants.indoor.IndoorSoccer;
import com.example.sports.team.football.variants.outdoor.BeachFootball;
import com.example.sports.team.football.core.OutdoorFootball;
import com.example.sports.team.football.variants.outdoor.TraditionalFootball;
import com.example.sports.team.football.variants.street.FreestyleFootball;
import com.example.sports.team.football.variants.street.PannaFootball;
import com.example.sports.team.football.core.StreetFootball;
import com.example.sports.team.rugby.variants.RugbySevens;
import com.example.sports.team.rugby.variants.TouchRugby;

public class Main {
    public static void main(String[] args) {
        // Football Variants
        System.out.println("=== Indoor Football: Futsal ===");
        IndoorFootball futsal = new Futsal();
        futsal.startGame();
        Sport.displayRules();
        futsal.setCourtType();
        futsal.SetTeamFormation();
        futsal.kickOff();
        futsal.play();
        futsal.score();
        futsal.endGame();

        System.out.println("\n=== Indoor Football: Indoor Soccer ===");
        IndoorFootball indoorSoccer = new IndoorSoccer();
        indoorSoccer.startGame();
        Sport.displayRules();
        indoorSoccer.setCourtType();
        indoorSoccer.SetTeamFormation();
        indoorSoccer.kickOff();
        indoorSoccer.play();
        indoorSoccer.score();
        indoorSoccer.endGame();

        System.out.println("\n=== Outdoor Football: Beach Football ===");
        OutdoorFootball beachFootball = new BeachFootball();
        beachFootball.startGame();
        Sport.displayRules();
        beachFootball.setFieldCondition();
        beachFootball.SetTeamFormation();
        beachFootball.kickOff();
        beachFootball.play();
        beachFootball.score();
        beachFootball.endGame();

        System.out.println("\n=== Outdoor Football: Traditional Football ===");
        OutdoorFootball traditionalFootball = new TraditionalFootball();
        traditionalFootball.startGame();
        Sport.displayRules();
        traditionalFootball.setFieldCondition();
        traditionalFootball.SetTeamFormation();
        traditionalFootball.kickOff();
        traditionalFootball.play();
        traditionalFootball.score();
        traditionalFootball.endGame();

        System.out.println("\n=== Street Football: Freestyle Football ===");
        StreetFootball freestyleFootball = new FreestyleFootball();
        freestyleFootball.startGame();
        Sport.displayRules();
        freestyleFootball.setPlayStyle();
        freestyleFootball.SetTeamFormation();
        freestyleFootball.kickOff();
        freestyleFootball.play();
        freestyleFootball.score();
        freestyleFootball.endGame();

        System.out.println("\n=== Street Football: Panna Football ===");
        StreetFootball pannaFootball = new PannaFootball();
        pannaFootball.startGame();
        Sport.displayRules();
        pannaFootball.setPlayStyle();
        pannaFootball.SetTeamFormation();
        pannaFootball.kickOff();
        pannaFootball.play();
        pannaFootball.score();
        pannaFootball.endGame();

        // Rugby Variants (Team Sport Extension)
        System.out.println("\n=== Rugby: Rugby Sevens ===");
        RugbySevens rugbySevens = new RugbySevens();
        rugbySevens.startGame();
        Sport.displayRules();
        rugbySevens.SetTeamFormation();
        rugbySevens.scrum();
        rugbySevens.play();
        rugbySevens.score();
        rugbySevens.endGame();

        System.out.println("\n=== Rugby: Touch Rugby ===");
        TouchRugby touchRugby = new TouchRugby();
        touchRugby.startGame();
        Sport.displayRules();
        touchRugby.SetTeamFormation();
        touchRugby.scrum();
        touchRugby.play();
        touchRugby.score();
        touchRugby.endGame();

        // Swimming Variants (Individual Sport Extension)
        System.out.println("\n=== Swimming: Open Water Swimming ===");
        OpenWaterSwimming openWater = new OpenWaterSwimming();
        openWater.startGame();
        Sport.displayRules();
        openWater.setTrainingRegimen();
        openWater.setStrokeType();
        openWater.play();
        openWater.score();
        openWater.endGame();

        System.out.println("\n=== Swimming: Synchronized Swimming ===");
        SynchronizedSwimming synchronizedSwimming = new SynchronizedSwimming();
        synchronizedSwimming.startGame();
        Sport.displayRules();
        synchronizedSwimming.setTrainingRegimen();
        synchronizedSwimming.setStrokeType();
        synchronizedSwimming.play();
        synchronizedSwimming.score();
        synchronizedSwimming.endGame();
    }
}