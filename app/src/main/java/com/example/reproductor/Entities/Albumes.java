package com.example.reproductor.Entities;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Albumes {

    private long id;
    private String titulo;
    private Artistas artista;
    private List<Canciones> canciones;


}
