package com.example.t213.ed2_lab2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Inicio extends AppCompatActivity {

    Button btnZigZag;
    Button btnSDES;
    Button btnRSA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btnZigZag = findViewById(R.id.btnZigZag);
        btnSDES = findViewById(R.id.btnSDES);
        btnRSA = findViewById(R.id.btnRSA);

        btnZigZag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityZZ();
            }
        });

        btnSDES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitySDES();
            }
        });

        btnRSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityRSA();
            }
        });
    }

    public void openActivityZZ()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void openActivitySDES()
    {
        Intent intent = new Intent(this,SDES.class);
        startActivity(intent);
    }

    public void openActivityRSA()
    {
        Intent intent = new Intent(this,RSA.class);
        startActivity(intent);
    }

}
