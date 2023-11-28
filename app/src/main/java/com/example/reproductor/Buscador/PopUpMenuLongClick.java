package com.example.reproductor.Buscador;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.MenuItem;
import android.widget.PopupMenu;

import com.example.reproductor.Entities.Canciones;
import com.example.reproductor.Entities.PlayList;
import com.example.reproductor.Entities.Usuarios;
import com.example.reproductor.R;
import com.example.reproductor.SQLite.db_MelodyMixer;

import java.util.ArrayList;
import java.util.List;

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
                        db_melodyMixer.addPLCancion(db, playListFav, canciones.get(posicion));
                        db_melodyMixer.addCancion(db, canciones.get(posicion));
                    }
                }
                break;

            // en caso de que quiera agregar la cancion a una playlist
            case R.id.mniAgregarPlaylist:
                List<PlayList> playListNoFavs = new ArrayList<>(); // todas las playlist sin favs

                playlistUsuarioActual = db_melodyMixer.recuperarListasUsuario(usuarioActual);

                // buscamos las que no sean 'Favoritos'
                for (PlayList playListNoFav : playlistUsuarioActual) {
                    if (!playListNoFav.getNombre().equals("Favoritos")) {
                        playListNoFavs.add(playListNoFav);
                    }
                }
                DialogoSeleccionPlayList dialogoSeleccionPlayList = new DialogoSeleccionPlayList(playListNoFavs);

                break;

            // en caso de que quiera crear una nueva play list
            case R.id.mniCrearPlaylist:
                break;
        }

        return true;
    }


}
