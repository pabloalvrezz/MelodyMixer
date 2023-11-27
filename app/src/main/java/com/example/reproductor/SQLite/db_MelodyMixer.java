package com.example.reproductor.SQLite;
<<<<<<< HEAD
import android.annotation.SuppressLint;
=======

import android.annotation.SuppressLint;
import android.content.ContentValues;
>>>>>>> 36a4bef44323b8c21e384190fbe802170dbb2151
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
<<<<<<< HEAD
import android.content.ContentValues;
import android.service.controls.Control;

import com.example.reproductor.Entities.Albumes;
=======

>>>>>>> 36a4bef44323b8c21e384190fbe802170dbb2151
import com.example.reproductor.Entities.Artistas;
import com.example.reproductor.Entities.Canciones;
import com.example.reproductor.Entities.PlayList;
import com.example.reproductor.Entities.Usuarios;

import java.io.File;
<<<<<<< HEAD
=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> 36a4bef44323b8c21e384190fbe802170dbb2151

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
<<<<<<< HEAD
        db.execSQL(tabla_PLAYLIST.INSERTA_DEFECTO);
        db.execSQL(tabla_PLAYLIST_CANCION.INSERTA_DEFECTO);
=======
>>>>>>> 36a4bef44323b8c21e384190fbe802170dbb2151
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
<<<<<<< HEAD
        ContentValues values = new ContentValues();
        values.put(tabla_USUARIOS.ColumnasUsuarios.COLUMNA_ID, usuario.getCorreo());
        values.put(tabla_USUARIOS.ColumnasUsuarios.COLUMNA_USUARIO, usuario.getUsuario());
        values.put(tabla_USUARIOS.ColumnasUsuarios.COLUMNA_APELLIDOS, usuario.getApellidos());
        values.put(tabla_USUARIOS.ColumnasUsuarios.COLUMNA_CONTRASEÑA, usuario.getContraseña());

        db.insert(tabla_USUARIOS.TABLE_NAME, null, values);
    }
    //Método para añadir fila a PLAYLIST
    public void addPlaylist(SQLiteDatabase db, PlayList playList){
        ContentValues values = new ContentValues();
        values.put(tabla_PLAYLIST.ColumnasPlayList.COLUMNA_ID, playList.getId());
        values.put(tabla_PLAYLIST.ColumnasPlayList.COLUMNA_NOMBRE, playList.getNombre());
        values.put(tabla_PLAYLIST.ColumnasPlayList.COLUMNA_ID_USUARIO, playList.getUsuario().getCorreo());

        db.insert(tabla_PLAYLIST.TABLE_NAME, null, values);
    }
    //Método para añadir fila a CANCIONES
    public void addCancion(SQLiteDatabase db, Canciones cancion){
=======
        ContentValues valuesUsuario = new ContentValues();
        ContentValues valuesPlayList = new ContentValues();

        // insertamos todos los valores del usuario que se acaba de registrar
        valuesUsuario.put(tabla_USUARIOS.ColumnasUsuarios.COLUMNA_ID, usuario.getCorreo());
        valuesUsuario.put(tabla_USUARIOS.ColumnasUsuarios.COLUMNA_USUARIO, usuario.getUsuario());
        valuesUsuario.put(tabla_USUARIOS.ColumnasUsuarios.COLUMNA_APELLIDOS, usuario.getApellidos());
        valuesUsuario.put(tabla_USUARIOS.ColumnasUsuarios.COLUMNA_CONTRASEÑA, usuario.getContraseña());

        // creamos la playlist de favoritos del usuario que se acaba de registrar
        valuesPlayList.put(tabla_PLAYLIST.ColumnasPlayList.COLUMNA_ID_USUARIO, usuario.getCorreo());
        valuesPlayList.put(tabla_PLAYLIST.ColumnasPlayList.COLUMNA_NOMBRE, "Favoritos");

        // insertamos el usuario actual y la playlist de favoritos
        db.insert(tabla_USUARIOS.TABLE_NAME, null, valuesUsuario);
        db.insert(tabla_PLAYLIST.TABLE_NAME, null, valuesPlayList);
    }

    //Método para añadir fila a PLAYLIST
    public void addPlaylist(SQLiteDatabase db, PlayList playList, Usuarios usuario) {
        ContentValues values = new ContentValues();
        values.put(tabla_PLAYLIST.ColumnasPlayList.COLUMNA_ID, playList.getId());
        values.put(tabla_PLAYLIST.ColumnasPlayList.COLUMNA_NOMBRE, playList.getNombre());
        values.put(tabla_PLAYLIST.ColumnasPlayList.COLUMNA_ID_USUARIO, usuario.getCorreo());

        db.insert(tabla_PLAYLIST.TABLE_NAME, null, values);
    }

    //Método para añadir fila a CANCIONES
    public void addCancion(SQLiteDatabase db, Canciones cancion) {
>>>>>>> 36a4bef44323b8c21e384190fbe802170dbb2151
        ContentValues values = new ContentValues();
        values.put(tabla_CANCIONES.ColumnasCanciones.COLUMNA_ID, cancion.getId());
        values.put(tabla_CANCIONES.ColumnasCanciones.COLUMNA_TITULO, cancion.getNombre());
        values.put(tabla_CANCIONES.ColumnasCanciones.COLUMNA_ARTISTA_NOMBRE, cancion.getArtistaNombre());
        values.put(tabla_CANCIONES.ColumnasCanciones.COLUMNA_DURACION, cancion.getDuracion());
        values.put(tabla_CANCIONES.ColumnasCanciones.COLUMNA_GENERO, cancion.getGenero());
        values.put(tabla_CANCIONES.ColumnasCanciones.COLUMNA_IMAGE_URL, cancion.getLinkImage());

        db.insert(tabla_CANCIONES.TABLE_NAME, null, values);
    }
