package dev.kirillzhelt.cliqueify.dto.actions;

public class PauseLiveRoom implements LiveRoomAction {

    private double elapsedTimeWhenPaused;

    public PauseLiveRoom(double elapsedTimeWhenPaused) {
        this.elapsedTimeWhenPaused = elapsedTimeWhenPaused;
    }

    public PauseLiveRoom() {}

    public double getElapsedTimeWhenPaused() {
        return elapsedTimeWhenPaused;
    }

    public void setElapsedTimeWhenPaused(double elapsedTimeWhenPaused) {
        this.elapsedTimeWhenPaused = elapsedTimeWhenPaused;
    }

    @Override
    public String toString() {
        return "PauseLiveRoom{" +
                "elapsedTimeWhenPaused=" + elapsedTimeWhenPaused +
                '}';
    }
}
