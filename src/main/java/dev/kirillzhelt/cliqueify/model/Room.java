package dev.kirillzhelt.cliqueify.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(
    indexes = {
        @Index(name = "expiryDateIndex", columnList = "expiryDate"),
    })
public class Room extends IdentifiedModel {

    private String name;

    @Enumerated(EnumType.ORDINAL)
    private PublicityType publicityType;

    private LocalDate expiryDate;

    @ManyToOne(optional = false)
    private User owner;

    private String linkToken;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private CurrentVideo currentVideo;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Playlist playlist;

    public Room() {
        this.playlist = new Playlist();
    }

    public Room(String name, PublicityType publicityType, LocalDate expiryDate, User owner) {
        this.name = name;
        this.publicityType = publicityType;
        this.expiryDate = expiryDate;
        this.owner = owner;

        this.playlist = new Playlist();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PublicityType getPublicityType() {
        return publicityType;
    }

    public void setPublicityType(PublicityType publicityType) {
        this.publicityType = publicityType;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getLinkToken() {
        return linkToken;
    }

    public void setLinkToken(String linkToken) {
        this.linkToken = linkToken;
    }

    public CurrentVideo getCurrentVideo() {
        return currentVideo;
    }

    public void setCurrentVideo(CurrentVideo currentVideo) {
        this.currentVideo = currentVideo;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", publicityType=" + publicityType +
                ", expiryDate=" + expiryDate +
                ", owner=" + owner +
                ", linkToken='" + linkToken + '\'' +
                ", currentVideo=" + currentVideo +
                ", id=" + id +
                '}';
    }
}
