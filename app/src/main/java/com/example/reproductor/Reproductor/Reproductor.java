package com.example.reproductor.Reproductor;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.reproductor.Entities.Canciones;
import com.example.reproductor.R;

import java.io.IOException;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.reproductor.Entities.Canciones;
import com.example.reproductor.R;


public class Reproductor extends AppCompatActivity {

    private Canciones cancionActual;


    ImageView imgCancion;
    TextView txvTextoCancion;
    TextView txvAutor;
    ProgressBar progressBar;
    ImageButton imbFavorita;
    ImageButton imbPlay;
    ImageButton imbSiguiente;
    ImageButton imbAnterior;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reproductor);

        imgCancion = (ImageView)findViewById(R.id.imgCancion); // Imagen de la canción
        txvTextoCancion = (TextView)findViewById(R.id.txvNombreCancion); // TextView con el nombre de la canción
        txvAutor = (TextView)findViewById(R.id.txvAutor); // TextView para el nombre del autor de la canción
        progressBar = (ProgressBar)findViewById(R.id.progressBar); // progressBar marcara la duración de la canción
        imbFavorita = (ImageButton)findViewById(R.id.imbFavorita); // imagen para marcar cuando una canción esta en favoritos
        imbPlay = (ImageButton)findViewById(R.id.imbPlay); // imagen para el boton de play
        imbSiguiente = (ImageButton)findViewById(R.id.imbSiguiente); // imagen para pasar a la siguiente cancion
        imbAnterior = (ImageButton)findViewById(R.id.imbAnterior); // imagen para pasar a la anterior cancion

        // LLega objeto cancion para conseguir toda la información de la canción seleccionada
        cancionActual = (Canciones)this.getIntent().getSerializableExtra("cancionSeleccionada");

        //Conseguimos el url dado
        String imageUrl = cancionActual.getLinkImage();

        //Y con glide colocamos la imagen a través del URL
        Glide.with(this)
                .load(imageUrl)
                .into(imgCancion);

        // Mostramos el nombre de la cancion y el autor en los textview correspondientes
        txvTextoCancion.setText(cancionActual.getNombre().toString());
        txvAutor.setText(cancionActual.getArtistaNombre().toString());

        imbPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mp = new MediaPlayer();
                try {

                    mp.setDataSource(cancionActual.getLinkPreview());
                    mp.prepare();
                    mp.start();
                }
                catch(IOException e){
                }
            }
        });

        cancionActual = (Canciones)this.getIntent().getSerializableExtra("cancionSeleccionada");

    }
}
