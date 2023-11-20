package com.example.reproductor;

public class Usuarios {
    private String id;
    private String nombre;
    private String apellidos;

    // Constructor
    public Usuarios(String id, String nombre, String apellidos) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    // Métodos getter y setter
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }
}
