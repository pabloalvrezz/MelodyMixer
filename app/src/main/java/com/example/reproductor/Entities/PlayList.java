package com.example.reproductor.Entities;

import java.io.Serializable;

import androidx.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayList implements Serializable {
    private long id;
    private String nombre;
    private String usuarioId;
    private String imgURLPlaylist;

    public PlayList(String nombre, String usuarioId, String imgURLPlaylist){
        this.nombre = nombre;
        this.usuarioId = usuarioId;
        this.imgURLPlaylist = imgURLPlaylist;
    }
}

