package S_DES;

public class DescifradoSDES {

    private int Key[];
    private int K1[];
    private int K2[];

    //constructor
    public DescifradoSDES(int[] key, int[] k1, int[] k2) {
        Key = key;
        K1 = k1;
        K2 = k2;
    }

    //permutación de 8 bits
    private int[] EightPermutations(int[] value){
        int[] eightpermutatios= new int[8];

        eightpermutatios[0] = value[1]; //2
        eightpermutatios[1] = value[5]; //6
        eightpermutatios[2] = value[2]; //3
        eightpermutatios[3] = value[0]; //1
        eightpermutatios[4] = value[3]; //4
        eightpermutatios[5] = value[7]; //8
        eightpermutatios[6] = value[4]; //5
        eightpermutatios[7] = value[6]; //7

        return eightpermutatios;
    }

    //dividir pemutación de 8 bits, izquierda
    private int[] LeftBits(int[] value){
        int[] leftbits= new int[4];

        leftbits[0] = value[0];
        leftbits[1] = value[1];
        leftbits[2] = value[2];
        leftbits[3] = value[3];

        return leftbits;
    }

    //dividir permutación de 8 bits,derecha
    private int[] RightPermutations(int[] value){
        int[] rightpermutations = new int[4];

        rightpermutations[0] = value[4];
        rightpermutations[1] = value[5];
        rightpermutations[2] = value[6];
        rightpermutations[3] = value[7];

        return rightpermutations;
    }

    //permutación de 4 bits
    private int[] FourPermutations(int[] value){
        int[] fourpermutations= new int[4];

        fourpermutations[0] = value[1]; //2
        fourpermutations[1] = value[3]; //4
        fourpermutations[2] = value[2]; //3
        fourpermutations[3] = value[0]; //1

        return fourpermutations;
    }

    //Expandir 4 bits en 8 bits
    private int[] ExpansionPermutations(int[] value){
        int[] expansionpermutations = new int[8];

        expansionpermutations[0] = value[3]; //4
        expansionpermutations[1] = value[0]; //1
        expansionpermutations[2] = value[1]; //2
        expansionpermutations[3] = value[2]; //3

        expansionpermutations[0] = value[1]; //2
        expansionpermutations[1] = value[2]; //3
        expansionpermutations[2] = value[3]; //4
        expansionpermutations[3] = value[0]; //1

        return expansionpermutations;
    }

    // valor1 xor valor2, 8 bits
    private int[] XOReightbits(int[] value1, int[] value2){
        int[] xoreightpermutations = new int[8];

        xoreightpermutations[0] = value1[0] ^ value2[0];
        xoreightpermutations[1] = value1[1] ^ value2[1];
        xoreightpermutations[2] = value1[2] ^ value2[2];
        xoreightpermutations[3] = value1[3] ^ value2[3];
        xoreightpermutations[4] = value1[4] ^ value2[4];
        xoreightpermutations[5] = value1[5] ^ value2[5];
        xoreightpermutations[6] = value1[6] ^ value2[6];
        xoreightpermutations[7] = value1[7] ^ value2[7];

        return xoreightpermutations;
    }

    // valor1 xor valor2, 8 bits
    private int[] XORfourbits(int[] value1, int[] value2){
        int[] xorfourpermutations = new int[4];

        xorfourpermutations[0] = value1[0] ^ value2[0];
        xorfourpermutations[1] = value1[1] ^ value2[1];
        xorfourpermutations[2] = value1[2] ^ value2[2];
        xorfourpermutations[3] = value1[3] ^ value2[3];


        return xorfourpermutations;
    }

    //matriz 4*4
    private int[][] S0(){
        int[][] box0=new int[4][4];

        box0[0][0]=01;
        box0[0][1]=00;
        box0[0][2]=11;
        box0[0][3]=10;

        box0[1][0]=11;
        box0[1][1]=00;
        box0[1][2]=01;
        box0[1][3]=00;

        box0[2][0]=00;
        box0[2][1]=10;
        box0[2][2]=01;
        box0[2][3]=11;

        box0[3][0]=11;
        box0[3][1]=01;
        box0[3][2]=11;
        box0[3][3]=00;


        return  box0;
    }

    //matriz 4*4
    private int[][] S1(){
        int[][] box1=new int[4][4];

        box1[0][0]=01;
        box1[0][1]=00;
        box1[0][2]=11;
        box1[0][3]=10;

        box1[1][0]=11;
        box1[1][1]=00;
        box1[1][2]=01;
        box1[1][3]=00;

        box1[2][0]=00;
        box1[2][1]=10;
        box1[2][2]=01;
        box1[2][3]=11;

        box1[3][0]=11;
        box1[3][1]=01;
        box1[3][2]=11;
        box1[3][3]=00;


        return  box1;
    }

    //------------------------------------Proceso de descifrado------------------------------------------


}
