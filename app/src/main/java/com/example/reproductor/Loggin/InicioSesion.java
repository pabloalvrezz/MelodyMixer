package com.example.reproductor.Loggin;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reproductor.Entities.Usuarios;
import com.example.reproductor.MainPage.MainPage;
import com.example.reproductor.R;
import com.example.reproductor.SQLite.db_MelodyMixer;

import androidx.appcompat.app.AppCompatActivity;

public class InicioSesion extends AppCompatActivity {

    private Button btnSignIn;
    private TextView txvSignUp;
    private EditText edContraseñaIn, edCorreoIn;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singin);

        db_MelodyMixer database = new db_MelodyMixer(this);
        SQLiteDatabase db = database.getReadableDatabase();

        btnSignIn = findViewById(R.id.btnSingIn);
        txvSignUp = findViewById(R.id.txvSignUp);
        edContraseñaIn = findViewById(R.id.edtContraseñaIn);
        edCorreoIn = findViewById(R.id.edtCorreoIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if ((!database.existeUsuarioContraseña(edContraseñaIn.getText().toString())) || (!database.existeUsuarioCorreo(edCorreoIn.getText().toString())))
                {
                    Toast.makeText(getApplicationContext(), "No existe un usuario con estas credenciales", Toast.LENGTH_SHORT).show(); //Mostramos el mensaje de ERROR
                }
                else
                {
                    Usuarios registro = new Usuarios(edCorreoIn.getText().toString(), database.obtenerNombrePorCorreo(edCorreoIn.getText().toString()),database.obtenerApellidosPorCorreo(edCorreoIn.getText().toString()),edContraseñaIn.getText().toString());
                    database.addUsuario(db, registro);
                    intent = new Intent(InicioSesion.this, MainPage.class);
                    intent.putExtra("usuario", registro);
                    startActivity(intent);
                }
            }
        });
    }
}
