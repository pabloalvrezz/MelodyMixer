package com.example.reproductor.SQLite;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class tabla_PLAYLIST_CANCION {

    //Variables por defecto
    public static final String TABLE_NAME = "PLAYLIST_CANCION";
    public static final String STRING_TYPE = "text";

    //Constructor para cada campo de la tabla
    public static class ColumnasPlayCanciones {
        public static final String COLUMNA_ID_PLAYLIST = "id_playlist";
        public static final String COLUMNA_ID_CANCION = "id_cancion";
    }

    //Sentencia SQL para crear la tabla
    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ColumnasPlayCanciones.COLUMNA_ID_PLAYLIST + " TEXT," +
                    ColumnasPlayCanciones.COLUMNA_ID_CANCION + " TEXT," +
                    "PRIMARY KEY (" + ColumnasPlayCanciones.COLUMNA_ID_PLAYLIST + ", " + ColumnasPlayCanciones.COLUMNA_ID_CANCION + ")," +
                    "FOREIGN KEY(" + ColumnasPlayCanciones.COLUMNA_ID_PLAYLIST + ") REFERENCES " + tabla_PLAYLIST.TABLE_NAME + "(" + tabla_PLAYLIST.ColumnasPlayList.COLUMNA_ID + ")," +
                    "FOREIGN KEY(" + ColumnasPlayCanciones.COLUMNA_ID_CANCION + ") REFERENCES " + tabla_CANCIONES.TABLE_NAME + "(" + tabla_CANCIONES.ColumnasCanciones.COLUMNA_ID + "))";

    private db_MelodyMixer openHelper;
    private SQLiteDatabase database;


    //Creando una instancia hacia la base de datos
    public tabla_PLAYLIST_CANCION(Context context) {
        openHelper = new db_MelodyMixer(context);
        database = openHelper.getWritableDatabase();
    }

}
