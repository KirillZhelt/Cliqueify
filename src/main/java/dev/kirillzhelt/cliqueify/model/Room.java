package dev.kirillzhelt.cliqueify.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Room extends IdentifiedModel {

    private String name;

    @Enumerated(EnumType.ORDINAL)
    private PublicityType publicityType;

    private LocalDate expiryDate;

    @ManyToOne(optional = false)
    private User owner;

    private String linkToken;

    public Room() {
    }

    public Room(String name, PublicityType publicityType, LocalDate expiryDate, User owner) {
        this.name = name;
        this.publicityType = publicityType;
        this.expiryDate = expiryDate;
        this.owner = owner;
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

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", publicityType=" + publicityType +
                ", expiryDate=" + expiryDate +
                ", owner=" + owner +
                ", linkToken='" + linkToken + '\'' +
                ", id=" + id +
                '}';
    }
}
