package dev.kirillzhelt.cliqueify.dto;

import dev.kirillzhelt.cliqueify.model.PublicityType;

import java.time.LocalDate;

public class RoomCreationParamsDTO {

    private String name;

    private PublicityType publicityType;

    private LocalDate expiryDate;

    public RoomCreationParamsDTO() {
    }

    public RoomCreationParamsDTO(String name, PublicityType publicityType, LocalDate expiryDate) {
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
        return "RoomCreationParamsDTO{" +
                "name='" + name + '\'' +
                ", publicityType=" + publicityType +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
