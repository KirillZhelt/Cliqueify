package dev.kirillzhelt.cliqueify.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@Entity
public class VideoState extends IdentifiedModel {

    @OneToOne(mappedBy = "state")
    protected CurrentVideo video;

    public CurrentVideo getVideo() {
        return video;
    }

    public void setVideo(CurrentVideo video) {
        this.video = video;
    }
}
