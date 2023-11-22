package com.example.reproductor.Entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuarios {
    private String id;
    private String usuario;
    private String apellidos;
    private String contraseña;
}
