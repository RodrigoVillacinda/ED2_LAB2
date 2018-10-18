package com.example.t213.ed2_lab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import S_DES.DescifradoSDES;
import S_DES.CifradoSDES;
import S_DES.LlaveSDES;

public class SDES extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdes);

        //key: 1 0 1 0 0 0 0 0 1 0
        int[] keydata = {1,0,1,0,0,0,0,0,1,0};
        LlaveSDES key =new LlaveSDES(keydata);
        int[] k1 = key.EightPrermutationsLS1();
        int[] k2 = key.EightPrermutationsLS2();
        //K1: 1 0 1 0 0 1 0 0 (see in step 5)
        //K2: 0 1 0 0 0 0 1 1 (see in step 8)

        int[] key1 = {1,0,1,0,0,1,0,0};
        int[] key2 = {0,1,0,0,0,0,1,1};
        int[] entr = {0,1,1,0,1,1,0,1};
        CifradoSDES cifrado = new CifradoSDES(entr,key1,key2);
        int[] cif = cifrado.EightPermutationsIK2();
        //key : 0 1 0 0	0 1	1 0



        int x = 0;
       // CifradoSDES prueba = new CifradoSDES();


    }
}
