package SQLite;

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
                    ColumnasUsuarios.COLUMNA_ID + " TEXT PRIMARY KEY," +
                    ColumnasUsuarios.COLUMNA_USUARIO + " TEXT," + ColumnasUsuarios.COLUMNA_APELLIDOS + " TEXT," +
                    ColumnasUsuarios.COLUMNA_CONTRASEÑA + " TEXT)";
}
