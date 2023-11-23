package com.example.reproductor.MainPage;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reproductor.Entities.Usuarios;
import com.example.reproductor.R;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MainPage extends AppCompatActivity {

    private Usuarios usuarioActual;
    private List<ListasHorizontal> elementos;
    private RecyclerView rvhListasRecomendadas, rvhListasDeUsuario;
    private TextView txtSaludo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        usuarioActual = (Usuarios) this.getIntent().getSerializableExtra("usuario");
        txtSaludo = (TextView) findViewById(R.id.txtSaludo);

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

        if (horaDelDia >= 7 && horaDelDia < 13) {
            txtSaludo.setText("Buenos días, " + usuarioActual.getUsuario());
        } else if (horaDelDia >= 13 && horaDelDia < 20) {
            txtSaludo.setText("Buenas tardes, " + usuarioActual.getUsuario());
        } else {
            txtSaludo.setText("Buenas noches, " + usuarioActual.getUsuario());
        }
    }
}
