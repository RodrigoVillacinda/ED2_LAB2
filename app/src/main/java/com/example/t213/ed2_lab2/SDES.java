package com.example.t213.ed2_lab2;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import S_DES.DescifradoSDES;
import S_DES.CifradoSDES;
import S_DES.LlaveSDES;

public class SDES extends AppCompatActivity {

    private static final int READ_REQUEST_CODE = 42;
    private static final int PERMISSION_REQUEST_STORAGE = 1000;

    Button btnCargarArchivo;
    Button btnCifrar;
    Button btnDescifrar;
    TextView tvOutput;
    TextView txtLlave;
    char[] charArray;
    ArrayList<String> listaBinarios = new ArrayList<String>();
    List<int[]> listaArrays = new ArrayList();
    int Llave;
    int[] data;
    int[] keyy=new int[10];
    int[] k1;
    int[] k2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdes);

        //Pidiendo permiso para acceder a almacenamiento
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_REQUEST_STORAGE);
        }

        btnCargarArchivo = findViewById(R.id.btnCargarArchivo);
        tvOutput = findViewById(R.id.tvOutput);
        btnCifrar = findViewById(R.id.btnCifrar);
        txtLlave = findViewById(R.id.txtLlave);
        btnDescifrar = findViewById(R.id.btnDescifrar);

        btnCargarArchivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Acá se llama a la acción para buscar los archivos
                performFileSearch();

            }
        });

        btnCifrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String llave = txtLlave.getText().toString();
                Llave = Integer.parseInt(llave);
                keyy =conversor(llave);

                LlaveSDES key =new LlaveSDES(keyy);
                k1 = key.EightPrermutationsLS1();
                k2 = key.EightPrermutationsLS2();
                int[] cif;
                int[] ciff;
                String cifra="";
                for (int i=0; i<listaArrays.size(); i++) {
                    int[] temporal=new int[8];
                    ciff = listaArrays.get(i);
                    if(ciff.length==8) {
                        temporal[0] = ciff[0];
                        temporal[1] = ciff[1];
                        temporal[2] = ciff[2];
                        temporal[3] = ciff[3];
                        temporal[4] = ciff[4];
                        temporal[5] = ciff[5];
                        temporal[6] = ciff[6];
                        temporal[7] = ciff[7];
                    }else{
                        if (ciff.length > 6) {
                            temporal[0] = 0;
                            temporal[1] = ciff[0];
                            temporal[2] = ciff[1];
                            temporal[3] = ciff[2];
                            temporal[4] = ciff[3];
                            temporal[5] = ciff[4];
                            temporal[6] = ciff[5];
                            temporal[7] = ciff[6];
                        } else {
                            temporal[0] = 0;
                            temporal[1] = 0;
                            temporal[2] = ciff[0];
                            temporal[3] = ciff[1];
                            temporal[4] = ciff[2];
                            temporal[5] = ciff[3];
                            temporal[6] = ciff[4];
                            temporal[7] = ciff[5];
                        }
                    }


                    CifradoSDES cifrado = new CifradoSDES(temporal, k1, k2);
                    cif = cifrado.EightPermutationsIK2();
                    cifra = cifra + Integer.toString(cif[0]) + Integer.toString(cif[1]) + Integer.toString(cif[2]) + Integer.toString(cif[3])
                    + Integer.toString(cif[4]) + Integer.toString(cif[5]) + Integer.toString(cif[6]) + Integer.toString(cif[7]);
                }
                grabar("S_DES", cifra);
            }
        });

        btnDescifrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //DescifradoSDES descifrado = new DescifradoSDES(cif, k1, k2);
                //int[] des =descifrado.DecryptionSDES(cif);


            }
        });


//terera


        //key: 1 0 1 0 0 0 0 0 1 0
        int[] keydata = {1,0,1,0,0,0,0,0,1,0};
        LlaveSDES key =new LlaveSDES(keydata);
        int[] k1 = key.EightPrermutationsLS1();
        int[] k2 = key.EightPrermutationsLS2();
        //K1: 1 0 1 0 0 1 0 0 (see in step 5)
        //K2: 0 1 0 0 0 0 1 1 (see in step 8)

        //{0,1,1,0,1,1,0,1};
        int[] key1 = {1,0,1,0,0,1,0,0};
        int[] key2 = {0,1,0,0,0,0,1,1};
        int[] entr = {0,0,0,0,0,0,0,0};
        CifradoSDES cifrado = new CifradoSDES(entr,k1,k2);
        int[] cif = cifrado.EightPermutationsIK2();
        //key : 0 1 0 0	0 1	1 0

        DescifradoSDES descifrado = new DescifradoSDES(cif, key1, key2);
        int[] des =descifrado.DecryptionSDES(cif);


        int x = 0;
       // CifradoSDES prueba = new CifradoSDES();


    }


    public int[] conversor(String texto){
        //texto = "001010100101011111011010101101010000001110101011";
        int digital[] = new int[texto.length()]; // estableciendo capacidad del arreglo con la longitud del texto
        int indice = 0;

        for(char valor : texto.toCharArray()){ // recorriendo caracteres del texto
            digital[indice] = Integer.valueOf(valor) - 48; // convirtiendo el caracter en numero respetando la tabla ASCII
            indice++;
        }


        return digital;
    }

    public void grabar(String nombre, String contenido) {

        try {
            File tarjeta = Environment.getExternalStorageDirectory();
            Toast.makeText(this,tarjeta.getAbsolutePath(),Toast.LENGTH_LONG).show();
            File file = new File(tarjeta.getAbsolutePath(), nombre + ".txt");
            OutputStreamWriter osw = new OutputStreamWriter(
                    new FileOutputStream(file));
            osw.write(contenido);
            osw.flush();
            osw.close();
            Toast.makeText(this, "Los datos fueron grabados correctamente",
                    Toast.LENGTH_SHORT).show();
            //et1.setText("");
            //et2.setText("");
        } catch (IOException ioe) {
            Toast.makeText(this, "No se pudo grabar",
                    Toast.LENGTH_SHORT).show();
        }
    }


    //Leer el contenido del archivo de texto
    private String readText(String input)
    {
        File file = new File(input);
        Toast.makeText(this, ""+file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        StringBuilder text = new StringBuilder();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null)
            {
                text.append(line);
                text.append(System.lineSeparator());
                //text.append("\n");
            }
            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return text.toString();

    }

    //Seleccionar archivo desde almacenamiento
    private void performFileSearch()
    {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK)
        {
            if (data != null)
            {
                Uri uri = data.getData();
                String path = uri.getPath();
                File archivo = new File(path);

                path = path.substring(path.indexOf(":") + 1);
                String path2 = Environment.getExternalStorageDirectory().getAbsolutePath()+"/";

                Toast.makeText(this, "" + path2+path, Toast.LENGTH_SHORT).show();

                String lectura = readText(path2+path);
                //charArray = lectura.toCharArray();
                String lec = "?rueba Prueba Prueba Prueba Prueba Prueba";
                charArray = lec.toCharArray();
                listaBinarios.clear();

                for (char c:charArray)
                {
                    String binario = Integer.toBinaryString(c);
                    listaBinarios.add(binario);
                }

                for (String s:listaBinarios)
                {
                    int[] enteros = new int[s.length()];
                    for (int i = 0; i < s.length(); i++) {
                        enteros[i] = Character.digit(s.charAt(i), 10);
                    }
                    listaArrays.add(enteros);
                }


                tvOutput.setText(lectura);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_STORAGE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Permiso Otorgado", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Permiso Denegado", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }


}
