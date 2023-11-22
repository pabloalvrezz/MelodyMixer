package com.example.reproductor.SQLite;

public class tabla_CANCIONES {
    public static final String TABLE_NAME = "sCANCIONES";

    public static class ColumnasCanciones {
        public static final String COLUMNA_ID = "id";
        public static final String COLUMNA_TITULO = "titulo";
        public static final String COLUMNA_ARTISTA_ID = "artista_id";
        public static final String COLUMNA_ALBUM_ID = "album_id";
        public static final String COLUMNA_DURACION = "duracion";
        public static final String COLUMNA_GENERO = "genero";
    }

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ColumnasCanciones.COLUMNA_ID + " INTEGER PRIMARY KEY," +
                    ColumnasCanciones.COLUMNA_TITULO + " TEXT," +
                    ColumnasCanciones.COLUMNA_ARTISTA_ID + " TEXT," +
                    ColumnasCanciones.COLUMNA_ALBUM_ID + " TEXT," +
                    ColumnasCanciones.COLUMNA_DURACION + " TEXT," +
                    ColumnasCanciones.COLUMNA_GENERO + " TEXT," +
                    "FOREIGN KEY(" + ColumnasCanciones.COLUMNA_ARTISTA_ID + ") REFERENCES " + tabla_ARTISTAS.TABLE_NAME + "(" + tabla_ARTISTAS.ColumnasArtistas.COLUMNA_ID + ")," +
                    "FOREIGN KEY(" + ColumnasCanciones.COLUMNA_ALBUM_ID + ") REFERENCES " + tabla_ALBUMES.TABLE_NAME + "(" + tabla_ALBUMES.ColumnasAlbumes.COLUMNA_ID + "))";
}
