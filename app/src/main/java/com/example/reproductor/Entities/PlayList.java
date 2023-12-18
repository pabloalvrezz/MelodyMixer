package com.example.reproductor.Entities;


<<<<<<< HEAD
=======
import java.io.Serializable;

>>>>>>> b9a99f4fa3390dc09788601da8d7ac06790df003
import androidx.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
<<<<<<< HEAD
public class PlayList {
=======
public class PlayList implements Serializable {
>>>>>>> b9a99f4fa3390dc09788601da8d7ac06790df003
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

