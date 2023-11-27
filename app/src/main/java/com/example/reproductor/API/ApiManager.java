package com.example.reproductor.API;

import com.example.reproductor.Entities.Canciones;

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
        Call<ApiResponse> call = apiService.buscarCancion(term, "music");

        // Realizar la llamada de forma asíncrona y manejar la respuesta mediante el Callback
        call.enqueue(callback);
    }

    // Método para procesar la respuesta de la API y obtener los nombres de las canciones
    public List<Canciones> obtenerCanciones(ApiResponse apiResponse) {
        List<Canciones> canciones = new ArrayList<>();
        Canciones cancion;
<<<<<<< HEAD
=======

>>>>>>> 36a4bef44323b8c21e384190fbe802170dbb2151
        // Verificar si la respuesta tiene resultados
        if (apiResponse != null && apiResponse.getResults() != null) {
            List<ResultadosDeApi> resultados = apiResponse.getResults();

            // Iterar sobre la lista de resultados y extraer los nombres de las canciones
            for (ResultadosDeApi resultado : resultados) {
<<<<<<< HEAD
                // Verificar si el resultado es distinto de nulo
=======
                // Verificar si el resultado es distinto de nulo y es una cancion
>>>>>>> 36a4bef44323b8c21e384190fbe802170dbb2151
                if (resultado != null) {
                    cancion = new Canciones(resultado.getTrackId(), resultado.getTrackName() ,resultado.getArtistName(), resultado.getTrackTimeMillis(), resultado.getPrimaryGenreName(), resultado.getArtworkUrl100(), resultado.getPreviewUrl());
                    canciones.add(cancion);
                }
            }
        }

        return canciones;
    }
}
