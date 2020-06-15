package dev.kirillzhelt.cliqueify.dto.actions;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class PlayLiveRoom implements LiveRoomAction {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startedToPlayTime;
    private double elapsedTime;

    public PlayLiveRoom(LocalDateTime startedToPlayTime, double elapsedTime) {
        this.startedToPlayTime = startedToPlayTime;
        this.elapsedTime = elapsedTime;
    }

    public PlayLiveRoom() {}

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
        return "PlayLiveRoom{" +
                "startedToPlayTime=" + startedToPlayTime +
                ", elapsedTime=" + elapsedTime +
                '}';
    }
}
