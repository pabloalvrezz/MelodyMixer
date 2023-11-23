package com.example.reproductor.SQLite;
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

    //Comprueba que existe un correo en la base de datos
    public boolean existeUsuarioConCorreo(String correo) {
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
        db.close();
        return existeUsuario;
    }

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
        boolean existeContraseña = cursor.getCount() > 0;
        // Cierra el cursor y la base de datos
        cursor.close();
        db.close();
        return existeContraseña;
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
    public void addPlaylist(SQLiteDatabase db, PlayList playList){
        ContentValues values = new ContentValues();
        values.put(tabla_PLAYLIST.ColumnasPlayList.COLUMNA_ID, playList.getId());
        values.put(tabla_PLAYLIST.ColumnasPlayList.COLUMNA_NOMBRE, playList.getNombre());
        values.put(tabla_PLAYLIST.ColumnasPlayList.COLUMNA_ID_USUARIO, playList.getUsuario().getCorreo());

        db.insert(tabla_PLAYLIST.TABLE_NAME, null, values);
    }
    //Método para añadir fila a CANCIONES
    public void addCancion(SQLiteDatabase db, Canciones cancion){
        ContentValues values = new ContentValues();
        values.put(tabla_CANCIONES.ColumnasCanciones.COLUMNA_ID, cancion.getId());
        values.put(tabla_CANCIONES.ColumnasCanciones.COLUMNA_TITULO, cancion.getNombre());
        values.put(tabla_CANCIONES.ColumnasCanciones.COLUMNA_ARTISTA_NOMBRE, cancion.getArtista().getId());
        values.put(tabla_CANCIONES.ColumnasCanciones.COLUMNA_DURACION, cancion.getDuracion());
        values.put(tabla_CANCIONES.ColumnasCanciones.COLUMNA_GENERO, cancion.getGenero());
        values.put(tabla_CANCIONES.ColumnasCanciones.COLUMNA_IMAGE_URL, cancion.getLinkImage());

        db.insert(tabla_CANCIONES.TABLE_NAME, null, values);
    }
    //Método para añadir fila a ALBUMES
    public void addArtistas(SQLiteDatabase db, Artistas artista){
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

    //Método que comprueba y devuelve si la base de datos está creada
    public boolean isDatabaseExists(Context context) {
        File dbFile = context.getDatabasePath(DATABASE_NAME);
        return dbFile.exists();
    }
}
