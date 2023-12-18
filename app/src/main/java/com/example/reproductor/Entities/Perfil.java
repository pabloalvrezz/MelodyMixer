package com.example.reproductor.Entities;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.bumptech.glide.load.engine.Resource;
import com.example.reproductor.R;
import com.example.reproductor.SQLite.db_MelodyMixer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

public class Perfil extends AppCompatActivity
{
    String Usuario;
    String Correo;
    String Apellidos;
    String Contraseña;

    //Creamos los controles necesarios
    ImageButton btnBack;
    Switch swModoOscuro;
    ImageView imvwFotoPerfil;
    EditText etxNombre;
    EditText etxApellidos;
    EditText etxContrasenia;
    Button btnActualizar;

    db_MelodyMixer database;
    SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);

        Toolbar toolbar = findViewById(R.id.tlbAtrasAcercaDe);
        setSupportActionBar(toolbar);

        //Vinculamos los controles con el layout
        btnBack = (ImageButton) findViewById(R.id.btnBack);//boton volver atras, en la toolbar
        swModoOscuro = findViewById(R.id.swModoOscuro);//Switch para cambiar entre modo claro y oscuro
        imvwFotoPerfil = (ImageView) findViewById(R.id.imvwFotoPerfil);//Foto de perfil del usuario
        etxNombre = (EditText) findViewById(R.id.etxNombre);//Edittext del nombre de usuario
        etxApellidos = (EditText) findViewById(R.id.etxApellidos);//Edittext del nombre de usuario
        etxContrasenia = (EditText) findViewById(R.id.etxContrasenia);//Edittext del nombre de usuario
        btnActualizar = (Button) findViewById(R.id.btnActualizar);//Boton para actualizar la informacion

        //Cojemos los datos del usuario actual
        Correo = getIntent().getExtras().getString("correo");
        Usuario = getIntent().getExtras().getString("nombre");
        Apellidos = getIntent().getExtras().getString("apellidos");
        Contraseña = getIntent().getExtras().getString("contraseña");

        //Actualizamos textBox
        etxNombre.setText(Usuario);
        etxApellidos.setText(Apellidos);
        etxContrasenia.setText(Contraseña);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        swModoOscuro.setChecked(true);

        if (getResources().getBoolean(R.bool.ModoOscuro) == true)
        {
            swModoOscuro.setChecked(true);
        }
        else
        {
            swModoOscuro.setChecked(false);
        }

        swModoOscuro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(swModoOscuro.isChecked())
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    //Preferencia que guarda el estado del modo oscuro
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("ModoOscuro", true);
                    editor.apply();
                }
                else
                {
                    if (swModoOscuro.isChecked() == false)
                    {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        //Preferencia que guarda el estado del modo oscuro
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putBoolean("ModoOscuro", false);
                        editor.apply();
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_atras, menu);
        return true;
    }

    //Se ejecuta cuando pulsamos el botón de actualizar, que guarda los cambios hechos
    public void btnActualizar_onClick(View view)
    {
        //Comprobamos que tengan datos todos los texbox
        if((etxNombre.getText().toString().equals("")) || (etxApellidos.getText().toString().equals("")) || (etxContrasenia.getText().toString().equals("")))
        {
            database.Actualizardatos(db,Correo, etxNombre.getText().toString(),etxApellidos.getText().toString(),etxContrasenia.getText().toString());
            Toast.makeText(getApplicationContext(),"Introduzca TODOS los datos" , Toast.LENGTH_SHORT).show();
        }

        //Si los tiene, continuamos con la actualizacion de los datos
        else
        {
            //Mostramos un mensaje para confirmar la actualizacion
            Toast.makeText(getApplicationContext(),"Los datos se han actualizado correctamente, " + Usuario, Toast.LENGTH_SHORT).show();
        }

    }

    public void pulsacionCasilla(View View)
    {
        //Cuando se pulse un texbox cualquiera, se borra el contenido para que el usuario pueda escribir
        etxNombre.setText("");
        etxApellidos.setText("");
        etxContrasenia.setText("");
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Atras:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}
