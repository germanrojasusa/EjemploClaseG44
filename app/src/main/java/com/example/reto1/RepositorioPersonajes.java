package com.example.reto1;

public interface RepositorioPersonajes {

    public Personaje getPersonajeById(int id);
    public int editar(int id, Personaje personaje);
    public int nuevo(Personaje personaje);
    public void borrar(int id);

    //Crea ejemplos automáticos simulados
    void creaEjemplos();

    //public <List>Personaje personajes();

}
