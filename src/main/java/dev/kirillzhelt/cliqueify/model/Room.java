package dev.kirillzhelt.cliqueify.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Entity
public class Room extends IdentifiedModel {

    private String name;

    @Enumerated(EnumType.ORDINAL)
    private PublicityType publicityType;

    private LocalDate expiryDate;

    public Room() {
    }

    public Room(String name, PublicityType publicityType, LocalDate expiryDate) {
        this.name = name;
        this.publicityType = publicityType;
        this.expiryDate = expiryDate;
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

    @Override
    public String toString() {
        return "Room{" +
                "id=" + this.getId() +
                ", name='" + name + '\'' +
                ", publicityType=" + publicityType +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
