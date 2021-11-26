package com.example.reto1;

public class Personaje {
    private String name;
    private String description;
    private String power;
    private int strength;
    private int agility;
    private int imageId;

    public Personaje(String name){
        this.name = name;
    }
    public Personaje(String name, String description){
        this.name = name;
        this.description = description;
    }

    public Personaje(String name, String description, int imageId, String power, int strength, int agility){
        this.name = name;
        this.description = description;
        this.imageId = imageId;
        this.power = power;
        this.strength = strength;
        this.agility = agility;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAgility() {
        return agility;
    }

    public int getStrength() {
        return strength;
    }
    public String getPower() {
        return power;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
