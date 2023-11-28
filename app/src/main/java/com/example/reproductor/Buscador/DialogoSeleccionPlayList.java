package com.example.reproductor.Buscador;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.DialogFragment;

import com.example.reproductor.Entities.PlayList;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DialogoSeleccionPlayList extends DialogFragment {

    private List<PlayList> playlistsUsuario;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Creamos el builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

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
                Log.i("Dialogos", "Opción elegida: " + playlistsUsuario.get(i).getNombre());
            }
        });

        return builder.create();
    }
}