<<<<<<< HEAD
    //Método para añadir fila a ALBUMES
    public void addArtistas(SQLiteDatabase db, Artistas artista){
=======

    //Método para añadir fila a ALBUMES
    public void addArtistas(SQLiteDatabase db, Artistas artista) {
>>>>>>> 36a4bef44323b8c21e384190fbe802170dbb2151
        ContentValues values = new ContentValues();
        values.put(tabla_ARTISTAS.ColumnasArtistas.COLUMNA_ID, artista.getId());
        values.put(tabla_ARTISTAS.ColumnasArtistas.COLUMNA_NOMBRE, artista.getNombre());

        db.insert(tabla_ARTISTAS.TABLE_NAME, null, values);
    }
<<<<<<< HEAD
    //Método para añadir fila a PLAYLIST_CANCION
    public void addPLCancion(SQLiteDatabase db, PlayList playList, Canciones cancion){
=======

    //Método para añadir fila a PLAYLIST_CANCION
    public void addPLCancion(SQLiteDatabase db, PlayList playList, Canciones cancion) {
>>>>>>> 36a4bef44323b8c21e384190fbe802170dbb2151
        ContentValues values = new ContentValues();
        values.put(tabla_PLAYLIST_CANCION.ColumnasPlayCanciones.COLUMNA_ID_PLAYLIST, playList.getId());
        values.put(tabla_PLAYLIST_CANCION.ColumnasPlayCanciones.COLUMNA_ID_CANCION, cancion.getId());

        db.insert(tabla_PLAYLIST_CANCION.TABLE_NAME, null, values);
    }

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

<<<<<<< HEAD
=======
    /*
     * Metodo que usaremos para comprobar si hay una contraseña asociada al usurio que esta
     * intentando ingresar en la base de datos
     */
>>>>>>> 36a4bef44323b8c21e384190fbe802170dbb2151
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

<<<<<<< HEAD
    //No se pa que sirve el Supress este (Pero da error si no)
=======
    /*
     * Metodo que usaremos para obtener el usuario por el
     * correo
     */
>>>>>>> 36a4bef44323b8c21e384190fbe802170dbb2151
    public String obtenerNombrePorCorreo(String correo) {
        int indexNombre = 0;
        String nombre = "";
        // Realiza la consulta
        String consulta = "SELECT usuario FROM USUARIOS WHERE email = ?";
        Cursor cursor = getReadableDatabase().rawQuery(consulta, new String[]{correo});
<<<<<<< HEAD
=======

>>>>>>> 36a4bef44323b8c21e384190fbe802170dbb2151
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

<<<<<<< HEAD
=======
    /*
     * Metodo que usaremos
     */
>>>>>>> 36a4bef44323b8c21e384190fbe802170dbb2151
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

<<<<<<< HEAD
=======
    /*
     * Metodo que usaremos para recuperar todas las listas
     * asociadas al usuario actual de la aplicacion
     */

    public List<PlayList> recuperarListasUsuario(Usuarios usuarioActual) {
        SQLiteDatabase db = this.getReadableDatabase(); // obtenemos la base de datos actual
        String consulta = "SELECT * FROM PLAYLIST WHERE usuario = ?";
        Cursor cursor = getReadableDatabase().rawQuery(consulta, new String[]{usuarioActual.getCorreo()});
        long idPlaylist;
        int nombrePlaylistIndex;
        String nombrePlaylist = "";
        PlayList playList;

        // Lista para almacenar las playlist del usuario
        List<PlayList> playlists = new ArrayList<PlayList>();

        // verificamos si se obtuvo resultados
        while (cursor.moveToNext()) {
            // Obtén los datos de cada fila y crea un objeto Playlist
            idPlaylist = cursor.getColumnIndex(tabla_PLAYLIST.ColumnasPlayList.COLUMNA_ID);
            nombrePlaylistIndex = cursor.getColumnIndex(tabla_PLAYLIST.ColumnasPlayList.COLUMNA_NOMBRE);

            // en caso de que haya playlists las agregamos a la lista
            if (nombrePlaylistIndex > 0) {
                nombrePlaylist = cursor.getString(nombrePlaylistIndex);
                playList = new PlayList(idPlaylist, nombrePlaylist,usuarioActual.getCorreo());

                playlists.add(playList);
            }

        }

        // Cierra el cursor después de usarlo
        cursor.close();

        return playlists;
    }

>>>>>>> 36a4bef44323b8c21e384190fbe802170dbb2151

    //Método que comprueba y devuelve si la base de datos está creada
    public boolean isDatabaseExists(Context context) {
        File dbFile = context.getDatabasePath(DATABASE_NAME);
        return dbFile.exists();
    }
}