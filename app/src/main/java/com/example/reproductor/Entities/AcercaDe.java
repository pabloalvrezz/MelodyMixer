package com.example.reproductor.Entities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reproductor.R;

import org.w3c.dom.Text;

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

        txvAcercaDe = (TextView)findViewById(R.id.txvAcercaDe);
        cardView = (CardView) findViewById(R.id.cardView);
        imgLogo = (ImageView)findViewById(R.id.imgLogo);
        txvInfo = (TextView)findViewById(R.id.txvInfo);
    }
}
