package com.example.reto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {
    private ImageView imagen1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.sergio, getTheme());
        imagen1 = (ImageView) findViewById(R.id.imageView3);

        imagen1.setImageDrawable(drawable);

    }
}