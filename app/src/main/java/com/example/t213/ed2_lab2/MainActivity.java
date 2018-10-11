package com.example.t213.ed2_lab2;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import ZigZag.ZigZag;
import ZigZag.DescifradoZigZag;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_STORAGE = 1000;
    private static final int READ_REQUEST_CODE = 42;

    //Variables;
    Button btnCargar;
    Button btnDescifrar;
    TextView tvLectura;
    TextView tvDescifrar;
    EditText txtLlave;
    int nivel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ZigZag hola= new ZigZag(3,"ESTEESUNTEXTO");

        String yiy="";
        yiy = hola.Cifrado();
        DescifradoZigZag prueba= new DescifradoZigZag(3, yiy);
        prueba.ListaDescifrado();
        String algo="";
        algo=prueba.Descifrado();
        int p=0;
        //Prueba de zig zag



        //Pidiendo permiso para manejar almacenamiento externo
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_REQUEST_STORAGE);
        }

        btnCargar = (Button) findViewById(R.id.btnCargar);
        btnDescifrar = (Button) findViewById(R.id.btnDescifrar);
        tvLectura = (TextView) findViewById(R.id.tvLectura);
        tvDescifrar = (TextView) findViewById(R.id.tvDescifrar);
        txtLlave = (EditText) findViewById(R.id.txtLlave);

        btnCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nivel = txtLlave.getText().toString();
                nivel = Integer.parseInt(Nivel);
                performFileSearch();
            }
        });

        btnDescifrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TextoDescifrar=tvLectura.getText().toString();
                DescifradoZigZag prueba= new DescifradoZigZag(nivel, TextoDescifrar);
                String descifrado = prueba.Descifrado();
                tvDescifrar.setText(descifrado);
            }
        });


    }

    //Con esto se lee el contenido del archivo seleccionado
    private String readText(String input)
    {
        File file = new File(input);
        StringBuilder text = new StringBuilder();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine())!=null)
            {
                text.append(line);
                text.append("\n");
            }
            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return text.toString();
    }

    //Seleccionar los archivos desde el storage
    private void performFileSearch()
    {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/*");
        startActivityForResult(intent,READ_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK)
        {
            if (data != null)
            {
                Uri uri = data.getData();
                String path = uri.getPath();
                path = path.substring(path.indexOf(":") + 1);
                Toast.makeText(this,""+path,Toast.LENGTH_SHORT).show();

                String TextoComprimir=readText(path);

                ZigZag hola= new ZigZag(nivel,TextoComprimir);
                tvLectura.setText(hola.Cifrado());

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_STORAGE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this,"Permiso Otorgado",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this,"Permiso Denegado",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }


}
