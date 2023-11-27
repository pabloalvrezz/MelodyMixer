package com.example.reproductor.MainPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reproductor.API.ApiManager;
import com.example.reproductor.API.ApiResponse;
import com.example.reproductor.Entities.Canciones;
import com.example.reproductor.R;
import com.example.reproductor.Reproductor.Reproductor;

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
    private TextView txtResultados;
    private List<Canciones> listaCanciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searcher);

        apiManager = new ApiManager();

        recyclerViewSearchResults = findViewById(R.id.rvhResultadosBusqueda);
        cancionAdapter = new CancionAdapter(this, new ArrayList<>());
        recyclerViewSearchResults.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSearchResults.setAdapter(cancionAdapter);

        txtResultados = (TextView)findViewById(R.id.txtResultadosCanciones);
        srvBuscadorCanciones = findViewById(R.id.srvBuscadorCanciones);

        // agregamos el escuchador para buscar en la api cuando pulse enter el usuario
        srvBuscadorCanciones.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                realizarBusqueda(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Si el nuevo texto está vacío, actualiza la lista a null
                if (newText.isEmpty()) {
                    actualizarListaCanciones(null);

                    txtResultados.setText("Resultados: ");

                }
                return true;
            }
        });

        // agregamos el escuchador para saber que cancion ha pulsado el usuario
        cancionAdapter.setOnItemClickListener(new CancionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                // Obtenemos la cancion elegida por el usuario
                Canciones cancionSeleccionada = listaCanciones.get(position);

                Intent intent = new Intent(BuscadorCanciones.this, Reproductor.class);
                intent.putExtra("cancionSeleccionada", cancionSeleccionada);
                startActivity(intent);
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
                    listaCanciones = apiManager.obtenerCanciones(apiResponse);
                    actualizarListaCanciones(listaCanciones);
                    txtResultados.setText("Se han encontrado: " + listaCanciones.size() + " resultados");
                } else {
                    txtResultados.setText("No hay resultados");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Ha ocurrido un error al realizar la búsqueda", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*
     * Método que usaremos para actualizar la lista de canciones que se muestra en el RecyclerView
     */
    private void actualizarListaCanciones(List<Canciones> listaCanciones) {
        cancionAdapter.setListaCanciones(listaCanciones);
    }


}
