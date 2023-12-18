package com.example.reproductor.PlayList;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reproductor.Entities.PlayList;
import com.example.reproductor.R;

import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;

public class CancionesPlayList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlist);

        ImageView imagenPL = findViewById(R.id.imvwPlaylist);
        TextView tituloPL = findViewById(R.id.tvwNombreAlbum);


        Intent intent = getIntent();
        if(intent != null && intent.hasExtra("playlist")){
            PlayList playList = (PlayList) intent.getSerializableExtra("playlist");
            tituloPL.setText(playList.getNombre());

            Glide.with(this)
                    .load(playList.getImgURLPlaylist()) // Aquí debes tener el método getImagenUrl() en tu PlayList
                    .placeholder(R.drawable.cargando_cancion_image) // R.drawable.placeholder es una imagen de carga por defecto
                    .into(imagenPL);
        }
    }
}
