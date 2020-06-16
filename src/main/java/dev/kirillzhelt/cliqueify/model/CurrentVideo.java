package dev.kirillzhelt.cliqueify.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class CurrentVideo extends IdentifiedModel {

    private String videoId;

    @OneToOne(mappedBy = "currentVideo")
    private Room room;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private VideoState state;

    public CurrentVideo() {}

    public CurrentVideo(String videoId, VideoState state) {
        this.videoId = videoId;
        this.state = state;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public VideoState getState() {
        return state;
    }

    public void setState(VideoState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "CurrentVideo{" +
                "videoId='" + videoId + '\'' +
                ", state=" + state +
                ", id=" + id +
                '}';
    }
}
