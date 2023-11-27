package com.example.reproductor.Entities;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Canciones implements Serializable {

    //Atributos
    private long id;
    private String nombre;
    private String artistaNombre;
    private int duracion;
    private String genero;
    private String linkImage;
    private String linkPreview;


}
