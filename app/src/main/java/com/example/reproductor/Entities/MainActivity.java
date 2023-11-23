package com.example.reproductor.Entities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reproductor.API.ApiManager;
import com.example.reproductor.API.ApiResponse;
import com.example.reproductor.CancionAdapter;
import com.example.reproductor.R;
import com.example.reproductor.SQLite.db_MelodyMixer;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText editTextBuscar;
    private Button btnBuscar;
    private RecyclerView recyclerViewCanciones;
    private CancionAdapter cancionAdapter;
    private ApiManager apiManager;
    private Button btnSignUp;
    private EditText edUsuario, edApellidos, edContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prueba_paginainicial);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db_MelodyMixer database = new db_MelodyMixer(this);
        SQLiteDatabase db = database.getWritableDatabase();

        edUsuario = findViewById(R.id.edtUsuario3);
        edApellidos = findViewById(R.id.edtUsuario4);
        edContraseña = findViewById(R.id.edtContraseña);
        btnSignUp = findViewById(R.id.btnSingUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_paginaprincipal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch (id){
            case R.id.Perfil:
                Intent perfil = new Intent(this, Perfil.class);
                startActivity(perfil);
                break;
            case R.id.AcercaDe:
                Intent acercade = new Intent(this, AcercaDe.class);
                startActivity(acercade);
                break;
            case R.id.Salir:
                this.finish();
                break;
        }
        return true;
    }
}
