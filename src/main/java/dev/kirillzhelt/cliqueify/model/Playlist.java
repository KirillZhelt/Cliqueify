package dev.kirillzhelt.cliqueify.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Playlist extends IdentifiedModel {

    @ElementCollection
    private List<String> videoIds = new ArrayList<>();

    @OneToOne(mappedBy = "playlist")
    private Room room;

    public Playlist() {}

    public Playlist(List<String> videoIds) {
        this.videoIds = videoIds;
    }

    public List<String> getVideoIds() {
        return videoIds;
    }

    public void setVideoIds(List<String> videoIds) {
        this.videoIds = videoIds;
    }

    public boolean addVideo(String videoId) {
        return this.videoIds.add(videoId);
    }

    public boolean removeVideo(String videoId) {
        return this.videoIds.remove(videoId);
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "videoIds=" + videoIds +
                ", id=" + id +
                '}';
    }
}
