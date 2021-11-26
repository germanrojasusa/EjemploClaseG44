package com.example.reto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    private TextView mensajePrueba;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        listView = findViewById(R.id.listView);


        //Creamos la información para llenar en la lista
        PersonajesLista personajesLista = new PersonajesLista();
        ArrayList<Personaje> personajes;

        personajes = personajesLista.getAll();

        //Creamos un adaptador
        PersonajesAdaptador personajeAdaptador = new PersonajesAdaptador(this,R.layout.list_row,personajes);

        listView.setAdapter(personajeAdaptador);

        /*
            personaje = personajesLista.getPersonajeById(key);
            personaje.getImageId();
            personaje.getName();
            personaje.getDescription();
            personaje.getAgility();
            personaje.getPower();
            personaje.getStrength();

         */

        //Obtenemos el dato enviado desde MainActivity1
        /*
        Bundle extras = getIntent().getExtras();
        String infoPrueba = extras.getString("textoPrueba");
        mensajePrueba = (TextView) findViewById(R.id.mensajePrueba);
        mensajePrueba.setText(infoPrueba);

         */

    }
}