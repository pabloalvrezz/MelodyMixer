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
        public static final String COLUMNA_IMAGE_URL = "imageUrl";
        public static final String COLUMNA_PREVIEW_URL = "previewUrl";
    }

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ColumnasCanciones.COLUMNA_ID + " INTEGER PRIMARY KEY," +
                    ColumnasCanciones.COLUMNA_TITULO + " TEXT," +
                    ColumnasCanciones.COLUMNA_ARTISTA_ID + " INTEGER," +
                    ColumnasCanciones.COLUMNA_DURACION + " INTEGER," +
                    ColumnasCanciones.COLUMNA_GENERO + " TEXT," +
                    ColumnasCanciones.COLUMNA_IMAGE_URL + " TEXT," +
                    ColumnasCanciones.COLUMNA_PREVIEW_URL + " TEXT," +
                    "FOREIGN KEY(" + ColumnasCanciones.COLUMNA_ARTISTA_ID + ") REFERENCES " + tabla_ARTISTAS.TABLE_NAME + "(" + tabla_ARTISTAS.ColumnasArtistas.COLUMNA_ID + "))";

    //Insertamos valores por defecto
    //Insertamos la primera cancion
    public static final String INSERTA_CANCION1 = "INSERT INTO " + TABLE_NAME + " (" +
            ColumnasCanciones.COLUMNA_ID+ ", " +
            ColumnasCanciones.COLUMNA_TITULO + ", " +
            ColumnasCanciones.COLUMNA_ARTISTA_ID + ", " +
            ColumnasCanciones.COLUMNA_DURACION + ", " +
            ColumnasCanciones.COLUMNA_GENERO + ", " +
            ColumnasCanciones.COLUMNA_IMAGE_URL + ", " +
            ColumnasCanciones.COLUMNA_PREVIEW_URL + ") " +
            "VALUES " +
            "(390992640 , 'Good Vibrations (Glee Cast Version)', 315816847, 253333, 'Pop', " +
            "'https://is1-ssl.mzstatic.com/image/thumb/Music/1d/06/9b/mzi.benjyygo.jpg/100x100bb.jpg', " +
            "'https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview115/v4/3f/6f/b0/3f6fb0cc-74e4-3f81-362c-9071c74311c2/mzaf_18434143732010597122.plus.aac.p.m4a')";

    public static final String INSERTA_CANCION2 = "INSERT INTO " + TABLE_NAME + " (" +
            ColumnasCanciones.COLUMNA_ID+ ", " +
            ColumnasCanciones.COLUMNA_TITULO + ", " +
            ColumnasCanciones.COLUMNA_ARTISTA_ID + ", " +
            ColumnasCanciones.COLUMNA_DURACION + ", " +
            ColumnasCanciones.COLUMNA_GENERO + ", " +
            ColumnasCanciones.COLUMNA_IMAGE_URL + ", " +
            ColumnasCanciones.COLUMNA_PREVIEW_URL + ") " +
            "VALUES " +
            "(878000348 , 'Respect', 98742, 98742, 'Pop', " +
            "'https://is1-ssl.mzstatic.com/image/thumb/Music115/v4/47/0d/c0/470dc076-70e9-be30-07e5-ecee641a7604/603497902460.jpg/100x100bb.jpg', " +
            "'https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview116/v4/db/95/ca/db95ca9e-0f46-1463-eaa4-26a138272607/mzaf_15311046442965620066.plus.aac.p.m4a')";

    private db_MelodyMixer openHelper;
    private SQLiteDatabase database;

    //Creando una instancia hacia la base de datos
    public tabla_CANCIONES(Context context) {
        openHelper = new db_MelodyMixer(context);
        database = openHelper.getWritableDatabase();
    }
}
