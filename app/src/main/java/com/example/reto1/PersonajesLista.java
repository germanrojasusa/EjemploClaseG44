package com.example.reto1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PersonajesLista implements RepositorioPersonajes {
    private ArrayList<Personaje> listaPersonajes;

    public PersonajesLista(){
        listaPersonajes = new ArrayList<Personaje>();
        creaEjemplos();
    }

    public Personaje getPersonajeById(int id){
        return listaPersonajes.get(id);
    }

    public void nuevo(Personaje personaje){
        listaPersonajes.add(personaje);
    }

    public int editar(int id, Personaje personaje){
        listaPersonajes.set(id, personaje);
        return id;
    }

    public void borrar(int id){
        listaPersonajes.remove(id);
    }

    public void creaEjemplos() {
        nuevo(new Personaje("Capitan Marvel", "Heroína con super poderes adquiridos en el espacio. Es considerada de las más fuertes",R.drawable.captainmarvel,"Capacidad de volar y super fuerza",10,8));
        nuevo(new Personaje("Thor", "Héroe Nórdico capáz de levantar un martillo legendario",R.drawable.thor,"Control del trueno y fuerza",9,7));
        nuevo(new Personaje("Spiderman", "Héroe juvenil que tiene poderes de araña", R.drawable.spiderman, "Agilidad de araña", 7,10));
        nuevo(new Personaje("Hulk", "Hombre extremdamente fuerte y verde", R.drawable.hulk,"Súper fuerza", 10, 5));
        nuevo(new Personaje("Ironman","Millonario, genio y filántropo con un armadura muy poderosa",R.drawable.ironman,"Volar y disparar con su traje",8,6));
        nuevo(new Personaje("Capitan América"));
        nuevo(new Personaje("Capitan Marvel", "Heroína con super poderes adquiridos en el espacio. Es considerada de las más fuertes",R.drawable.captainmarvel,"Capacidad de volar y super fuerza",10,8));
        nuevo(new Personaje("Thor", "Héroe Nórdico capáz de levantar un martillo legendario",R.drawable.thor,"Control del trueno y fuerza",9,7));
        nuevo(new Personaje("Spiderman", "Héroe juvenil que tiene poderes de araña", R.drawable.spiderman, "Agilidad de araña", 7,10));
        nuevo(new Personaje("Hulk", "Hombre extremdamente fuerte y verde", R.drawable.hulk,"Súper fuerza", 10, 5));
        nuevo(new Personaje("Capitan Marvel", "Heroína con super poderes adquiridos en el espacio. Es considerada de las más fuertes",R.drawable.captainmarvel,"Capacidad de volar y super fuerza",10,8));
        nuevo(new Personaje("Thor", "Héroe Nórdico capáz de levantar un martillo legendario",R.drawable.thor,"Control del trueno y fuerza",9,7));
        nuevo(new Personaje("Spiderman", "Héroe juvenil que tiene poderes de araña", R.drawable.spiderman, "Agilidad de araña", 7,10));
        nuevo(new Personaje("Hulk", "Hombre extremdamente fuerte y verde", R.drawable.hulk,"Súper fuerza", 10, 5));
        nuevo(new Personaje("Capitan Marvel", "Heroína con super poderes adquiridos en el espacio. Es considerada de las más fuertes",R.drawable.captainmarvel,"Capacidad de volar y super fuerza",10,8));
        nuevo(new Personaje("Thor", "Héroe Nórdico capáz de levantar un martillo legendario",R.drawable.thor,"Control del trueno y fuerza",9,7));
        nuevo(new Personaje("Spiderman", "Héroe juvenil que tiene poderes de araña", R.drawable.spiderman, "Agilidad de araña", 7,10));
        nuevo(new Personaje("Hulk", "Hombre extremdamente fuerte y verde", R.drawable.hulk,"Súper fuerza", 10, 5));

    }

    public ArrayList<Personaje> getAll() {
        return listaPersonajes;
    }
}
