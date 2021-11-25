package com.example.reto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    private ImageView imagen1;
    private TextView textView;
    private TextView textView2;
    private TextView textView3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.sergio, getTheme());
        imagen1 = (ImageView) findViewById(R.id.imageView);

        imagen1.setImageDrawable(drawable);

        Personaje personaje0;
        Personaje personaje1;
        Personaje personaje2;

        PersonajesLista personajes = new PersonajesLista();

        personaje0 = personajes.getPersonajeById(0);
        personaje1 = personajes.getPersonajeById(1);
        personaje2 = personajes.getPersonajeById(2);

        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);

        textView.setText(personaje0.getName());
        textView2.setText(personaje1.getName());
        textView3.setText(personaje2.getName());


    }
}