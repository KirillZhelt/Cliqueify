package dev.kirillzhelt.cliqueify.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Room extends IdentifiedModel {

    private String name;

    @Enumerated(EnumType.ORDINAL)
    private PublicityType publicityType;

    private LocalDate expiryDate;

    @ManyToOne(optional = false)
    private User owner;

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

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", publicityType=" + publicityType +
                ", expiryDate=" + expiryDate +
                ", owner=" + owner +
                ", id=" + id +
                '}';
    }
}
