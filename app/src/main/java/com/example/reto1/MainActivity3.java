package com.example.reto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    private TextView mensajePrueba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        //Obtenemos el dato enviado desde MainActivity1
        Bundle extras = getIntent().getExtras();
        String infoPrueba = extras.getString("textoPrueba");
        mensajePrueba = (TextView) findViewById(R.id.mensajePrueba);

        mensajePrueba.setText(infoPrueba);

    }
}