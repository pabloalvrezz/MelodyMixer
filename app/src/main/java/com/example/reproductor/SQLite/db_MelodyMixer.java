package com.example.reproductor.SQLite;
import android.annotation.SuppressLint;
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

    //Método para añadir fila a USUARIOS
    public void addUsuario(SQLiteDatabase db, Usuarios usuario) {
        ContentValues values = new ContentValues();
        values.put(tabla_USUARIOS.ColumnasUsuarios.COLUMNA_ID, usuario.getEmail());
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
        values.put(tabla_PLAYLIST.ColumnasPlayList.COLUMNA_ID_USUARIO, playList.getUsuario().getEmail());

        db.insert(tabla_PLAYLIST.TABLE_NAME, null, values);
    }
    //Método para añadir fila a CANCIONES
    public void addCancion(SQLiteDatabase db, Canciones cancion){
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

    public boolean existeUsuarioCorreo(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Especifica la tabla y las columnas que deseas consultar
        String[] columnas = {tabla_USUARIOS.ColumnasUsuarios.COLUMNA_ID};

        // Especifica la cláusula WHERE para buscar el correo específico
        String seleccion = tabla_USUARIOS.ColumnasUsuarios.COLUMNA_ID + " = ?";
        String[] argumentos = {email};

        // Realiza la consulta
        Cursor cursor = db.query(tabla_USUARIOS.TABLE_NAME, columnas, seleccion, argumentos, null, null, null);

        // Verifica si se devolvieron filas
        boolean existeUsuario = cursor.getCount() > 0;

        // Cierra el cursor y la base de datos
        cursor.close();
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
        boolean existeUsuario = cursor.getCount() > 0;

        // Cierra el cursor y la base de datos
        cursor.close();
        return existeUsuario;
    }

    //No se pa que sirve el Supress este (Pero da error si no)
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


    //Método que comprueba y devuelve si la base de datos está creada
    public boolean isDatabaseExists(Context context) {
        File dbFile = context.getDatabasePath(DATABASE_NAME);
        return dbFile.exists();
    }
}
