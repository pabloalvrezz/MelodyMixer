package com.example.reproductor.API;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    // Interfaz para realizar solicitudes HTTP a la API de iTunes
    private final ApiService apiService;

    // Constructor para inicializar la instancia de Retrofit y ApiService
    public ApiManager() {
        // Configurar Retrofit con la URL base y el convertidor Gson
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crear una instancia de ApiService utilizando Retrofit
        apiService = retrofit.create(ApiService.class);
    }

    // Método para buscar una canción en la API de iTunes
    public void buscarCancion(String term, Callback<ApiResponse> callback) {
        // Crear una llamada a la API para buscar la canción por término
        Call<ApiResponse> call = apiService.buscarCancion(term);

        // Realizar la llamada de forma asíncrona y manejar la respuesta mediante el Callback
        call.enqueue(callback);
    }

    // Método para procesar la respuesta de la API y obtener los nombres de las canciones
    public List<String> obtenerNombresCanciones(ApiResponse apiResponse) {
        List<String> nombresCanciones = new ArrayList<>();

        // Verificar si la respuesta tiene resultados
        if (apiResponse != null && apiResponse.getResults() != null) {
            List<ResultadosDeApi> resultados = apiResponse.getResults();

            // Iterar sobre la lista de resultados y extraer los nombres de las canciones
            for (ResultadosDeApi resultado : resultados) {
                // Verificar si el resultado tiene el nombre de la canción
                if (resultado.getTrackName() != null) {
                    nombresCanciones.add(resultado.getTrackName());
                }
            }
        }

        return nombresCanciones;
    }
}
