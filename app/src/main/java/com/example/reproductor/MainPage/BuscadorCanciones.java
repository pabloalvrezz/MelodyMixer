package com.example.reproductor.MainPage;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

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


        srvBuscadorCanciones = findViewById(R.id.srvBuscadorCanciones);

        srvBuscadorCanciones.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                realizarBusqueda(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Este método se llama cuando cambia el texto en el SearchView
                // Puedes agregar lógica aquí para filtrar la lista según el nuevo texto

                // Si el nuevo texto está vacío, actualiza la lista a null
                if (newText.isEmpty()) {
                    actualizarListaCanciones(null);

                }

                return true;
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
