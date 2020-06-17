package dev.kirillzhelt.cliqueify.model;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class PlayingState extends VideoState {

    private LocalDateTime startedToPlayTime;
    private double elapsedTime;

    public PlayingState(LocalDateTime startedToPlayTime, double elapsedTime) {
        this.startedToPlayTime = startedToPlayTime;
        this.elapsedTime = elapsedTime;
    }

    public PlayingState() {}

    public LocalDateTime getStartedToPlayTime() {
        return startedToPlayTime;
    }

    public void setStartedToPlayTime(LocalDateTime startedToPlayTime) {
        this.startedToPlayTime = startedToPlayTime;
    }

    public double getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(double elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    @Override
    public String toString() {
        return "PlayingState{" +
                "startedToPlayTime=" + startedToPlayTime +
                ", elapsedTime=" + elapsedTime +
                ", id=" + id +
                '}';
    }
}
