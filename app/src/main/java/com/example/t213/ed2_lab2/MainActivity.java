package com.example.t213.ed2_lab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ZigZag.ZigZag;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ZigZag hola= new ZigZag(3,"Prueba de zig zag");
        hola.ContedionoLista();
        hola.Compresion();
    }
}
