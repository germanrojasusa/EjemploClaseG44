package com.example.reto1.presentacion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.reto1.R;
import com.example.reto1.modelo.Personaje;

import java.util.ArrayList;
import java.util.List;

public class PersonajesAdaptador extends ArrayAdapter<Personaje> {
    private Context contexto;
    private int recurso;
    private List<Personaje> listaPersonajes;

    public PersonajesAdaptador(@NonNull Context context, int resource, @NonNull ArrayList<Personaje> objects) {
        super(context, resource, objects);
        this.contexto = context;
        this.recurso = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.contexto);

        convertView = layoutInflater.inflate(recurso,parent,false);

        ImageView imageView = convertView.findViewById(R.id.image);
        TextView textTitle = convertView.findViewById(R.id.title);
        TextView textSubTitle = convertView.findViewById(R.id.subTitle);
        TextView textDescription = convertView.findViewById(R.id.description);
        ImageView imagenFavorita = convertView.findViewById(R.id.imagenFavorita);

        if (getItem(position).getFavorito()){
            imagenFavorita.setImageResource(R.drawable.favoriteon);
        }
        if (getItem(position).getImageBitMap() != null) {
            imageView.setImageBitmap(getItem(position).getImageBitMap());
        }else{
            imageView.setImageResource(getItem(position).getImageId());
        }
        textTitle.setText(getItem(position).getName());
        textSubTitle.setText(getItem(position).getPower());
        textDescription.setText(getItem(position).getDescription());

        return convertView;
    }
}
