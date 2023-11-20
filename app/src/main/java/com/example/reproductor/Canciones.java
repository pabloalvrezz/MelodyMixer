package com.example.reproductor;

public class Canciones {

    //Atributos
    private long id;
    private String nombre;
    private Artistas artista;
    private Albumes album;
    private int duracion;
    private String genero;


    // Constructor
    public Canciones(long id, String nombre, Artistas artista, Albumes album, int duracion, String genero) {
        this.id = id;
        this.nombre = nombre;
        this.artista = artista;
        this.album = album;
        this.duracion = duracion;
        this.genero = genero;
    }

    // Métodos getter y setter
    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Artistas getArtista() {
        return artista;
    }

    public Albumes getAlbum() {
        return album;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getGenero(){
        return genero;
    }
}
