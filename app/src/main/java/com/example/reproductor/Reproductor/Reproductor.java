package com.example.reproductor.Reproductor;

import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.reproductor.Entities.Canciones;
import com.example.reproductor.Entities.PlayList;
import com.example.reproductor.R;
import com.example.reproductor.SQLite.db_MelodyMixer;

import java.io.IOException;

public class Reproductor extends AppCompatActivity {

    db_MelodyMixer database;
    SQLiteDatabase db;

    private Canciones cancionActual;

    ImageView imgCancion;
    TextView txvTextoCancion;
    TextView txvAutor;
    SeekBar seekBar;
    ImageButton imbFavorita;
    ImageButton imbPlay;
    ImageButton imbSiguiente;
    ImageButton imbAnterior;

    MediaPlayer mp;
    boolean botonPlay;
    int Playposition;

    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reproductor);

        database = new db_MelodyMixer(this);     //Creamos el objeto de la BD
        db = database.getWritableDatabase();

        imgCancion = (ImageView)findViewById(R.id.imgCancion); // Imagen de la canción
        txvTextoCancion = (TextView)findViewById(R.id.txvNombreCancion); // TextView con el nombre de la canción
        txvAutor = (TextView)findViewById(R.id.txvAutor); // TextView para el nombre del autor de la canción
        seekBar = (SeekBar) findViewById(R.id.seekBar); // progressBar marcara la duración de la canción
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

        mp = new MediaPlayer();
        try{
            mp.setDataSource(cancionActual.getLinkPreview());
            mp.prepare();
        }catch(IOException | IllegalStateException e){
        e.printStackTrace();
    }
        handler = new Handler();

        seekBar.setMax(mp.getDuration());
        playCicle();

        botonPlay = false;
        imbPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                        if(!botonPlay){
                            imbPlay.setImageResource(R.drawable.pause_image);
                            mp.start();
                            playCicle();
                            botonPlay = true;
                        }
                        else{
                            imbPlay.setImageResource(R.drawable.play_image);
                            mp.pause();
                            botonPlay = false;
                        }
                }catch(IllegalStateException e){
                    e.printStackTrace();
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean input) {
                if(input){
                    mp.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        imbFavorita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                }catch(IllegalStateException e){
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        mp.release();
        handler.removeCallbacks(runnable);
    }

    public void playCicle(){
        seekBar.setProgress(mp.getCurrentPosition());

        if(mp.isPlaying()){
            runnable = new Runnable() {
                @Override
                public void run() {
                    playCicle();
                }
            };
            handler.postDelayed(runnable, 1000);
        }
        else{
            imbPlay.setImageResource(R.drawable.play_image);
            if(mp.getDuration() == seekBar.getProgress()){
                seekBar.setProgress(0);
            }
        }
    }
}
