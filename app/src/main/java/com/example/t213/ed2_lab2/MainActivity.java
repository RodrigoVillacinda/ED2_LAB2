package com.example.t213.ed2_lab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ZigZag.ZigZag;
import ZigZag.DescifradoZigZag;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ZigZag hola= new ZigZag(6,"ESTEESUNTEXTO ESTEESUNTEXTO ESTEESUNTEXTO ESTEESUNTEXTO ESTEESUNTEXTO ESTEESUNTEXTO ESTEESUNTEXTO ESTEESUNTEXTO ESTEESUNTEXTO ESTEESUNTEXTO ESTEESUNTEXTO ESTEESUNTEXTO ESTEESUNTEXTO ESTEESUNTEXTO ESTEESUNTEXTO ESTEESUNTEXTO ESTEESUNTEXTO ESTEESUNTEXTO ESTEESUNTEXTO ESTEESUNTEXTO ESTEESUNTEXTO ESTEESUNTEXTO ESTEESUNTEXTO ESTEESUNTEXTO ESTEESUNTEXTO ESTEESUNTEXTO ESTEESUNTEXTO ESTEESUNTEXTO ESTEESUNTEXTO");

        String yiy="";
        yiy = hola.Cifrado();
        DescifradoZigZag prueba= new DescifradoZigZag(6, yiy);
        prueba.ListaDescifrado();
        String algo="";
        algo=prueba.Descifrado();
        int p=0;
        //Prueba de zig zag
    }
}
