package com.example.reproductor.SQLite;

public class tabla_PLAYLIST {

    public static final String TABLE_NAME = "PLAYLIST";

    public static class ColumnasPlayList {
        public static final String COLUMNA_ID = "id";
        public static final String COLUMNA_NOMBRE = "nombre";
        public static final String COLUMNA_ID_USUARIO = "usuario";
    }

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" + ColumnasPlayList.COLUMNA_ID + " INTEGER PRIMARY KEY," +
                    ColumnasPlayList.COLUMNA_NOMBRE + " TEXT," +
                    ColumnasPlayList.COLUMNA_ID_USUARIO + " TEXT," +
                    "FOREIGN KEY(" + ColumnasPlayList.COLUMNA_ID_USUARIO + ") REFERENCES " + tabla_USUARIOS.TABLE_NAME + "(" + tabla_USUARIOS.ColumnasUsuarios.COLUMNA_ID + "))";
}
