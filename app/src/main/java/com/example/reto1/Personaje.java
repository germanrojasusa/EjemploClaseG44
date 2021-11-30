package com.example.reto1;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Personaje implements Parcelable {
    private String name;
    private String description;
    private String power;
    private int strength;
    private int agility;
    private int imageId;

    //Constructor que puede inicializarse sólo con el nombre
    public Personaje(String name){
        this.name = name;
        this.description = "";
        this.imageId = R.drawable.sergio;
        this.power = "";
        this.strength = 0;
        this.agility = 0;
    }

    //Constructor que puede inicializarse con el nombre y la descripción
    public Personaje(String name, String description){
        this.name = name;
        this.description = description;
        this.imageId = R.drawable.sergio;
        this.power = "";
        this.strength = 0;
        this.agility = 0;
    }

    //Constructor que inicializa Personaje con todos los datos
    public Personaje(String name, String description, int imageId, String power, int strength, int agility){
        this.name = name;
        this.description = description;
        this.imageId = imageId;
        this.power = power;
        this.strength = strength;
        this.agility = agility;
    }

    //Constructor especial que se genera de forma automático cuando se implementa la Interfaz de
    // Parcelable (para poder enviar este tipo de objetos entre actividades)
    protected Personaje(Parcel in) {
        name = in.readString();
        description = in.readString();
        power = in.readString();
        strength = in.readInt();
        agility = in.readInt();
        imageId = in.readInt();
    }

    //Método generado automáticamente cuando se implementa la interfaz de Parcelable
    public static final Creator<Personaje> CREATOR = new Creator<Personaje>() {
        @Override
        public Personaje createFromParcel(Parcel in) {
            return new Personaje(in);
        }

        @Override
        public Personaje[] newArray(int size) {
            return new Personaje[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(power);
        dest.writeInt(strength);
        dest.writeInt(agility);
        dest.writeInt(imageId);
    }
}
