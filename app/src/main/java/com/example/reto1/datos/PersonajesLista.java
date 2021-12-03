package com.example.reto1.datos;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.reto1.R;
import com.example.reto1.datos.DbHelper;
import com.example.reto1.modelo.Personaje;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class PersonajesLista extends DbHelper {
    private ArrayList<Personaje> listaPersonajes;
    private Context context;
    private DbHelper dbHelper;
    private SQLiteDatabase db;
    public PersonajesLista(@Nullable Context context){
        super(context);
        this.context = context;
        this.dbHelper = new DbHelper(this.context);
        this.db = this.dbHelper.getWritableDatabase();

        listaPersonajes = new ArrayList<Personaje>();
        leerPersonajesDb();
        //creaEjemplos();
    }

    public Personaje getPersonajeByPosition(int positionId){
        return listaPersonajes.get(positionId);
    }

    public Personaje getPersonajeById(long id){
        for (Personaje personaje : listaPersonajes){
            if (personaje.getId()==id){
                return personaje;
            }
        }
        return null;
    }

    public void nuevo(Personaje personaje){
        listaPersonajes.add(personaje);
    }
    public Personaje agregar(Personaje personaje){
        listaPersonajes.add(personaje);
        return(personaje);
    }

    public long editarPersonaje(long id, Personaje personaje){
        try {
            ContentValues values;
            values = getValuesPersonaje(personaje);
            this.db.update(TABLE_PERSONAJES, values, "id=?",new String[]{String.valueOf(personaje.getId())});
            listaPersonajes.set((int) id, personaje);
        } catch (Exception exception){
            exception.toString();
        }

        return id;
    }

    public void borrar(int id){
        listaPersonajes.remove(id);
    }

    public void creaEjemplos() {
        nuevo(new Personaje("Capitan Marvel", "Heroína con super poderes adquiridos en el espacio. Es considerada de las más fuertes", R.drawable.captainmarvel,"Capacidad de volar y super fuerza",10,8));
        nuevo(new Personaje("Thor", "Héroe Nórdico capáz de levantar un martillo legendario",R.drawable.thor,"Control del trueno y fuerza",9,7));
        nuevo(new Personaje("Spiderman", "Héroe juvenil que tiene poderes de araña", R.drawable.spiderman, "Agilidad de araña", 7,10));
        nuevo(new Personaje("Hulk", "Hombre extremdamente fuerte y verde", R.drawable.hulk,"Súper fuerza", 10, 5));
        nuevo(new Personaje("Spiderman", "Héroe juvenil que tiene poderes de araña", R.drawable.spiderman, "Agilidad de araña", 7,10));
        nuevo(new Personaje("Hulk", "Hombre extremdamente fuerte y verde", R.drawable.hulk,"Súper fuerza", 10, 5));
    }
    //Método para leer los personajes desde la base de datos
    private void leerPersonajesDb() {
        Cursor personajesCursor = db.query(TABLE_PERSONAJES, new String[]{"id","nombre","descripcion","poder","fuerza","agilidad","imagen"},null,null,null,null,null);

        if(personajesCursor.moveToFirst()){
            do{
                //Asignamos la información de cada registro
                @SuppressLint("Range") long idFromDB = personajesCursor.getLong(personajesCursor.getColumnIndex("id"));
                @SuppressLint("Range") String nombre = personajesCursor.getString(personajesCursor.getColumnIndex("nombre"));
                @SuppressLint("Range") String descripcion = personajesCursor.getString(personajesCursor.getColumnIndex("descripcion"));
                @SuppressLint("Range") String poder = personajesCursor.getString(personajesCursor.getColumnIndex("poder"));
                @SuppressLint("Range") int fuerza = personajesCursor.getInt(personajesCursor.getColumnIndex("fuerza"));
                @SuppressLint("Range") int agilidad = personajesCursor.getInt(personajesCursor.getColumnIndex("agilidad"));
                @SuppressLint("Range") byte[] imagenBlob = personajesCursor.getBlob(personajesCursor.getColumnIndex("imagen"));
                Log.i(null, "Nombre: " + nombre + " - "+" descripción: " + descripcion + " idFromDB: " + idFromDB);
                Bitmap imagenBitmap = BitmapFactory.decodeByteArray(imagenBlob, 0, imagenBlob.length);

                nuevo(new Personaje(nombre,descripcion,imagenBitmap,poder,fuerza,agilidad,idFromDB));

            } while (personajesCursor.moveToNext());
        }
        personajesCursor.close();
    }
    //Método para insertar un personaje a nuestra base de datos SQLite
    public long insertarPersonaje(Personaje personaje){
        long id = 0;
        try {
            ContentValues values;
            values = getValuesPersonaje(personaje);

            id = this.db.insert(TABLE_PERSONAJES, null, values);
            personaje.setId(id);
            listaPersonajes.add(personaje);
        } catch (Exception exception){
            exception.toString();
        }
        return id;
    }

    //Este método setea los values necesarios desde el objeto de personaje (Mapeo de la DB al Objeto Personaje)
    private ContentValues getValuesPersonaje(Personaje personaje) {
        ContentValues values;
        values = new ContentValues();

        values.put("nombre", personaje.getName());
        values.put("descripcion", personaje.getDescription());
        values.put("poder", personaje.getPower());
        values.put("fuerza", personaje.getStrength());
        values.put("agilidad", personaje.getAgility());

        //Esta es condición es necesaria, por que nuestro Modelo Personaje usa Boolean y la base
        // datos SQLite no soporta Boolean, sólo Integer. Por eso 1 es TRUE y 0 es FALSE
        if(personaje.getFavorito()){
            values.put("favorito", 1);
        }else{
            values.put("favorito", 0);
        }


        //Necesitamos tener la imagen en BitMap para poder guardarla en nuestra base de datos como BLOB
        Bitmap bitmapImagen;

        if (personaje.getImageBitMap() != null){
            bitmapImagen = personaje.getImageBitMap();
        }else{
            bitmapImagen = BitmapFactory.decodeResource(context.getResources(), personaje.getImageId());
        }

        //Byte
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        bitmapImagen.compress(Bitmap.CompressFormat.JPEG,100,byteArray);

        //Con la imagen en BLOB es posible insertarla en la base de datos
        byte[] imagenBlob = byteArray.toByteArray();
        values.put("imagen", imagenBlob);
        return values;
    }

    //Método para entregar el listado de personajes favoritos
    public ArrayList<Personaje> getFavoritos(){
        ArrayList<Personaje> personajesFavoritos = null;
        for (Personaje personaje: listaPersonajes){
            if (personaje.getFavorito()) {
                personajesFavoritos.add(personaje);
            }
        }
        return personajesFavoritos;
    }

    public ArrayList<Personaje> getAll() {
        return listaPersonajes;
    }
}
