package com.example.reto1.presentacion;

import static com.example.reto1.App.CANAL_ID_1;
import static com.example.reto1.App.CANAL_ID_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.reto1.R;
import com.example.reto1.datos.PersonajesLista;
import com.example.reto1.modelo.Personaje;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;

public class PersonajesListaActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "1";
    private TextView mensajePrueba;
    private ListView listView;
    private FloatingActionButton agregarNuevo;
    private NotificationManagerCompat notificationManager;
    private FormularioCrearPersonaje formularioCrearPersonaje;
    public Personaje personajeACrear;
    public Uri imagenACrear;

    private PersonajesLista personajesLista;
    private PersonajesAdaptador personajeAdaptador;
    private ArrayList<Personaje> personajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personajes_lista);


        //Se vista de lista desde el Layout
        listView = findViewById(R.id.listView);

        //Creamos la información para llenar en la lista
        personajesLista = new PersonajesLista(PersonajesListaActivity.this);


        //Obtenemos todos los personajes (de ejemplo)
        personajes = personajesLista.getAll();

        //Creamos un adaptador para vincular la información al ListView (usando list row)
        personajeAdaptador = new PersonajesAdaptador(this,R.layout.list_row,personajes);

        listView.setAdapter(personajeAdaptador);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Si estamos acá es por que se seleccionó alguna fila de nuestro ListView
                //Traemos la info del personaje seleccionado
                Personaje personajeSelected = personajesLista.getPersonajeByPosition(position);

                //Vamos a validar si se seleccionó el elemento de favorito o si es otro

                if (view.getId()==R.id.imagenFavorita){
                    //Si entra acá es por que quiere agregar o eliminar el elemento como favorito
                    if(personajeSelected.getFavorito()){
                        //TODO: Hacer un switcheo de la imagen del botón entre off y on
                        //TODO: Persistir el cambio de favorito en Base de datos
                    }
                }else {

                    Intent intentADetalle = new Intent(getApplicationContext(), PersonajesDetalleActivity.class);

                    //Para enviar información
                    intentADetalle.putExtra("idFromDBPersonaje", personajeSelected.getId());
                    Log.i(null, "Nombre: " + personajeSelected.getName() + " - " + " descripción: " + personajeSelected.getDescription() + " idFromDB: " + String.valueOf(personajeSelected.getId()));
                    startActivity(intentADetalle);
                    //Toast toast = Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT);
                    //toast.show();
                }
            }
        });

        /*Creación de nuevos personajes */

        //Se obtiene el botón flotante para agregar nuevos personajes
        agregarNuevo = findViewById(R.id.agregarNuevo);

        //Se configura el evento cuando se hace click en el botón para agregar a un personaje
        agregarNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Si estamos acá es por que se seleccionó el botón flotante para agregar a un personaje
                /*INICIO DE TIPOS DE MENSAJES, NOTIFICACIONES Y DIALOGOS*/
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
                formularioCrearPersonaje = new FormularioCrearPersonaje(PersonajesListaActivity.this,personajeAdaptador,personajesLista,personajes, null);
                formularioCrearPersonaje.setDialogResult(new FormularioCrearPersonaje.OnMyDialogResult(){
                    public void finish(Personaje personajeDelDialogo){
                        personajeACrear = personajeDelDialogo;
                        Log.i(null,"PERSONAJE A CREAR OBTENIDO (estoy en Activity): name - " + personajeACrear.getName());
                        //Al ser la primera vez de abrir el dialogo, obtendremos el personajeTemporal para ser creado
                    }
                });
                formularioCrearPersonaje.show();

                /*FIN DE TIPOS DE MENSAJES, NOTIFICACIONES Y DIALOGOS*/
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
    //Método para obtener la imagen agregada en el Dialog (cuando el usuario carga una imagen)
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
            case 1:
                if(resultCode == RESULT_OK){
                    imagenACrear = imageReturnedIntent.getData();

                    Bitmap imageBitMap = null;
                    try {
                        imageBitMap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imagenACrear);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    personajeACrear.setImageBitMap(imageBitMap);
                    formularioCrearPersonaje = new FormularioCrearPersonaje(PersonajesListaActivity.this,personajeAdaptador,personajesLista,personajes, personajeACrear);
                    formularioCrearPersonaje.setDialogResult(new FormularioCrearPersonaje.OnMyDialogResult(){
                        public void finish(Personaje personajeDelDialogo){
                            personajeACrear = personajeDelDialogo;
                            Log.i(null,"PERSONAJE A CREAR OBTENIDO (estoy en Activity): name - " + personajeACrear.getName());
                            //Al ser la primera vez de abrir el dialogo, obtendremos el personajeTemporal para ser creado
                        }
                    });
                    formularioCrearPersonaje.show();
                    //formularioCrearPersonaje.imagenPersonaje.setImageURI(selectedImage);
                }
                break;
        }
    }
}