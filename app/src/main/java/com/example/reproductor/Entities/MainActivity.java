package com.example.reproductor.Entities;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reproductor.API.ApiManager;
import com.example.reproductor.API.ApiResponse;
import com.example.reproductor.CancionAdapter;
import com.example.reproductor.R;
import com.example.reproductor.SQLite.db_MelodyMixer;
import com.example.reproductor.SQLite.tabla_USUARIOS;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText editTextBuscar;
    private Button btnBuscar;
    private RecyclerView recyclerViewCanciones;
    private CancionAdapter cancionAdapter;
    private ApiManager apiManager;
    private db_MelodyMixer database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
         database = new db_MelodyMixer(this);
        this.insertarDatos();
    }

    private void insertarDatos() {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(tabla_USUARIOS.ColumnasUsuarios.COLUMNA_ID, 1); // Reemplaza 1 con el valor real del ID
        values.put(tabla_USUARIOS.ColumnasUsuarios.COLUMNA_USUARIO, "admin"); // Reemplaza 1 con el valor real del ID
        values.put(tabla_USUARIOS.ColumnasUsuarios.COLUMNA_APELLIDOS, "admin"); // Reemplaza 1 con el valor real del ID
        values.put(tabla_USUARIOS.ColumnasUsuarios.COLUMNA_CONTRASEÑA, "admin"); // Reemplaza 1 con el valor real del ID

        long newRowId = db.insert(tabla_USUARIOS.TABLE_NAME, null, values);

        if (newRowId != -1) {
            Toast.makeText(this, "Datos insertados correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al insertar datos", Toast.LENGTH_SHORT).show();
        }
    }


    private void realizarBusqueda(String busqueda) {
        apiManager.buscarCancion(busqueda, new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();
                    List<String> nombresCanciones = apiManager.obtenerNombresCanciones(apiResponse);
                    actualizarListaCanciones(nombresCanciones);
                } else {
                    // Manejar la respuesta de error de la API
                    Toast.makeText(MainActivity.this, "Error al buscar canciones", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // Manejar el fallo de la solicitud
                Toast.makeText(MainActivity.this, "Error de red", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void actualizarListaCanciones(List<String> nombresCanciones) {
        // Limpiar la lista actual de canciones y agregar las nuevas
        cancionAdapter.setListaCanciones(nombresCanciones);
        cancionAdapter.notifyDataSetChanged();
    }
}
