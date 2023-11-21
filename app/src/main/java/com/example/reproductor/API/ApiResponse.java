package com.example.reproductor.API;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// clase que usaremos para obtener una lista con los resultados de la busqueda a la API
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private List<ResultadosDeApi> results;

}
