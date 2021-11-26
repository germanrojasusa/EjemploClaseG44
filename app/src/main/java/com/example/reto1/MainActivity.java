package com.example.reto1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    //Variables de ejemplo para guardar variables
    private Boolean storeBoolean = false;
    private int storeInt = 0;
    private String storeString = "";


    private TextView miTexto;
    private Button botonSuma;
    private Button botonSiguiente;
    private TextView num1;
    private TextView num2;
    private Number suma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        suma = 0;
        botonSuma = (Button) findViewById(R.id.botonSuma);
        botonSiguiente = (Button) findViewById(R.id.botonSiguiente);
        miTexto = (TextView) findViewById(R.id.miTexto);

        num1 = (TextView) findViewById(R.id.num1);
        num2 = (TextView) findViewById(R.id.num2);

        //miTexto.setText("Nombre: " + testPersonaje.getName());

        /*
        new AlertDialog.Builder(this)
                .setTitle("Atención!")
                .setMessage("Soy una alerta para mostrar valores: \nBooleano:"+storeBoolean+"\nInt:"+storeInt+"\nString:"+storeString)
                .show();
         */
        //Evento cuando se hace click sobre el botón botonSuma
        botonSuma.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Presionamos un botón", Toast.LENGTH_SHORT).show();
                //miTexto.setText("Cambiando texto");
                String valor1 = num1.getText().toString();
                String valor2 = num2.getText().toString();
                int nro1=0;
                int nro2=0;
                try{
                    nro1 = Integer.parseInt(valor1);
                } catch(NumberFormatException ex){ // handle your exception
                    nro1 = 0;
                }
                try{
                    nro2 = Integer.parseInt(valor2);
                } catch(NumberFormatException ex){ // handle your exception
                    nro2 = 0;
                }

                suma = nro1 + nro2;
                String resu = String.valueOf(suma);
                miTexto.setText(resu);

            }
        });

        //Evento al hacer click en el botón botonSiguiente
        botonSiguiente.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentASiguiente = new Intent(getApplicationContext(), MainActivity3.class);
                startActivity(intentASiguiente);
            }
        });

    }

    //Ciclo de vida para creación de menú
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.opcionesmenu, menu);
        return true;
    }

    //Eventos para capturar cuando un item se selecciona en un menú
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        //Esto es verdad si el item seleccionado es el primero (con id item1)
        if (id==R.id.item1){
            Toast.makeText(this, "Presionamos la opción 1", Toast.LENGTH_SHORT).show();
            miTexto.setText("Opción 1");

        }
        //Esto es verdad si el item seleccionado es el segundo (con id item2)
        if (id==R.id.item2){
            Toast.makeText(this, "Presionamos la opción 2", Toast.LENGTH_SHORT).show();
            miTexto.setText("Opción 2");

            Intent intentASiguiente = new Intent(getApplicationContext(), MainActivity3.class);
            String sumaString = suma.toString();
            //Para enviar información
            intentASiguiente.putExtra("textoPrueba", "El resultado de la suma fue: " + sumaString);

            startActivity(intentASiguiente);

        }
        //Esto es verdad si el item seleccionado es el tercero (con id item3)
        if (id==R.id.item3){
            Toast.makeText(this, "Presionamos la opción 3", Toast.LENGTH_SHORT).show();
            miTexto.setText("Opción 3");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean("llaveBooleanEj", true);
        outState.putInt("llaveIntEj", storeInt+1);
        outState.putString("llaveStringEj", storeString + "-");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        storeBoolean = savedInstanceState.getBoolean("llaveBooleanEj");
        storeInt = savedInstanceState.getInt("llaveIntEj");
        storeString = savedInstanceState.getString("llaveStringEj");
    }
}