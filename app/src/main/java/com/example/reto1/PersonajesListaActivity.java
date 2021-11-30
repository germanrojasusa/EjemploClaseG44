package com.example.reto1;

import static com.example.reto1.App.CANAL_ID_1;
import static com.example.reto1.App.CANAL_ID_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class PersonajesListaActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "1";
    private TextView mensajePrueba;
    private ListView listView;
    private FloatingActionButton agregarNuevo;
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personajes_lista);



        //Se vista de lista desde el Layout
        listView = findViewById(R.id.listView);

        //Creamos la información para llenar en la lista
        PersonajesLista personajesLista = new PersonajesLista();

        ArrayList<Personaje> personajes;
        //Obtenemos todos los personajes (de ejemplo)
        personajes = personajesLista.getAll();

        //Creamos un adaptador para vincular la información al ListView (usando list row)
        PersonajesAdaptador personajeAdaptador = new PersonajesAdaptador(this,R.layout.list_row,personajes);

        listView.setAdapter(personajeAdaptador);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Si estamos acá es por que se seleccionó alguna fila de nuestro ListView
                //Traemos la info del personaje seleccionado
                Personaje personajeSelected = personajesLista.getPersonajeById(position);
                Intent intentADetalle = new Intent(getApplicationContext(), PersonajesDetalleActivity.class);

                //Para enviar información
                intentADetalle.putExtra("personajeSelected", personajeSelected);

                startActivity(intentADetalle);
                //Toast toast = Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT);
                //toast.show();
            }
        });

        /*Creación de nuevos personajes */

        //Se obtiene el botón flotante para agregar nuevos personajes
        agregarNuevo = findViewById(R.id.agregarNuevo);


        //Para notificaciones se requiere de un canal que se crea con el siguiente método

        //Se configura el evento cuando se hace click en el botón para agregar a un personaje
        agregarNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Si estamos acá es por que se seleccionó el botón flotante para agregar a un personaje

                /* TOAST
                    Toast toast = Toast.makeText(getApplicationContext(), "Soy un mensaje por Toast", Toast.LENGTH_LONG);
                toast.show();
                */

                /* //SNACKBAR
                //Imprimir un SnackBar (puede tener o no un botón de acción)
                Snackbar.make(view, "Ejemplo de SnackBar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                 */


                /* //NOTIFICACIONES
                notificationManager = NotificationManagerCompat.from(PersonajesListaActivity.this);
                sendOnChannel1();
                sendOnChannel2();
                */
                //Envío de notificaciones de prueba
                //Configuración de notificación

                /* Dialog personalizado

                 */
                FormularioCrearPersonaje formularioCrearPersonaje = new FormularioCrearPersonaje(PersonajesListaActivity.this,personajeAdaptador,personajesLista,personajes);
                formularioCrearPersonaje.show();
            }
        });

    }

    public void sendOnChannel1(){
        Notification notification = new NotificationCompat.Builder(this, CANAL_ID_1)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Título 1")
                .setContentText("Descripción completa de la notificación del canal 1")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManager.notify(1, notification);
    }
    public void sendOnChannel2(){
        Notification notification = new NotificationCompat.Builder(this, CANAL_ID_2)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Título 2")
                .setContentText("Descripción completa de la notificación del canal 2")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManager.notify(2, notification);
    }
}