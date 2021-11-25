package com.example.reto1;

import java.util.ArrayList;
import java.util.List;

public class PersonajesLista implements RepositorioPersonajes {
    private List<Personaje> listaPersonajes;

    public PersonajesLista(){
        listaPersonajes = new ArrayList<Personaje>();
        creaEjemplos();
    }

    @Override
    public Personaje getPersonajeById(int id){
        return listaPersonajes.get(id);
    }

    @Override
    public int nuevo(Personaje personaje){
        listaPersonajes.add(personaje);
        return listaPersonajes.size()-1;
    }

    @Override
    public int editar(int id, Personaje personaje){
        listaPersonajes.set(id, personaje);
        return id;
    }

    @Override
    public void borrar(int id){
        listaPersonajes.remove(id);
    }

    @Override
    public void creaEjemplos() {
        int id = nuevo(new Personaje("Capitan Marvel", "Heroína con super poderes adquiridos en el espacio. Es considerada de las más fuertes"));
        id = nuevo(new Personaje("Thor", "Héroe Nórdico capáz de levantar un martillo legendario"));
        id = nuevo(new Personaje("Spiderman", "Héroe juvenil que tiene poderes de araña"));
        id = nuevo(new Personaje("Hulk", "Hombre extremdamente fuerte y verde","Súper fuerza", 10, 5));
        id = nuevo(new Personaje("Capitan América"));
    }


}
