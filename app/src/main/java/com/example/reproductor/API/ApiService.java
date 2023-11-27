package com.example.reproductor.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// Interfaz que define las operaciones de la API de iTunes
public interface ApiService {

    // Método para buscar una canción por término en la API de iTunes
    @GET("search")
    Call<ApiResponse> buscarCancion(@Query("term") String term);
}
