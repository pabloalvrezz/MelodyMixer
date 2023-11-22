package SQLite;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
        /*db.execSQL(tabla_ARTISTA.SQL_CREATE_TABLE);
        db.execSQL(tabla_ALBUM.SQL_CREATE_TABLE);
        db.execSQL(tabla_CANCIONES.SQL_CREATE_TABLE);
        db.execSQL(tabla_PLAYLIST.SQL_CREATE_TABLE);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
