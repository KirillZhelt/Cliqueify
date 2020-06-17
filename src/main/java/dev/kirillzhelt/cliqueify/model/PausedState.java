package dev.kirillzhelt.cliqueify.model;

import javax.persistence.Entity;

@Entity
public class PausedState extends VideoState {

    private double elapsedTimeWhenPaused;

    public PausedState(double elapsedTimeWhenPaused) {
        this.elapsedTimeWhenPaused = elapsedTimeWhenPaused;
    }

    public PausedState() {}

    public double getElapsedTimeWhenPaused() {
        return elapsedTimeWhenPaused;
    }

    public void setElapsedTimeWhenPaused(double elapsedTimeWhenPaused) {
        this.elapsedTimeWhenPaused = elapsedTimeWhenPaused;
    }

    @Override
    public String toString() {
        return "PausedState{" +
                "elapsedTimeWhenPaused=" + elapsedTimeWhenPaused +
                ", id=" + id +
                '}';
    }
}
