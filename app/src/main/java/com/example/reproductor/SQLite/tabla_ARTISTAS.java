package com.example.reproductor.SQLite;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class tabla_ARTISTAS {

    public static final String TABLE_NAME = "ARTISTAS";
    public static final String STRING_TYPE = "text";

    public static class ColumnasArtistas {
    public static final String COLUMNA_ID = "id";
    public static final String COLUMNA_NOMBRE = "nombre";
    }

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ColumnasArtistas.COLUMNA_ID + " INTEGER PRIMARY KEY," +
                    ColumnasArtistas.COLUMNA_NOMBRE + " TEXT)";

    //Insertamos valores por defecto
    public static final String INSERTA_DEFECTO =
            "insert into "+TABLE_NAME+" values(null, null)";

    private db_MelodyMixer openHelper;
    private SQLiteDatabase database;


    //Creando una instancia hacia la base de datos
    public tabla_ARTISTAS(Context context) {
        openHelper = new db_MelodyMixer(context);
        database = openHelper.getWritableDatabase();
    }
}
