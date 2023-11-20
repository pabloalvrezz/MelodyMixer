package com.example.reproductor;
import java.util.List;

public class Albumes {

    private long id;
    private String titulo;
    private Artistas artista;
    private List<Canciones> canciones;

    // Constructor
    public Albumes(long id, String titulo, Artistas artista, List<Canciones> canciones) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.canciones = canciones;
    }

    // Métodos getter y setter
    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Artistas getArtista() {
        return artista;
    }

    public List<Canciones> getCanciones() {
        return canciones;
    }
}
