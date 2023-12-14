package com.example.reproductor.Entities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.example.reproductor.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class AcercaDe extends AppCompatActivity {

    TextView txvAcercaDe;
    CardView cardView;
    ImageView imgLogo;
    TextView txvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acercade);

        Toolbar toolbar = findViewById(R.id.tlbAtrasAcercaDe);
        setSupportActionBar(toolbar);

        txvAcercaDe = (TextView)findViewById(R.id.txvAcercaDe);
        cardView = (CardView) findViewById(R.id.cardView);
        imgLogo = (ImageView)findViewById(R.id.imgLogo);
        txvInfo = (TextView)findViewById(R.id.txvInfo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_atras, menu);
        return true;
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
