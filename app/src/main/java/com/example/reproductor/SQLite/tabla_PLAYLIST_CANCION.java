package com.example.reproductor.SQLite;
<<<<<<< HEAD
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.reproductor.Entities.Canciones;
import com.example.reproductor.Entities.PlayList;

=======

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

>>>>>>> b9a99f4fa3390dc09788601da8d7ac06790df003
public class tabla_PLAYLIST_CANCION {

    //Variables por defecto
    public static final String TABLE_NAME = "PLAYLIST_CANCION";
    public static final String STRING_TYPE = "text";

<<<<<<< HEAD
    //Constructor para cada campo de la tabla
    public static class ColumnasPlayCanciones {
        public static final String COLUMNA_ID_PLAYLIST = "id_playlist";
        public static final String COLUMNA_ID_CANCION = "id_cancion";
    }

=======
>>>>>>> b9a99f4fa3390dc09788601da8d7ac06790df003
    //Sentencia SQL para crear la tabla
    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ColumnasPlayCanciones.COLUMNA_ID_PLAYLIST + " TEXT," +
                    ColumnasPlayCanciones.COLUMNA_ID_CANCION + " TEXT," +
                    "PRIMARY KEY (" + ColumnasPlayCanciones.COLUMNA_ID_PLAYLIST + ", " + ColumnasPlayCanciones.COLUMNA_ID_CANCION + ")," +
                    "FOREIGN KEY(" + ColumnasPlayCanciones.COLUMNA_ID_PLAYLIST + ") REFERENCES " + tabla_PLAYLIST.TABLE_NAME + "(" + tabla_PLAYLIST.ColumnasPlayList.COLUMNA_ID + ")," +
                    "FOREIGN KEY(" + ColumnasPlayCanciones.COLUMNA_ID_CANCION + ") REFERENCES " + tabla_CANCIONES.TABLE_NAME + "(" + tabla_CANCIONES.ColumnasCanciones.COLUMNA_ID + "))";

    //Insertamos valores por defecto
<<<<<<< HEAD

    public static final String INSERTA_DEFECTO =
            "insert into "+TABLE_NAME+" values(null, null)";
    public static final String INSERTA_POP =
            "insert into "+TABLE_NAME+" values(2, 390992640)";

    public static final String INSERTA_POP_2 =
            "insert into "+TABLE_NAME+" values(2, 878000348)";




    private db_MelodyMixer openHelper;
    private SQLiteDatabase database;


=======
    public static final String INSERTA_DEFECTO =
            "insert into " + TABLE_NAME + " values(null, null, null)";
    public static final String INSERTA_POP =
            "insert into " + TABLE_NAME + " values(2, 390992640)";
    public static final String INSERTA_POP_2 =
            "insert into " + TABLE_NAME + " values(2, 878000348)";
    private db_MelodyMixer openHelper;
    private SQLiteDatabase database;

>>>>>>> b9a99f4fa3390dc09788601da8d7ac06790df003
    //Creando una instancia hacia la base de datos
    public tabla_PLAYLIST_CANCION(Context context) {
        openHelper = new db_MelodyMixer(context);
        database = openHelper.getWritableDatabase();
    }

<<<<<<< HEAD
=======
    //Constructor para cada campo de la tabla
    public static class ColumnasPlayCanciones {
        public static final String COLUMNA_ID_PLAYLIST = "id_playlist";
        public static final String COLUMNA_ID_CANCION = "id_cancion";
    }

>>>>>>> b9a99f4fa3390dc09788601da8d7ac06790df003
}
