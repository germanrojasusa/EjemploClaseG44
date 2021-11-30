package com.example.reto1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PersonajesDetalleActivity extends AppCompatActivity {
    private TextView name;
    private TextView description;
    private TextView power;
    private ImageView image;
    private ProgressBar strength;
    private ProgressBar agility;

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


        // Traemos la información que viene desde el ListView del Personaje seleccionado
        Personaje personaje;
        personaje = (Personaje) getIntent().getParcelableExtra("personajeSelected");

        //Setear la información en los componentes gráficos (Vistas: textos, ProgressBar)
        name.setText(personaje.getName());
        power.setText(personaje.getPower());
        description.setText(personaje.getDescription());
        image.setImageResource(personaje.getImageId());
        strength.setProgress(10*personaje.getStrength());
        agility.setProgress(10*personaje.getAgility());


    }
}