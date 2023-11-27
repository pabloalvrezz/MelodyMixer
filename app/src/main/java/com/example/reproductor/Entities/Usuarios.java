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
public class Usuarios implements Serializable {
    private String email;
    private String usuario;
    private String apellidos;
    private String contraseña;
}
