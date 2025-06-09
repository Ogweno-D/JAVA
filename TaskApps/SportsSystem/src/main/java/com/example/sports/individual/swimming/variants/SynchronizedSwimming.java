package com.example.sports.individual.swimming.variants;

import com.example.sports.individual.swimming.core.Swimming;

public class SynchronizedSwimming implements Swimming {
    @Override
    public void play() {
        System.out.println("Performing synchronized swimming: Choreographed routines in the pool.");
    }

    @Override
    public void score() {
        System.out.println("Scoring in synchronized swimming: Judged on technical and artistic merit.");
    }

    @Override
    public void endGame() {
        System.out.println("Synchronized swimming performance ended after routine.");
    }

    @Override
    public void setTrainingRegimen() {
        System.out.println("Setting training regimen: Breath control and choreography practice.");
    }

    @Override
    public void setStrokeType() {
        System.out.println("Setting stroke: Combination of strokes for artistic performance.");
    }
}