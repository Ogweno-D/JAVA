package  com.example.sports.individual.swimming.variants;

import com.example.sports.individual.swimming.core.Swimming;

public class OpenWaterSwimming implements Swimming {
    @Override
    public void play() {
        System.out.println("Competing in open water swimming: Long-distance in natural waters.");
    }

    @Override
    public void score() {
        System.out.println("Scoring in open water swimming: Fastest time wins.");
    }

    @Override
    public void endGame() {
        System.out.println("Open water swimming race ended after 10km.");
    }

    @Override
    public void setTrainingRegimen() {
        System.out.println("Setting training regimen: Endurance swimming and navigation practice.");
    }

    @Override
    public void setStrokeType() {
        System.out.println("Setting stroke: Freestyle for open water efficiency.");
    }
}