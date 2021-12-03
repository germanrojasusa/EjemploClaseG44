package com.example.reto1.presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.reto1.R;
import com.example.reto1.datos.PersonajesLista;
import com.example.reto1.modelo.Personaje;

public class PersonajesDetalleActivity extends AppCompatActivity {
    private TextView name;
    private TextView description;
    private TextView power;
    private ImageView image;
    private ProgressBar strength;
    private ProgressBar agility;

    private PersonajesLista personajesLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personajes_detalle);

        //Mapeo de variables con el Layout gráfico (XML)
        name = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        power = (TextView) findViewById(R.id.power);
        image = (ImageView) findViewById(R.id.image);
        strength = (ProgressBar) findViewById(R.id.strength);
        agility = (ProgressBar) findViewById(R.id.agility);


        // Utilizamos el Id de base de datos del personaje para luego consultar toda la unfo de
        // nuestro personaje utilizando Personajes Lista
        long personajeId = (long) getIntent().getLongExtra("idFromDBPersonaje",0);

        personajesLista = new PersonajesLista(this);
        Personaje personaje;
        personaje = personajesLista.getPersonajeById(personajeId);

        //Setear la información en los componentes gráficos (Vistas: textos, ProgressBar)
        name.setText(personaje.getName());
        power.setText(personaje.getPower());
        description.setText(personaje.getDescription());
        strength.setProgress(10*personaje.getStrength());
        agility.setProgress(10*personaje.getAgility());

        if (personaje.getImageBitMap() != null){
            image.setImageBitmap(personaje.getImageBitMap());
        }else{
            image.setImageResource(personaje.getImageId());
        }

    }
}