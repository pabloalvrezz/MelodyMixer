package com.example.reproductor.SQLite;

import android.annotation.SuppressLint;


import android.annotation.SuppressLint;
import android.content.ContentValues;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.service.controls.Control;

import com.example.reproductor.Entities.Albumes;

import com.example.reproductor.Entities.Artistas;
import com.example.reproductor.Entities.Canciones;
import com.example.reproductor.Entities.PlayList;
import com.example.reproductor.Entities.Usuarios;

import java.io.File;

import java.util.ArrayList;
import java.util.List;
public class db_MelodyMixer extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MelodyMixer";
    private static final int DATABASE_VERSION = 1;

    public db_MelodyMixer(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Se crean las tablas
        db.execSQL(tabla_USUARIOS.SQL_CREATE_TABLE);
        db.execSQL(tabla_ARTISTAS.SQL_CREATE_TABLE);
        db.execSQL(tabla_CANCIONES.SQL_CREATE_TABLE);
        db.execSQL(tabla_PLAYLIST.SQL_CREATE_TABLE);
        db.execSQL(tabla_PLAYLIST_CANCION.SQL_CREATE_TABLE);

        //Se le meten datos
        db.execSQL(tabla_USUARIOS.INSERTA_DEFECTO);
        db.execSQL(tabla_ARTISTAS.INSERTA_DEFECTO);
        db.execSQL(tabla_CANCIONES.INSERTA_CANCION1);
        db.execSQL(tabla_CANCIONES.INSERTA_CANCION2);
        db.execSQL(tabla_PLAYLIST.INSERTA_DEFECTO);
        db.execSQL(tabla_PLAYLIST.INSERTA_DEFECTO_2);
        db.execSQL(tabla_PLAYLIST_CANCION.INSERTA_DEFECTO);

    }

    //Para recrear las tablas
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Comprobamos que estén ya creadas y las borramos en caso afirmativo
        db.execSQL("DROP TABLE IF EXISTS " + tabla_USUARIOS.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + tabla_ARTISTAS.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + tabla_CANCIONES.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + tabla_PLAYLIST.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + tabla_PLAYLIST_CANCION.TABLE_NAME);

        //Las creamos de nuevo
        db.execSQL(tabla_USUARIOS.SQL_CREATE_TABLE);
        db.execSQL(tabla_ARTISTAS.SQL_CREATE_TABLE);
        db.execSQL(tabla_CANCIONES.SQL_CREATE_TABLE);
        db.execSQL(tabla_PLAYLIST.SQL_CREATE_TABLE);
        db.execSQL(tabla_PLAYLIST_CANCION.SQL_CREATE_TABLE);
    }

    //Método para añadir fila a USUARIOS
    public void addUsuario(SQLiteDatabase db, Usuarios usuario) {

        ContentValues values = new ContentValues();
        values.put(tabla_USUARIOS.ColumnasUsuarios.COLUMNA_ID, usuario.getCorreo());
        values.put(tabla_USUARIOS.ColumnasUsuarios.COLUMNA_USUARIO, usuario.getUsuario());
        values.put(tabla_USUARIOS.ColumnasUsuarios.COLUMNA_APELLIDOS, usuario.getApellidos());
        values.put(tabla_USUARIOS.ColumnasUsuarios.COLUMNA_CONTRASEÑA, usuario.getContraseña());

        db.insert(tabla_USUARIOS.TABLE_NAME, null, values);
    }

    //Método para añadir fila a PLAYLIST
    public void addPlaylist(SQLiteDatabase db, PlayList playList, Usuarios usuario) {
        ContentValues values = new ContentValues();
        values.put(tabla_PLAYLIST.ColumnasPlayList.COLUMNA_NOMBRE, playList.getNombre());
        values.put(tabla_PLAYLIST.ColumnasPlayList.COLUMNA_ID_USUARIO, usuario.getCorreo());
        values.put(tabla_PLAYLIST.ColumnasPlayList.COLUMNA_IMG, playList.getImgURLPlaylist());

        db.insert(tabla_PLAYLIST.TABLE_NAME, null, values);
    }

    //Método para añadir fila a CANCIONES
    public void addCancion(SQLiteDatabase db, Canciones cancion) {

        ContentValues values = new ContentValues();
        values.put(tabla_CANCIONES.ColumnasCanciones.COLUMNA_ID, cancion.getId());
        values.put(tabla_CANCIONES.ColumnasCanciones.COLUMNA_TITULO, cancion.getNombre());
        values.put(tabla_CANCIONES.ColumnasCanciones.COLUMNA_ARTISTA_NOMBRE, cancion.getArtistaNombre());
        values.put(tabla_CANCIONES.ColumnasCanciones.COLUMNA_DURACION, cancion.getDuracion());
        values.put(tabla_CANCIONES.ColumnasCanciones.COLUMNA_GENERO, cancion.getGenero());
        values.put(tabla_CANCIONES.ColumnasCanciones.COLUMNA_IMAGE_URL, cancion.getLinkImage());

        db.insert(tabla_CANCIONES.TABLE_NAME, null, values);
    }


    //Método para añadir fila a ALBUMES
    public void addArtistas(SQLiteDatabase db, Artistas artista) {

        ContentValues values = new ContentValues();
        values.put(tabla_ARTISTAS.ColumnasArtistas.COLUMNA_ID, artista.getId());
        values.put(tabla_ARTISTAS.ColumnasArtistas.COLUMNA_NOMBRE, artista.getNombre());

        db.insert(tabla_ARTISTAS.TABLE_NAME, null, values);
    }

    //Método para añadir fila a PLAYLIST_CANCION
    public void addPLCancion(SQLiteDatabase db, PlayList playList, Canciones cancion){

        ContentValues values = new ContentValues();
        values.put(tabla_PLAYLIST_CANCION.ColumnasPlayCanciones.COLUMNA_ID_PLAYLIST, playList.getId());
        values.put(tabla_PLAYLIST_CANCION.ColumnasPlayCanciones.COLUMNA_ID_CANCION, cancion.getId());

        db.insert(tabla_PLAYLIST_CANCION.TABLE_NAME, null, values);
    }

    // Metodo que usaremos para comprobar si existe el usuario
    public boolean existeUsuarioCorreo(String correo) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Especifica la tabla y las columnas que deseas consultar
        String[] columnas = {tabla_USUARIOS.ColumnasUsuarios.COLUMNA_ID};

        // Especifica la cláusula WHERE para buscar el correo específico
        String seleccion = tabla_USUARIOS.ColumnasUsuarios.COLUMNA_ID + " = ?";
        String[] argumentos = {correo};

        // Realiza la consulta
        Cursor cursor = db.query(tabla_USUARIOS.TABLE_NAME, columnas, seleccion, argumentos, null, null, null);

        // Verifica si se devolvieron filas
        boolean existeUsuario = cursor.getCount() > 0;

        // Cierra el cursor y la base de datos
        cursor.close();
        return existeUsuario;
    }

    /*
     * Metodo que usaremos para comprobar si hay una contraseña asociada al usurio que esta
     * intentando ingresar en la base de datos
     */

    public boolean existeUsuarioContraseña(String contraseña) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Especifica la tabla y las columnas que deseas consultar
        String[] columnas = {tabla_USUARIOS.ColumnasUsuarios.COLUMNA_CONTRASEÑA};

        // Especifica la cláusula WHERE para buscar el correo específico
        String seleccion = tabla_USUARIOS.ColumnasUsuarios.COLUMNA_CONTRASEÑA + " = ?";
        String[] argumentos = {contraseña};

        // Realiza la consulta
        Cursor cursor = db.query(tabla_USUARIOS.TABLE_NAME, columnas, seleccion, argumentos, null, null, null);

        // Verifica si se devolvieron filas
        boolean existeUsuario = cursor.getCount() > 0;

        // Cierra el cursor y la base de datos
        cursor.close();
        return existeUsuario;
    }


    //No se pa que sirve el Supress este (Pero da error si no)

    /*
     * Metodo que usaremos para obtener el usuario por el
     * correo
     */

    public String obtenerNombrePorCorreo(String correo) {
        int indexNombre = 0;
        String nombre = "";
        // Realiza la consulta
        String consulta = "SELECT usuario FROM USUARIOS WHERE email = ?";
        Cursor cursor = getReadableDatabase().rawQuery(consulta, new String[]{correo});


        // Verifica si se obtuvo algún resultado
        if (cursor.moveToFirst() && cursor != null) {
            // Obtiene el nombre de la columna 'USUARIO'
            indexNombre = cursor.getColumnIndex(tabla_USUARIOS.ColumnasUsuarios.COLUMNA_USUARIO);
        }
        if (indexNombre >= 0) {
            nombre = cursor.getString(indexNombre);
        } else {
            // Manejar el caso donde la columna no existe
            nombre = "Columna no encontrada";
        }
        // Cierra el cursor después de usarlo
        cursor.close();
        // Devuelve el nombre o null si no se encontró
        return nombre;
    }

    /*
     * Metodo que usaremos
     */

    public String obtenerApellidosPorCorreo(String correo) {
        int indexApellidos = 0;
        String apellidos = "";
        // Realiza la consulta
        String consulta = "SELECT apellidos FROM USUARIOS WHERE email = ?";
        Cursor cursor = getReadableDatabase().rawQuery(consulta, new String[]{correo});
        // Verifica si se obtuvo algún resultado
        if (cursor.moveToFirst()) {
            // Obtiene los apellidos de la columna 'APELLIDOS'
            indexApellidos = cursor.getColumnIndex(tabla_USUARIOS.ColumnasUsuarios.COLUMNA_APELLIDOS);
        }
        if (indexApellidos >= 0) {
            apellidos = cursor.getString(indexApellidos);
        } else {
            // Manejar el caso donde la columna no existe
            apellidos = "Columna no encontrada";
        }
        // Cierra el cursor después de usarlo
        cursor.close();
        // Devuelve el nombre o null si no se encontró
        return apellidos;
    }

    /*
     * Metodo que usaremos para recuperar todas las listas
     * asociadas al usuario actual de la aplicacion
     */
    public List<PlayList> recuperarListasUsuario(Usuarios usuarioActual) {
        String consulta = "SELECT * FROM PLAYLIST WHERE usuario = ?";
        Cursor cursor = getReadableDatabase().rawQuery(consulta, new String[]{usuarioActual.getCorreo()});
        long idPlaylist;
        int nombrePlaylistIndex, urlPlaylistIndex;
        String nombrePlaylist = "", urlPlaylist = "";
        PlayList playList;

        // Lista para almacenar las playlist del usuario
        List<PlayList> playlists = new ArrayList<PlayList>();

        // verificamos si se obtuvo resultados
        while (cursor.moveToNext()) {
            // Obtén los datos de cada fila y crea un objeto Playlist
            idPlaylist = cursor.getColumnIndex(tabla_PLAYLIST.ColumnasPlayList.COLUMNA_ID) + 1;
            nombrePlaylistIndex = cursor.getColumnIndex(tabla_PLAYLIST.ColumnasPlayList.COLUMNA_NOMBRE);
            urlPlaylistIndex = cursor.getColumnIndex(tabla_PLAYLIST.ColumnasPlayList.COLUMNA_IMG);

            // en caso de que haya playlists las agregamos a la lista
            if (nombrePlaylistIndex > 0) {
                nombrePlaylist = cursor.getString(nombrePlaylistIndex);
                urlPlaylist = cursor.getString(urlPlaylistIndex);
                playList = new PlayList(idPlaylist, nombrePlaylist,usuarioActual.getCorreo(), urlPlaylist);

                playlists.add(playList);
            }
        }

        // Cierra el cursor después de usarlo
        cursor.close();

        return playlists;
    }

    public List<PlayList> recuperarListasRecomendadas() {
        String consulta = "SELECT * FROM PLAYLIST WHERE " + tabla_PLAYLIST.ColumnasPlayList.COLUMNA_ID_USUARIO + " IS NULL";
        Cursor cursor = getReadableDatabase().rawQuery(consulta, null);

        long idPlaylist;
        int nombrePlaylistIndex, imgURLIndex;
        String nombrePlaylist = "", imgURL = "";
        PlayList playList;

        // Lista para almacenar las playlists con idUsuario NULL
        List<PlayList> playlists = new ArrayList<>();

        // verificamos si se obtuvo resultados
        while (cursor.moveToNext()) {
            // Obtén los datos de cada fila y crea un objeto Playlist
            idPlaylist = cursor.getColumnIndex(tabla_PLAYLIST.ColumnasPlayList.COLUMNA_ID);
            nombrePlaylistIndex = cursor.getColumnIndex(tabla_PLAYLIST.ColumnasPlayList.COLUMNA_NOMBRE);
            imgURLIndex = cursor.getColumnIndex(tabla_PLAYLIST.ColumnasPlayList.COLUMNA_IMG);

            // en caso de que haya playlists las agregamos a la lista
            if (nombrePlaylistIndex >= 0) {
                nombrePlaylist = cursor.getString(nombrePlaylistIndex);
                imgURL = cursor.getString(imgURLIndex);
                playList = new PlayList(idPlaylist, nombrePlaylist, null, imgURL);

                playlists.add(playList);
            }
        }

        // Cierra el cursor después de usarlo
        cursor.close();

        return playlists;
    }

    // Método que usaremos para verificar si la canción actual se encuentra en la playlist
    public boolean seEncuentraEnPlayList(PlayList playList, Canciones cancion) {
        SQLiteDatabase db = getReadableDatabase();

        // Consulta para verificar si la canción está en la playlist
        String consulta = "SELECT * FROM " + tabla_PLAYLIST_CANCION.TABLE_NAME +
                " WHERE " + tabla_PLAYLIST_CANCION.ColumnasPlayCanciones.COLUMNA_ID_PLAYLIST + " = ?" +
                " AND " + tabla_PLAYLIST_CANCION.ColumnasPlayCanciones.COLUMNA_ID_CANCION + " = ?";

        // Parámetros para la consulta
        String[] parametros = {String.valueOf(playList.getId()), String.valueOf(cancion.getId())};

        // Ejecutar la consulta
        Cursor cursor = db.rawQuery(consulta, parametros);

        // Verificar si hay resultados
        boolean seEncuentraEnPlayList = cursor.moveToFirst();

        // Cerrar el cursor después de usarlo
        cursor.close();

        return seEncuentraEnPlayList;
    }



    //Método que comprueba y devuelve si la base de datos está creada
    public boolean isDatabaseExists(Context context) {
        File dbFile = context.getDatabasePath(DATABASE_NAME);
        return dbFile.exists();
    }


}
