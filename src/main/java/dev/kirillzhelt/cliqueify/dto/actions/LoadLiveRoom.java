package dev.kirillzhelt.cliqueify.dto.actions;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.kirillzhelt.cliqueify.dto.actions.processors.LiveRoomActionProcessor;
import dev.kirillzhelt.cliqueify.dto.actions.processors.LoadActionProcessor;

import java.time.LocalDateTime;

public class LoadLiveRoom implements LiveRoomAction {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startedToPlayTime;

    private String videoId;

    public LoadLiveRoom(String videoId) {
        this.videoId = videoId;
    }

    public LoadLiveRoom() {}

    @Override
    public LiveRoomActionProcessor buildProcessor() {
        return new LoadActionProcessor(this);
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public LocalDateTime getStartedToPlayTime() {
        return startedToPlayTime;
    }

    public void setStartedToPlayTime(LocalDateTime startedToPlayTime) {
        this.startedToPlayTime = startedToPlayTime;
    }

    @Override
    public String toString() {
        return "LoadLiveRoom{" +
                "startedToPlayTime=" + startedToPlayTime +
                ", videoId='" + videoId + '\'' +
                '}';
    }
}
