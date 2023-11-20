package com.example.reproductor;

public class PlayList {
    private long id;
    private String nombre;
    private Usuarios usuario;

    // Constructor
    public PlayList(long id, String nombre, Usuarios usuario) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
    }

    // Métodos getter y setter
    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Usuarios getUsuario() {
        return usuario;
    }
}
