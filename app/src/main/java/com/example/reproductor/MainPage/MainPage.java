package com.example.reproductor.MainPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reproductor.API.ApiManager;
import com.example.reproductor.API.ApiResponse;
import com.example.reproductor.Entities.Canciones;
import com.example.reproductor.Entities.Usuarios;
import com.example.reproductor.R;

import java.util.Calendar;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MainPage extends AppCompatActivity {

    private Usuarios usuarioActual;
    private List<ListasHorizontal> elementos;
    private RecyclerView rvhListasRecomendadas, rvhListasDeUsuario;
    private ImageButton imbBuscador;
    private TextView txtSaludo;
    private ApiManager apiManager;
    private CancionAdapter cancionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        usuarioActual = (Usuarios) this.getIntent().getSerializableExtra("usuario");
        txtSaludo = (TextView) findViewById(R.id.txtSaludo);
        imbBuscador = (ImageButton)findViewById(R.id.imbBuscador);

        imbBuscador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPage.this, BuscadorCanciones.class);
                startActivity(intent);
            }
        });

        // establecemos el saludo
        this.establecerSaludo();

    }
    /*
     * Metodo utilizado para establecer el saludo dependiendo
     * de la hora del dia
     */
    private void establecerSaludo() {
        Calendar calendar = Calendar.getInstance();
        int horaDelDia = calendar.get(Calendar.HOUR_OF_DAY);

        if(horaDelDia >= 6 && horaDelDia <= 13)
            this.txtSaludo.setText("Buenos días " + this.usuarioActual.getUsuario());
        else{
            if(horaDelDia > 13 && horaDelDia <= 20)
                this.txtSaludo.setText("Buenas tardes " + this.usuarioActual.getUsuario());
            else
                this.txtSaludo.setText("Buenas noches " + this.usuarioActual.getUsuario());
        }

    }

    /*
     * Metodo que usaremos para realizar la busqueda de las canciones
     * en la API
     */
    private void realizarBusqueda(String busqueda) {
        apiManager.buscarCancion(busqueda, new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();
                    List<Canciones> nombresCanciones = apiManager.obtenerCanciones(apiResponse);
                    actualizarListaCanciones(nombresCanciones);
                } else {
                    // Manejar la respuesta de error de la API
                    Toast.makeText(MainPage.this, "Error al buscar canciones", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // Manejar el fallo de la solicitud
                Toast.makeText(MainPage.this, "Error de red", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*
     * Metodo que usaremos para actualizar la lista de canciones que busque el usuario
     */
    private void actualizarListaCanciones(List<Canciones> nombresCanciones) {
        // Limpiar la lista actual de canciones y agregar las nuevas
        cancionAdapter.setListaCanciones(nombresCanciones);
        cancionAdapter.notifyDataSetChanged();
    }
}
