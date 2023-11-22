package com.example.reproductor.SQLite;

public class tabla_ALBUMES {

    public static final String TABLE_NAME = "ALBUMES";
    public static final String STRING_TYPE = "text";

    public static class ColumnasAlbumes {
        public static final String COLUMNA_ID = "id";
        public static final String COLUMNA_TITULO = "titulo";
        public static final String COLUMNA_ARTISTA = "artista";
    }

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" + ColumnasAlbumes.COLUMNA_ID + " TEXT PRIMARY KEY," +
                    ColumnasAlbumes.COLUMNA_TITULO + " TEXT," +
                    ColumnasAlbumes.COLUMNA_ARTISTA + " TEXT," +
                    "FOREIGN KEY(" + ColumnasAlbumes.COLUMNA_ARTISTA + ") REFERENCES " + tabla_ARTISTAS.TABLE_NAME + "(" + tabla_ARTISTAS.ColumnasArtistas.COLUMNA_ID + "))";
}
