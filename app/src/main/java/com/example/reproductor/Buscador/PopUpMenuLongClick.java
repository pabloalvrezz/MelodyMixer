package com.example.reproductor.Buscador;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.example.reproductor.Entities.Canciones;
import com.example.reproductor.Entities.PlayList;
import com.example.reproductor.Entities.Usuarios;
import com.example.reproductor.R;
import com.example.reproductor.SQLite.db_MelodyMixer;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PopUpMenuLongClick implements PopupMenu.OnMenuItemClickListener {

    private Context context;
    private db_MelodyMixer db_melodyMixer;
    private Usuarios usuarioActual;
    private List<PlayList> playlistUsuarioActual;
    private List<Canciones> canciones;
    private int posicion;


    public PopUpMenuLongClick(Context context, Usuarios usuarioActual, List<Canciones> canciones, int posicion) {
        this.context = context;
        this.usuarioActual = usuarioActual;
        this.canciones = canciones;
        this.posicion = posicion;
        db_melodyMixer = new db_MelodyMixer(this.context);

        // recuperamos todas las playlist del usuario actual
        playlistUsuarioActual = db_melodyMixer.recuperarListasUsuario(usuarioActual);
    }
    /*
     * TODO
     * agregar la comprobacion de que la cancion no se encuentre ya en favoritos
     */

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        SQLiteDatabase db = db_melodyMixer.getReadableDatabase();

        // evaluamos que item ha pulsado el usuario
        switch (menuItem.getItemId()) {
            // en caso de que quiera agregar la cancion a favoritos
            case R.id.mniAgregarFavoritos:

                // obtenemos todas las playlists del usuario
                playlistUsuarioActual = db_melodyMixer.recuperarListasUsuario(usuarioActual);

                // buscamos la que sea 'Favoritos'
                for (PlayList playListFav : playlistUsuarioActual) {
                    if (playListFav.getNombre().equals("Favoritos")) {

                        // agregamos la cancion a la playlist de favoritos
                        // pero antes tenemos que comprobar que no este ya en la lista de favoritos
                        if (db_melodyMixer.seEncuentraEnPlayList(playListFav, canciones.get(posicion)) == false) {
                            db_melodyMixer.addPLCancion(db, playListFav, canciones.get(posicion));
                            db_melodyMixer.addCancion(db, canciones.get(posicion));
                            Toast.makeText(this.context, "Se agrega correctamente a favs", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(this.context, "Ya existe la cancion en favoritos", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                break;

            // en caso de que quiera agregar la cancion a una playlist
            case 14:
                List<PlayList> playlistSinFavs = new ArrayList<>(); // lista de playlist sin la lista de favs
                FragmentActivity fragmentActivity = (FragmentActivity) context;

                // metemos todas las playlist del usuario que no sean de favoritos
                for (PlayList playlist : this.playlistUsuarioActual) {
                    if (!playlist.getNombre().equals("Favoritos"))
                        playlistSinFavs.add(playlist);
                }

                // Crea e muestra el diálogo
                AlertDialog.Builder builder = new AlertDialog.Builder(fragmentActivity);
                DialogoSeleccionPlayList dialogoSeleccionPlayList = new DialogoSeleccionPlayList(playlistSinFavs, canciones.get(posicion), this.context);

                // Muestra el diálogo
                dialogoSeleccionPlayList.show(fragmentActivity.getSupportFragmentManager(), "tag");

                break;
            /*
             * En caso de que el usuario quiera crear una nueva playlist, la crearemos y agregaremos
             * la cancion directamente a la playlist
             */

            case R.id.mniCrearPlaylist:
                AlertDialog.Builder builderCrearPlaylist = new AlertDialog.Builder(context);
                LayoutInflater inflater = LayoutInflater.from(context);
                View crearPlaylistView = inflater.inflate(R.layout.crear_playlist, null);
                builderCrearPlaylist.setView(crearPlaylistView);

                EditText etxNombrePlayList = crearPlaylistView.findViewById(R.id.etxNombrePlaylist);
                ImageView imgFotoPlaylist = crearPlaylistView.findViewById(R.id.imgFotoPlaylist);

                // Cargamos la futura imagen de la playlist
                Glide.with(this.context)
                        .load(this.canciones.get(posicion).getLinkImage())
                        .override(500, 500)
                        .into(imgFotoPlaylist);


                builderCrearPlaylist.setTitle("Crear nueva playlist")
                        .setPositiveButton("Crear", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                // obtenemos el nombre de la playlist
                                String nombrePlaylist = etxNombrePlayList.getText().toString();

                                // comprobamos que el usuario haya metido nombre de playlist
                                // y que no exista una con el mismo nombre
                                if (!nombrePlaylist.isEmpty() && !existePlaylist(nombrePlaylist)) {

                                    // creamos la nueva playlist y le asociamos el usuario
                                    PlayList nuevaPlaylist = new PlayList();
                                    nuevaPlaylist.setNombre(nombrePlaylist);
                                    nuevaPlaylist.setUsuarioId(usuarioActual.getCorreo());

                                    // agregamos la playlist en la base de datos
                                    db_melodyMixer.addPlaylist(db, nuevaPlaylist, usuarioActual);
                                    db_melodyMixer.addCancion(db, canciones.get(posicion));
                                    db_melodyMixer.addPLCancion(db, nuevaPlaylist, canciones.get(posicion));

                                    Toast.makeText(context, "Playlist creada: " + nombrePlaylist + " y canción agregada a la playlist", Toast.LENGTH_SHORT).show();
                                }


                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // Cancelar la creación de la playlist
                                dialog.dismiss();
                            }
                        });

                AlertDialog dialog = builderCrearPlaylist.create();

                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                        Button negativeButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);

                        if (positiveButton != null && negativeButton != null) {

                            // cambiamos el color de los botones del cuadro de dialogo
                            positiveButton.setTextColor(ContextCompat.getColor(context, R.color.fondo_oscuro));
                            positiveButton.setTextColor(ContextCompat.getColor(context, R.color.fondo_claro));
                        }
                    }
                });

                dialog.show();
                break;

        }

        return true;
    }

    // metodo que usaremos para comprobar si existe una playlist
    private boolean existePlaylist(String nombrePlaylist) {
        boolean existe = false;

        // comrprobamos que el nombre de la playlist no este vacio
        if (!nombrePlaylist.isEmpty()) {

            // recorremos todas las playlist del usuario y comprobamos los nombres
            for (PlayList playlist : this.db_melodyMixer.recuperarListasUsuario(this.usuarioActual)) {

                // comprobamos los nombres en minusculas y sin espacios
                // en caso de que una playlist tenga el mismo nombre, significa que si existe
                if (playlist.getNombre().toLowerCase().trim().equals(nombrePlaylist.toLowerCase().trim()))
                    existe = true;
            }
        }


        return existe;
    }
}