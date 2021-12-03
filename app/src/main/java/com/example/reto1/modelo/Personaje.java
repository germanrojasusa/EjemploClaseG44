package com.example.reto1.modelo;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.reto1.R;

public class Personaje implements Parcelable {
    private long id;
    private String name;
    private String description;
    private String power;
    private int strength;
    private int agility;
    private int imageId;
    private Bitmap imageBitMap;
    private Boolean favorito;

    //Constructor que inicializa Personaje con todos los datos y usando el id de imagen en drawable
    public Personaje(String name, String description, int imageId, String power, int strength, int agility){
        this.id = 0;
        this.name = name;
        this.description = description;
        this.imageId = imageId;
        this.power = power;
        this.strength = strength;
        this.agility = agility;
        this.favorito = false;
    }

    //Constructor que inicializa Personaje con todos los datos usando Bitmap Image (de base de datos)
    public Personaje(String name, String description, Bitmap imageBitMap, String power, int strength, int agility){
        this.id = 0;
        this.name = name;
        this.description = description;
        this.imageBitMap = imageBitMap;
        this.imageId = R.drawable.sergio;
        this.power = power;
        this.strength = strength;
        this.agility = agility;
        this.favorito = false;
    }
    //Constructor que recibe Bitmap como imagen
    public Personaje(String name, String description, Bitmap imageBitMap, String power, int strength, int agility, long idFromDB) {
        this.id = idFromDB;
        this.name = name;
        this.description = description;
        this.imageBitMap = imageBitMap;
        this.imageId = R.drawable.sergio;
        this.power = power;
        this.strength = strength;
        this.agility = agility;
        this.favorito = false;
    }

    //Constructor especial que se genera de forma automático cuando se implementa la Interfaz de
    // Parcelable (para poder enviar este tipo de objetos entre actividades)
    protected Personaje(Parcel in) {
        id = in.readLong();
        name = in.readString();
        description = in.readString();
        power = in.readString();
        strength = in.readInt();
        agility = in.readInt();
        imageId = in.readInt();
        imageBitMap = in.readParcelable(Bitmap.class.getClassLoader());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            favorito = in.readBoolean();
        }
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
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(power);
        dest.writeInt(strength);
        dest.writeInt(agility);
        dest.writeInt(imageId);
        dest.writeParcelable(imageBitMap, flags);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            dest.writeBoolean(favorito);
        }
    }

    public Bitmap getImageBitMap() {
        return imageBitMap;
    }

    public void setImageBitMap(Bitmap imageBitMap) {
        this.imageBitMap = imageBitMap;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Boolean getFavorito() {
        return favorito;
    }

    public void setFavorito(Boolean favorito) {
        this.favorito = favorito;
    }
}
