package SQLite;

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
}
