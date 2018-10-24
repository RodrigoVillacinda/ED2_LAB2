package CIF_RSA;

import java.util.Random;

public class LlaveRSA {

    public LlaveRSA(int p, int q) {
        P = p;
        Q = q;
    }

    private int P;
    private int Q;

    //private int P =11;
   // private int Q =23;

    private int GenerarNumerosPrimos(){
        int primo = 0;
        int a =0;
        Random random = new Random();
        int contador=0;



        while (primo == 0 || primo == 1 || primo == 2 ){

            primo = random.nextInt(25);

        for(int I = 1; I <= primo; I++)
        {
            if(primo>2) {
                if ((primo % I) == 0 || primo / 2 == 1) {
                    contador++;
                }
            }
        }
            primo = ( contador <= 2)?primo:a;
            contador = 0;
           //if (primo<10){
             //   primo=0;
            //}
        }

        return primo;
    }

    //Generación de módulo N
    private int ModuloClaves(){
        while (P==Q){
            P = GenerarNumerosPrimos();
            Q =  GenerarNumerosPrimos();
        }
        int n=0;
        n=Q*P;
        return n;
    }

    //Función Euler
    private int Euler(){
        int euler = 0;
        euler = (P-1)*(Q-1);
        return euler;
    }

    //funcór que retorna "e"
    private int E(){
        int e = 1;
        int a = 1;
        int euler = Euler();

        while (e == 1) {
            e = GenerarNumerosPrimos();
            e = (e < euler) ? e : a;

        }
        //e=3;
        return e;
    }

    //Aritmetica modular, D
    private int AritmeticaModular(){
        int aritmeticamodular = 0;
        int n = 0;
        int e = E();
        int euler = Euler();

        while (aritmeticamodular!=1){
            n++;

            aritmeticamodular = (e*n)%(euler);
        }

        return n;
    }



    public int[] Claves(){
        int[] clavepublica= new int[4];

        //clave privada
        clavepublica[0] = ModuloClaves(); //n
        clavepublica[1] =  AritmeticaModular(); //d

        //clave publica
        clavepublica[2] = ModuloClaves(); //n
        clavepublica[3] =  E(); //e
        return clavepublica;
    }


}
