package dev.kirillzhelt.cliqueify.dto.actions;


import dev.kirillzhelt.cliqueify.dto.actions.processors.AddVideoActionProcessor;
import dev.kirillzhelt.cliqueify.dto.actions.processors.LiveRoomActionProcessor;

public class AddVideoLiveRoom implements LiveRoomAction {

    private String videoId;

    public AddVideoLiveRoom() {}

    public AddVideoLiveRoom(String videoId) {
        this.videoId = videoId;
    }

    @Override
    public LiveRoomActionProcessor buildProcessor() {
        return new AddVideoActionProcessor(this);
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    @Override
    public String toString() {
        return "AddVideoLiveRoom{" +
                "videoId='" + videoId + '\'' +
                '}';
    }
}
