package com.example.reto1.presentacion;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reto1.R;
import com.example.reto1.datos.PersonajesLista;
import com.example.reto1.modelo.Personaje;

import java.util.ArrayList;

//Para personalizr un Dialog, es necesario extender la clase de Dialog y agregar los elementos que se necesiten
public class FormularioCrearPersonaje extends Dialog implements
        android.view.View.OnClickListener {


    //Se definen elementos gráficos
    public Activity activity;
    public Dialog dialog;
    public Button btn_crear, btn_cancelar;

    OnMyDialogResult mDialogResult;


    //Elementos de diseño que capturan la información de nuevos personajes
    public TextView form_nombre;
    public TextView form_descripcion;
    public TextView form_poder;
    public TextView form_fuerza;
    public TextView form_agilidad;
    public ImageView imagenPersonaje;

    //
    private static final int RESULT_LOAD_IMAGE=1;
    private Personaje personajeACrear;

    //Elementos para estar sincronizados con los datos y la vista de lista
    public PersonajesAdaptador personajeAdaptador;
    private PersonajesLista personajesLista;
    private ArrayList<Personaje> personajes;

    public FormularioCrearPersonaje(Activity activity, PersonajesAdaptador personajeAdaptador, PersonajesLista personajesLista, ArrayList<Personaje> personajes, Personaje personajeACrear) {
        super(activity);
        this.activity = activity;
        this.personajeAdaptador = personajeAdaptador;
        this.personajesLista = personajesLista;
        this.personajes = personajes;
        this.personajeACrear = personajeACrear;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.form_crear_personaje);

        //Inicializando los botónes
        btn_crear = (Button) findViewById(R.id.btn_crear);
        btn_cancelar = (Button) findViewById(R.id.btn_cancelar);

        //Inicializando los textos:
        this.form_nombre = (TextView) findViewById(R.id.editTextNombre);
        this.form_descripcion = (TextView) findViewById(R.id.editTextDescripcion);
        this.form_poder = (TextView) findViewById(R.id.editTextPoder);
        this.form_fuerza = (TextView) findViewById(R.id.editTextFuerza);
        this.form_agilidad = (TextView) findViewById(R.id.editTextAgilidad);
        this.imagenPersonaje = (ImageView) findViewById(R.id.imagenPersonaje);

        //Inicialización de información si había llenado información previa del personaje a crear:
        if (this.personajeACrear != null) {

            this.form_nombre.setText(this.personajeACrear.getName());
            this.form_descripcion.setText(this.personajeACrear.getDescription());
            this.form_poder.setText(this.personajeACrear.getPower());

            if (Integer.valueOf(this.personajeACrear.getStrength()) != null) {
                this.form_fuerza.setText(String.valueOf(this.personajeACrear.getStrength()));
            }
            if (Integer.valueOf(this.personajeACrear.getAgility()) != null) {
                this.form_agilidad.setText(String.valueOf(this.personajeACrear.getAgility()));
            }

            if (this.personajeACrear.getImageBitMap() != null) {
                this.imagenPersonaje.setImageBitmap(this.personajeACrear.getImageBitMap());
            }
        }

        //Setear eventos al click en ambos botones
        btn_crear.setOnClickListener(this);
        btn_cancelar.setOnClickListener(this);

        //Setear eventos al click de la imagen
        imagenPersonaje.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Log.i(null, "Estamos en el click");

        //Captura de datos agregados por el usuario
        String nombre = (String) form_nombre.getText().toString();
        String descripcion = (String) form_descripcion.getText().toString();
        String poder = (String) form_poder.getText().toString();
        String fuerza_str = (String) form_fuerza.getText().toString();
        String agilidad_str = (String) form_agilidad.getText().toString();

        BitmapDrawable imagenDrawable= (BitmapDrawable) imagenPersonaje.getDrawable();
        Bitmap imagenBitmap = imagenDrawable.getBitmap();

        //Se convierte la fuerza y agilidad a enteros
        int fuerza;
        int agilidad;

        try{
            fuerza = Integer.parseInt(fuerza_str);
        } catch(NumberFormatException ex){ // handle your exception
            fuerza = 0;
        }
        try{
            agilidad = Integer.parseInt(agilidad_str);
        } catch(NumberFormatException ex){ // handle your exception
            agilidad = 0;
        }

        this.personajeACrear = new Personaje(nombre,descripcion,imagenBitmap,poder,fuerza,agilidad);

        Log.i(null, "Estamos obteniendo info (Estoy en dialog) - nombre: " + personajeACrear.getName());

        switch (view.getId()) {
            case R.id.btn_crear:
                //Si entra acá es por que debe crearse
                this.personajesLista.insertarPersonaje(this.personajeACrear);

                //Se actualizan los listados en caaso que se cree
                this.personajes = personajesLista.getAll();
                this.personajeAdaptador.notifyDataSetChanged();
                this.personajeACrear = null;
                break;
            case R.id.btn_cancelar:
                break;
            case R.id.imagenPersonaje:

                if (mDialogResult != null ){
                    Log.i(null, "Entró al != null");
                    mDialogResult.finish(this.personajeACrear);
                }
                Intent seleccionarImagen = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activity.startActivityForResult(seleccionarImagen, 1);

                //Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //activity.startActivityForResult(takePicture, 0);//zero can be replaced with any action code (called requestCode)

                break;
                default:
                break;
        }

        dismiss();
    }
    public void setDialogResult(OnMyDialogResult dialogResult){
        mDialogResult = dialogResult;
    }

    public interface OnMyDialogResult{
        void finish(Personaje personajeACrear);
    }
}