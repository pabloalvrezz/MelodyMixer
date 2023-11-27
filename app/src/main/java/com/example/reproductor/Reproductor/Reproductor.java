package com.example.reproductor.Reproductor;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reproductor.Entities.Canciones;
import com.example.reproductor.R;

public class Reproductor extends AppCompatActivity {

    private Canciones cancionActual;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reproductor);

        cancionActual = (Canciones)this.getIntent().getSerializableExtra("cancionSeleccionada");
    }
}
