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
import com.example.reproductor.Entities.Usuarios;
import com.example.reproductor.R;
import com.example.reproductor.SQLite.db_MelodyMixer;

import java.io.IOException;
import java.util.List;

public class Reproductor extends AppCompatActivity {

    db_MelodyMixer database;
    SQLiteDatabase db;

    private Canciones cancionActual;
    private List<Canciones> cancionesPlaylist;
    Usuarios usuarioActual;

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

    boolean consulta;
<<<<<<< HEAD
    PlayList playlistFavoritos;
=======
>>>>>>> b9a99f4fa3390dc09788601da8d7ac06790df003

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
        cancionesPlaylist = (List<Canciones>) this.getIntent().getSerializableExtra("cancionesPlaylist");
        usuarioActual = (Usuarios)this.getIntent().getSerializableExtra("usuarioActual");

        //Conseguimos el url dado
        String imageUrl = cancionActual.getLinkImage();

        //Y con glide colocamos la imagen a través del URL
        Glide.with(this)
                .load(imageUrl)
                .into(imgCancion);

        // Mostramos el nombre de la cancion y el autor en los textview correspondientes
        txvTextoCancion.setText(cancionActual.getNombre().toString());
        txvAutor.setText(cancionActual.getArtistaNombre().toString());

<<<<<<< HEAD
        mp = new MediaPlayer(); //Instanciamos el mediaplayer
        try{
            mp.setDataSource(cancionActual.getLinkPreview()); //El mediaPlayer recoge el link del audio a reproducir
            mp.prepare(); //Se prepara
        }catch(IOException | IllegalStateException e){
        e.printStackTrace();
    }

        handler = new Handler(); //Creamos un controlador

        seekBar.setMax(mp.getDuration()); //Haremos que el máximo de la barra sea la duración de la canción
        playCicle(); //Método que empezará a mover la barra
=======
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
>>>>>>> b9a99f4fa3390dc09788601da8d7ac06790df003

        botonPlay = false;
        imbPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
<<<<<<< HEAD
                        if(!botonPlay){ //Cuando el boton sea falso aparecera la imagen de pause
                            imbPlay.setImageResource(R.drawable.pause_image);
                            mp.start(); //Comenzará a reproducirse la canción
                            playCicle(); //Llamará al metodo para continuar la canción
                            botonPlay = true; //Pasamos el boton a true
                        }
                        else{
                            imbPlay.setImageResource(R.drawable.play_image); //Se cambia imagen a play
                            mp.pause(); //Se pausara la canción
                            botonPlay = false; //Pasamos el boton false
                        }
=======
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
>>>>>>> b9a99f4fa3390dc09788601da8d7ac06790df003
                }catch(IllegalStateException e){
                    e.printStackTrace();
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean input) {
                if(input){
<<<<<<< HEAD
                    mp.seekTo(progress); //El progreso de la barra irá cambiando a medida que avanza la canción
=======
                    mp.seekTo(progress);
>>>>>>> b9a99f4fa3390dc09788601da8d7ac06790df003
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

<<<<<<< HEAD
        //Recuperamos la lista de playlist del usuario para saber si existe la canción que quiere añadir a favoritos
        for(PlayList playlist : this.database.recuperarListasUsuario(usuarioActual)){
            if(playlist.getNombre().equals("Favoritos")){
                consulta = database.seEncuentraEnPlayList(playlist, cancionActual); //Si la canción se encuentra en la playlist favoritos mandará true, sino en false
                playlistFavoritos = playlist;
=======
        for(PlayList playlist : this.database.recuperarListasUsuario(usuarioActual)){
            if(playlist.getNombre().equals("Favoritos")){
                consulta = database.seEncuentraEnPlayList(playlist, cancionActual);
>>>>>>> b9a99f4fa3390dc09788601da8d7ac06790df003
            }
        }

        //Comprobamos si la canción esta en favoritos
<<<<<<< HEAD
        if(consulta) imbFavorita.setImageResource(R.drawable.fav_image); //En caso de estar en favoritos se colocará la imagen en fav
        else imbFavorita.setImageResource(R.drawable.no_fav_image); //En caso de no estar en favoritos se colocará la imagen sin rellenar

=======
        if(consulta){
            imbFavorita.setImageResource(R.drawable.fav_image);
        }
        else{
            imbFavorita.setImageResource(R.drawable.no_fav_image);
        }
>>>>>>> b9a99f4fa3390dc09788601da8d7ac06790df003
        imbFavorita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
<<<<<<< HEAD
                    if(consulta){
                        imbFavorita.setImageResource(R.drawable.no_fav_image);
                        database.eliminarCancionFavoritos(playlistFavoritos, cancionActual);
                        consulta = false;
                    }
                    else{
                        imbFavorita.setImageResource(R.drawable.fav_image);
                        if(!database.estaCancionEnTablaCanciones(cancionActual)) database.addCancion(db, cancionActual);
                        database.addPLCancion(db, playlistFavoritos, cancionActual);
                        consulta = true;
                    }
=======

>>>>>>> b9a99f4fa3390dc09788601da8d7ac06790df003
                }catch(IllegalStateException e){
                    e.printStackTrace();
                }
            }
        });
    }
<<<<<<< HEAD

    //Metodo para liberar el mediaPlayer
=======
>>>>>>> b9a99f4fa3390dc09788601da8d7ac06790df003
    @Override
    protected void onDestroy(){
        super.onDestroy();
        mp.release();
        handler.removeCallbacks(runnable);
    }

<<<<<<< HEAD
    //Metodo para hacer que la barra vaya avanzando
    public void playCicle(){
        seekBar.setProgress(mp.getCurrentPosition()); //Se coloca el progreso en la posición según los segundos de la canción

        if(mp.isPlaying()){ //Si el mediaPlayer esta sonando
            runnable = new Runnable() {
                @Override
                public void run() {
                    playCicle(); //Avanza la barra
                }
            };
            handler.postDelayed(runnable, 1000); //Avanza cada 1000 milisegundos
        }
        else{
            imbPlay.setImageResource(R.drawable.play_image); //Se coloca la imagen de play
            if(mp.getDuration() == seekBar.getProgress()){ //si la duración de la canción coincide con el progreso realizado (llega al final)  del boton entonces se reinicia
=======
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
>>>>>>> b9a99f4fa3390dc09788601da8d7ac06790df003
                seekBar.setProgress(0);
            }
        }
    }
}
