package com.example.reproductor.MainPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reproductor.API.ApiManager;
import com.example.reproductor.Buscador.BuscadorCanciones;
import com.example.reproductor.Buscador.CancionAdapter;
import com.example.reproductor.Entities.Usuarios;
import com.example.reproductor.R;

import java.util.Calendar;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

                intent.putExtra("usuarioActual", usuarioActual);

                startActivity(intent);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                overridePendingTransition(0,0);

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

        if(horaDelDia > 6 && horaDelDia <= 12){
            this.txtSaludo.setText("Buenos días, " + usuarioActual.getUsuario());
        }
        else{
            if(horaDelDia > 12 && horaDelDia <= 20){
                this.txtSaludo.setText("Buenas tardes, " + usuarioActual.getUsuario());
            }
            else{
                this.txtSaludo.setText("Buenas noches, " + usuarioActual.getUsuario());
            }
        }

    }


}
