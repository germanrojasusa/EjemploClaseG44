package com.example.reto1.datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 6;
    private static final String DATABASE_NOMBRE = "personajes.db";
    public static final String TABLE_PERSONAJES = "tabla_personajes";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    //Para inicializar la base de datos y crear las tablas que necesitemos
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_PERSONAJES + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "poder TEXT NOT NULL," +
                "descripcion TEXT NOT NULL," +
                "fuerza INTEGER DEFAULT 0," +
                "agilidad INTEGER DEFAULT 0," +
                "favorito INTEGER DEFAULT 0," +
                "imagen BLOB DEFAULT NULL)");
    }

    //Necesario para cada vez que se actualiza la estructura de la base de datos
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_PERSONAJES);
        onCreate(sqLiteDatabase);
    }

}
