package com.example.reproductor.MainPage;

import android.os.Bundle;

<<<<<<< HEAD
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reproductor.Entities.Usuarios;
import com.example.reproductor.R;

import com.example.reproductor.Entities.Usuarios;
import com.example.reproductor.R;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class MainPage {

public class MainPage extends AppCompatActivity{
>>>>>>> origin/main

    private Usuarios usuarioActual;
    private List<ListasHorizontal> elementos;
    private RecyclerView rvhListasRecomendadas, rvhListasDeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        usuarioActual = (Usuarios) this.getIntent().getSerializableExtra("usuario");

    }

}
