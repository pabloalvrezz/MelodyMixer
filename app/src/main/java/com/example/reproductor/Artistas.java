package com.example.reproductor;

public class Artistas {
    private long id;
    private String nombre;
    // Puedes agregar más atributos según tus necesidades

    // Constructor
    public Artistas(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Métodos getter y setter
    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
