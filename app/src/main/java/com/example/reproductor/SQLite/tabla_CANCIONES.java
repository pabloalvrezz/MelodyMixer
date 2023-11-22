package com.example.reproductor.SQLite;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class tabla_CANCIONES {
    public static final String TABLE_NAME = "CANCIONES";

    public static class ColumnasCanciones {
        public static final String COLUMNA_ID = "id";
        public static final String COLUMNA_TITULO = "titulo";
        public static final String COLUMNA_ARTISTA_ID = "artista_id";
        public static final String COLUMNA_DURACION = "duracion";
        public static final String COLUMNA_GENERO = "genero";
    }

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ColumnasCanciones.COLUMNA_ID + " INTEGER PRIMARY KEY," +
                    ColumnasCanciones.COLUMNA_TITULO + " TEXT," +
                    ColumnasCanciones.COLUMNA_ARTISTA_ID + " TEXT," +
                    ColumnasCanciones.COLUMNA_DURACION + " TEXT," +
                    ColumnasCanciones.COLUMNA_GENERO + " TEXT," +
                    "FOREIGN KEY(" + ColumnasCanciones.COLUMNA_ARTISTA_ID + ") REFERENCES " + tabla_ARTISTAS.TABLE_NAME + "(" + tabla_ARTISTAS.ColumnasArtistas.COLUMNA_ID + "))";

    private db_MelodyMixer openHelper;
    private SQLiteDatabase database;


    //Creando una instancia hacia la base de datos
    public tabla_CANCIONES(Context context) {
        openHelper = new db_MelodyMixer(context);
        database = openHelper.getWritableDatabase();
    }
}
