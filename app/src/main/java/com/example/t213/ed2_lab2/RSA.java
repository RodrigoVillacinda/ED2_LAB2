package com.example.t213.ed2_lab2;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import CIF_RSA.CifradoRSA;
import CIF_RSA.DescifradoRSA;
import CIF_RSA.LlaveRSA;
import S_DES.CifradoSDES;
import S_DES.LlaveSDES;

public class RSA extends AppCompatActivity {

    private static final int READ_REQUEST_CODE = 42;
    private static final int PERMISSION_REQUEST_STORAGE = 1000;

    //------------botones-----------------
    Button btnCargarArchivo;
    Button btnCifrar;
    Button btnDescifrar;
    Button btnGenerar;
    //-------------txt--------------------
    TextView tvOutput;
    TextView txtP;
    TextView txtQ;
    //-------------variables--------------
    String P;
    String Q;
    int kprivadaN;
    int kprivadaD;
    int kpublicN;
    int kpublicD;
    char[] charArray;
    ArrayList<String> listaBinarios = new ArrayList<String>();
    List<int[]> listaArrays = new ArrayList();
    int[] data;
    int[] keyy=new int[10];
    int[] llave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsa);



        CIF_RSA.LlaveRSA key = new LlaveRSA(11,23);
        int[] k = key.Claves();

        String pruba = "esto es una prueba";
        char charachter = pruba.charAt(0);
        int ascii = (int) charachter;
//
        //-----------------cifrado--------------------
        CifradoRSA cifrado = new CifradoRSA(8, k);
        BigInteger C = cifrado.Cifrado();

        //-----------------Descifrado--------------------
        DescifradoRSA descifrado = new DescifradoRSA(Integer.parseInt(C.toString()), k);
        BigInteger N = descifrado.Descifrado();

        
        double res=Math.pow(3,7);
        double mod = res % 253;

        BigInteger hola = new BigInteger("253");
        BigInteger val =pow(new BigInteger("13"), new BigInteger("147") );
        BigInteger r = val.mod(hola);
        int p = 144%11;
        int x =0;

        //error
        //Pidiendo permiso para acceder a almacenamiento
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_REQUEST_STORAGE);
        }

        //-------------------------------------------------mapeo-------------------------------------------------------------------
        btnCargarArchivo = findViewById(R.id.btnCargarArchivo);
        btnGenerar = findViewById(R.id.btnGenerar);
        btnCifrar = findViewById(R.id.btnCifrar);
        btnDescifrar = findViewById(R.id.btnDescifrar);

        tvOutput = findViewById(R.id.tvOutput);
        txtP = findViewById(R.id.txtP);
        txtQ = findViewById(R.id.txtQ);
        //-------------------------------------------------Botones-------------------------------------------------------------------------


        btnGenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                P = txtP.getText().toString();
                Q = txtQ.getText().toString();
                CIF_RSA.LlaveRSA key = new LlaveRSA(Integer.parseInt(P),Integer.parseInt(Q));
                llave = key.Claves();

                kprivadaN = llave[0]; //n
                kprivadaD = llave[1]; //d
                grabar("KeyPrivate", Integer.toHexString(kprivadaN) + "," + Integer.toHexString(kprivadaD));

                kpublicN = llave[2]; //n
                kpublicD = llave[2]; //e
                grabar("KeyPrivate", Integer.toHexString(kpublicN) + "," + Integer.toHexString(kpublicD));

            }
        });


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

                char[] character;
                String cifra="";
                for (int i=0; i<charArray.length; i++) {

                    character = charArray;
                    int ascii  = (int)character[i];
                    //-----------------cifrado--------------------
                    CifradoRSA cifrado = new CifradoRSA(ascii, llave);
                    BigInteger C = cifrado.Cifrado();
                    cifra = cifra + C.toString();
                }
                grabar("cifrado", cifra);
            }
        });

        btnDescifrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                char[] character;
                String descifra="";
                for (int i=0; i<charArray.length; i++) {

                    character = charArray;
                    int ascii  = (int)character[i];
                    //-----------------descifrado--------------------
                    DescifradoRSA descifrado = new DescifradoRSA(ascii, llave);
                    BigInteger N = descifrado.Descifrado();
                    descifra = descifra + N.toString();
                }
                grabar("descifrado", descifra);

            }
        });




    }

    public BigInteger pow(BigInteger base, BigInteger exponent) {
        BigInteger result = BigInteger.ONE;
        while (exponent.signum() > 0) {
            if (exponent.testBit(0)) result = result.multiply(base);
            base = base.multiply(base);
            exponent = exponent.shiftRight(1);
        }
        return result;
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
                charArray = lectura.toCharArray();
                String lec = "?rueba Prueba Prueba Prueba Prueba Prueba";
                charArray = lectura.toCharArray();
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
