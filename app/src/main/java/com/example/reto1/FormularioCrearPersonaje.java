package com.example.reto1;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

//Para personalizr un Dialog, es necesario extender la clase de Dialog y agregar los elementos que se necesiten
public class FormularioCrearPersonaje extends Dialog implements
        android.view.View.OnClickListener {

    //Se definen elementos gráficos
    public Activity activity;
    public Dialog dialog;
    public Button btn_crear, btn_cancelar;

    //Elementos de diseño que capturan la información de nuevos personajes
    public TextView form_nombre;
    public TextView form_descripcion;
    public TextView form_poder;
    public TextView form_fuerza;
    public TextView form_agilidad;


    public PersonajesAdaptador personajeAdaptador;
    private ArrayList<Personaje> personajes;
    private PersonajesLista personajesLista;


    public FormularioCrearPersonaje(Activity activity, PersonajesAdaptador personajeAdaptador, PersonajesLista personajesLista, ArrayList<Personaje> personajes) {
        super(activity);
        this.activity = activity;
        this.personajeAdaptador = personajeAdaptador;
        this.personajesLista = personajesLista;
        this.personajes = personajes;
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

        //Setear eventos al click en ambos botones
        btn_crear.setOnClickListener(this);
        btn_cancelar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_crear:
                //Captura de datos agregados por el usuario
                String nombre = (String) form_nombre.getText().toString();
                String descripcion = (String) form_descripcion.getText().toString();
                String poder = (String) form_poder.getText().toString();
                String fuerza_str = (String) form_fuerza.getText().toString();
                String agilidad_str = (String) form_agilidad.getText().toString();

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

                this.personajesLista.nuevo(new Personaje(nombre,descripcion,R.drawable.ironman,poder,fuerza,agilidad));

                //this.personajesLista.nuevo(new Personaje("Ironman #"+this.personajes.size(),"Millonario, genio y filántropo con un armadura muy poderosa",R.drawable.ironman,"Volar y disparar con su traje",8,6));
                this.personajes = personajesLista.getAll();
                this.personajeAdaptador.notifyDataSetChanged();
                dismiss();
                break;
            case R.id.btn_cancelar:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}