package com.example.reproductor.SQLite;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class tabla_USUARIOS {
    public static final String TABLE_NAME = "USUARIOS";
    public static final String STRING_TYPE = "text";

    public static class ColumnasUsuarios {
        public static final String COLUMNA_ID = "id_usuario";
        public static final String COLUMNA_USUARIO = "usuario";
        public static final String COLUMNA_APELLIDOS = "apellidos";
        public static final String COLUMNA_CONTRASEÑA = "contraseña";
    }

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ColumnasUsuarios.COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ColumnasUsuarios.COLUMNA_USUARIO + " TEXT," +
                    ColumnasUsuarios.COLUMNA_APELLIDOS + " TEXT," +
                    ColumnasUsuarios.COLUMNA_CONTRASEÑA + " TEXT)";

    //Insertamos valores por defecto
    public static final String INSERTA_DEFECTO =
            "INSERT INTO " + TABLE_NAME + " (" +
                    ColumnasUsuarios.COLUMNA_USUARIO + ", " +
                    ColumnasUsuarios.COLUMNA_APELLIDOS + ", " +
                    ColumnasUsuarios.COLUMNA_CONTRASEÑA + ") " +
                    "VALUES ('admin', 'ApellidoAdmin', 'admin')";


    private db_MelodyMixer openHelper;
    private SQLiteDatabase database;

    //Creando una instancia hacia la base de datos
    public tabla_USUARIOS(Context context) {
        openHelper = new db_MelodyMixer(context);
        database = openHelper.getWritableDatabase();
    }
}
