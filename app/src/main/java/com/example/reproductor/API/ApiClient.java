package com.example.reproductor.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    // URL base de la API de iTunes
    private static final String BASE_URL = "https://itunes.apple.com/";

    // Instancia única de Retrofit
    private static Retrofit retrofit = null;

    // Método para obtener la instancia de Retrofit
    public static Retrofit getClient() {
        // Verifica si la instancia de Retrofit ya existe
        if (retrofit == null) {
            // Si no existe, crea una nueva instancia de Retrofit
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()) // Convierte JSON a objetos Java usando Gson
                    .build();
        }
        return retrofit;
    }
}
