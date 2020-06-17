package dev.kirillzhelt.cliqueify.dto.actions;

import dev.kirillzhelt.cliqueify.dto.actions.processors.LiveRoomActionProcessor;
import dev.kirillzhelt.cliqueify.dto.actions.processors.PauseActionProcessor;

public class PauseLiveRoom implements LiveRoomAction {

    private double elapsedTimeWhenPaused;
    private String videoId;

    public PauseLiveRoom(double elapsedTimeWhenPaused) {
        this.elapsedTimeWhenPaused = elapsedTimeWhenPaused;
    }

    public PauseLiveRoom() {}

    @Override
    public LiveRoomActionProcessor buildProcessor() {
        return new PauseActionProcessor(this);
    }

    public double getElapsedTimeWhenPaused() {
        return elapsedTimeWhenPaused;
    }

    public void setElapsedTimeWhenPaused(double elapsedTimeWhenPaused) {
        this.elapsedTimeWhenPaused = elapsedTimeWhenPaused;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    @Override
    public String toString() {
        return "PauseLiveRoom{" +
                "elapsedTimeWhenPaused=" + elapsedTimeWhenPaused +
                ", videoId='" + videoId + '\'' +
                '}';
    }
}
