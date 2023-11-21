package com.example.reproductor.Entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Canciones {

    //Atributos
    private long id;
    private String nombre;
    private Artistas artista;
    private Albumes album;
    private int duracion;
    private String genero;

}
