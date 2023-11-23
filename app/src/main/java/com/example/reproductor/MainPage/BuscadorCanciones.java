package com.example.reproductor.MainPage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reproductor.API.ApiManager;
import com.example.reproductor.API.ApiResponse;
import com.example.reproductor.Entities.Canciones;
import com.example.reproductor.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuscadorCanciones extends AppCompatActivity {

    private RecyclerView recyclerViewSearchResults;
    private CancionAdapter cancionAdapter;
    private ApiManager apiManager;
    private Button btnBuscarCancion;
    private SearchView srvBuscadorCanciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searcher);

        apiManager = new ApiManager();

        recyclerViewSearchResults = findViewById(R.id.rvhResultadosBusqueda);
        cancionAdapter = new CancionAdapter(this, new ArrayList<>());
        recyclerViewSearchResults.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSearchResults.setAdapter(cancionAdapter);

        btnBuscarCancion = findViewById(R.id.btnBuscarCancion);
        srvBuscadorCanciones = findViewById(R.id.srvBuscadorCanciones);

        btnBuscarCancion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String busqueda = srvBuscadorCanciones.getQuery().toString();
                if (!busqueda.isEmpty()) {
                    realizarBusqueda(busqueda);
                } else {
                    Toast.makeText(BuscadorCanciones.this, "Ingrese un término de búsqueda", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*
     * Método que usaremos para realizar la búsqueda de las canciones en la API
     */
    private void realizarBusqueda(String busqueda) {
        apiManager.buscarCancion(busqueda, new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();
                    List<Canciones> listaCanciones = apiManager.obtenerCanciones(apiResponse);
                    actualizarListaCanciones(listaCanciones);
                } else {
                    // Manejar la respuesta de error de la API
                    // Puedes mostrar un mensaje de error en un TextView o Toast
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // Manejar el fallo de la solicitud
                // Puedes mostrar un mensaje de error en un TextView o Toast
            }
        });
    }

    /*
     * Método que usaremos para actualizar la lista de canciones que se muestra en el RecyclerView
     */
    private void actualizarListaCanciones(List<Canciones> listaCanciones) {
        // Limpiar la lista actual de canciones y agregar las nuevas
        cancionAdapter.setListaCanciones(listaCanciones);
    }
}
