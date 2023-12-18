package com.example.reproductor.Buscador;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.reproductor.Entities.Canciones;
import com.example.reproductor.Entities.PlayList;
import com.example.reproductor.SQLite.db_MelodyMixer;

import java.util.List;

public class DialogoSeleccionPlayList extends DialogFragment {

    private List<PlayList> playlistsUsuario;
    private db_MelodyMixer db_melodyMixer;
    private Canciones cancionAAgregar;
    private SQLiteDatabase db;
    private Context context;

    public DialogoSeleccionPlayList(List<PlayList> playlistsUsuario, Canciones cancionAAgregar, Context context) {
        this.playlistsUsuario = playlistsUsuario;
        this.cancionAAgregar = cancionAAgregar;
        this.context = context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Creamos el builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        db_melodyMixer = new db_MelodyMixer(this.getContext());
        db = db_melodyMixer.getReadableDatabase();

        builder.setTitle("Selecciona la playlist para agregar la canción");

        // Creamos un array de nombres de playlist
        String[] nombresPlaylist = new String[playlistsUsuario.size()];
        for (int i = 0; i < playlistsUsuario.size(); i++) {
            nombresPlaylist[i] = playlistsUsuario.get(i).getNombre();
        }

        // Agregamos los nombres al diálogo
        builder.setItems(nombresPlaylist, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // En la playlist que pulse el usuario, agregaremos la canción a la misma
                db_melodyMixer.addCancion(db, cancionAAgregar);
                db_melodyMixer.addPLCancion(db, playlistsUsuario.get(i), cancionAAgregar);
                Toast.makeText(getContext(), "Se agrega correctamente", Toast.LENGTH_LONG);
            }
        });

        return builder.create();
    }
}
