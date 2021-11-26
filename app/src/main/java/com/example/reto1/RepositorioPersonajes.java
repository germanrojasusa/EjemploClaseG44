package com.example.reto1;

import java.util.ArrayList;

public interface RepositorioPersonajes {

    public Personaje getPersonajeById(int id);

    //Edita un personaje
    public int editar(int id, Personaje personaje);

    //Crea un personaje
    public void nuevo(Personaje personaje);

    //Borra un personaje
    public void borrar(int id);

    //Crea ejemplos automáticos simulados
    void creaEjemplos();

    //Obtiene todo el listado de personajes
    public ArrayList<Personaje> getAll();

}