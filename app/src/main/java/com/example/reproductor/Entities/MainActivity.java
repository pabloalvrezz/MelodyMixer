package com.example.reproductor.Entities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reproductor.API.ApiManager;
import com.example.reproductor.API.ApiResponse;
import com.example.reproductor.CancionAdapter;
import com.example.reproductor.MainPage.MainPage;
import com.example.reproductor.R;
import com.example.reproductor.SQLite.db_MelodyMixer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText editTextBuscar;
    private Button btnBuscar;
    private RecyclerView recyclerViewCanciones;
    private CancionAdapter cancionAdapter;
    private ApiManager apiManager;
    private Button btnSignUp;
    private EditText edNombreUp, edApellidos, edContraseñaUp, edCorreoUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singup);

        db_MelodyMixer database = new db_MelodyMixer(this);     //Creamos el objeto de la BD
        SQLiteDatabase db = database.getWritableDatabase();

        edCorreoUp = findViewById(R.id.edtCorreoUp);    //EditText del correo (Registro)
        edNombreUp = findViewById(R.id.edtNombreUp);    //EditText del nombre (Registro)
        edApellidos = findViewById(R.id.edtApellidos);      //EditText de los apellidos (Registro)
        edContraseñaUp = findViewById(R.id.edtContraseñaUp);    //EditText de la contraseña (Registro)
        btnSignUp = findViewById(R.id.btnSingUp);

        //Método pa cuando pulse el botón de registro
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                //Comprobamos que ninguno de los campos estén vacíos
                if ((edContraseñaUp.getText() == null) || (edNombreUp.getText() == null) || (edApellidos.getText() == null) || (edCorreoUp.getText() == null)) {

                    Toast.makeText(getApplicationContext(), "Rellene todos los campos", Toast.LENGTH_SHORT).show(); //Mostramos el mensaje de ERROR
                }
                else {
                    Usuarios registro = new Usuarios(edCorreoUp.getText().toString(), edNombreUp.getText().toString(), edApellidos.getText().toString(), edContraseñaUp.getText().toString());
                    database.addUsuario(db, registro);
                    intent = new Intent(MainActivity.this, MainPage.class);
                    intent.putExtra("usuario", registro);
                    startActivity(intent);
                }
            }
        });
    }

    private void realizarBusqueda(String busqueda) {
        apiManager.buscarCancion(busqueda, new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();
                    List<String> nombresCanciones = apiManager.obtenerNombresCanciones(apiResponse);
                    actualizarListaCanciones(nombresCanciones);
                } else {
                    // Manejar la respuesta de error de la API
                    Toast.makeText(MainActivity.this, "Error al buscar canciones", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // Manejar el fallo de la solicitud
                Toast.makeText(MainActivity.this, "Error de red", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void actualizarListaCanciones(List<String> nombresCanciones) {
        // Limpiar la lista actual de canciones y agregar las nuevas
        cancionAdapter.setListaCanciones(nombresCanciones);
        cancionAdapter.notifyDataSetChanged();
    }
}
